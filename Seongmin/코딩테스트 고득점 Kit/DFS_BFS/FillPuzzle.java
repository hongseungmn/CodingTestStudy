/*
 * 
 */

import java.util.ArrayList;


public class FillPuzzle {

  int[][] board;
  int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //상하좌우 움직임
  int N, M; //game_board의 가로 세로
  boolean[][] visit_table; // 퍼즐 도출 시 방문 여부 확인용
  boolean[][] visit_board; //퍼즐 맞출 시 방문 여부 확인용

  int answer = 0;

  public int solution(int[][] game_board, int[][] table) {

    N = game_board.length;
    M = game_board[0].length;

    board = game_board;
    visit_board = new boolean[N][M];
    visit_table = new boolean[N][M];

    //table에서 퍼즐 조각 찾기
    for(int i=0; i<N; i++) {
      for(int j=0; j<M; j++) {
        if(!visit_table[i][j] && table[i][j] == 1 ) { // 퍼즐시작점
          bfs(table, i, j); 
        }
      }
    }

    for(int k=0; k<visit_board.length; k++) {
      for(int l=0; l<visit_board.length; l++) {
        if(visit_board[k][l]) answer++;
        System.out.printf("%o ", visit_board[k][l] ? 1 : 0);
      }
      System.out.println();
    }
    
    return answer;
  }

  void bfs(int[][] table, int r, int c) {

    //1. 시작점으로부터 bfs로 퍼즐을 찾은 후 그에 맞는 2차원 배열을 생성

    //1-1) 퍼즐의 좌표들을 담을 ArrayList 생성
    ArrayList<int[]> list = new ArrayList<>();

    //1-2) 퍼즐의 시작점초기화
    list.add(new int[] {r, c});
    visit_table[r][c] = true;

    //1-3)퍼즐에 알맞는 직사각형 형태의 가로 세로를 구해야 한다(x 최소, x 최대) , (y 최소, y 최대)
    int max_R = r;
    int min_R = r;
    int max_C = c;
    int min_C = c;

    //퍼즐 좌표의 현재 값
    int idx = 0;
    
    //2. 좌표들을 순회
    while(idx < list.size()) {
      //2-1) 현재 좌표 가져옴
      int[] now = list.get(idx++);

      //2-2) 현재 좌표의 상하좌우 움직임
      for(int i=0; i<4; i++) {
        int next_R = now[0] + move[i][0];
        int next_C = now[1] + move[i][1];

        //2-3) 테이블의 범위를 넘어섰거나 연결된 좌표의 값이 0 이거나 이미 방문을 했을 때 continue
        if(!checkOverTable(next_R, next_C) || visit_table[next_R][next_C] || table[next_R][next_C] == 0) continue;

        //2-4) 해당 좌표를 리스트에 삽입, 방문 노드로 변경
        visit_table[next_R][next_C] = true;
        list.add(new int[] {next_R, next_C});

        //2-5) 해당 퍼즐의 직사각형 좌표 재설정
        max_R = Math.max(max_R, next_R);
        min_R = Math.min(min_R, next_R);
        max_C = Math.max(max_C, next_C);
        min_C = Math.min(min_C, next_C);
      }
    }

    //3. 해당 직사각형 형태에 맞는 배열을 생성한다
    /*  ex) 
     *  min_R = 1, max_R = 3, min_C = 2, max_C = 4, table = [true, true, false ,...]
     *  
     *     [2] [3] [4]
     * [1]  1   0   0
     * [2]  1   1   0
     * [3]  0   1   1
     * 
     */
    int[][] puzzle = makeArr(list, min_R, max_R, min_C, max_C);
    //  puzzle 출력테스트 
    // for(int i=0; i<puzzle.length; i++) {
    //   for(int j=0; j<puzzle[0].length; j++) {
    //     System.out.printf("%o ", puzzle[i][j]);
    //   }
    //   System.out.println();
    // }
    // System.out.println("--------------------------------");
    //4. 해당 퍼즐을 game_board에 맞춰보는 작업을 수행한다
    // 모든 퍼즐 조각은 격자 칸에 딱 맞게 놓여있으며, 격자 칸을 벗어나거나, 걸쳐 있는 등 잘못 놓인 경우는 없습니다. -> 빈칸에 퍼즐을 꽂기만 하면 됨
    for(int i=0; i < N; i++) {
      for(int j=0; j < M; j++) {
        //4-1) board가 빈칸이고 방문하지 않은 노드를 발견하면 퍼즐을 맞춰본다
        if(board[i][j] == 0 && !visit_board[i][j]) {
          fitPuzzle(i,j,puzzle);
        }
      }
    }
    // System.out.println("answer : " + answer);
  }


  /**
   * 좌표값을 받은 후 해당 좌표가 테이블을 넘었는지를 확인
   * @param r row
   * @param c col
   * @return 테이블을 넘었는지 여부 확인
   */
  boolean checkOverTable(int r, int c) {
    if(r < 0 || c < 0 || r >= N || c >= M) return false;
    else return true;
  }


  /**
   * table에서 해당 퍼즐에 맞는 배열(직사각형 형태)로 리턴한다.
   * @param list 퍼즐 배열을 담고 있는 리스트
   * @param min_R R 최소
   * @param max_R R 최대
   * @param min_C C 최소
   * @param max_C C 최대
   * @return 완성된 퍼즐 배열
   */
  int[][] makeArr(ArrayList<int[]> list ,int min_R, int max_R, int min_C, int max_C) {

    int n = max_R - min_R + 1;
    int m = max_C - min_C + 1;

    //퍼즐 배열 생성
    int[][] puzzle = new int[n][m];

    //list를 순회하면서 해당 좌표에 위치한 경우 퍼즐의 값을 1로 변경한다
    for(int[] p : list) {
      puzzle[p[0] - min_R][p[1] - min_C] = 1;
    }

    return puzzle;
  }

  void fitPuzzle(int r, int c, int[][] puzzle) {

    int[][] rot_puzzle = puzzle;

    // 회전은 할 수 있으므로 회전을 하며 확인
    // 퍼즐을 회전시킨다.
    for(int i=0; i < 4; i++) {
      rot_puzzle = rotatePuzzle(rot_puzzle); // 루프 한번마다 90도씩 회전된 퍼즐을 반환
      // board의 퍼즐 형태와 puzzle을 확인
      int puzzle_row = rot_puzzle.length;
      int puzzle_col = rot_puzzle[0].length;
      for(int k=r; k < r + puzzle_row; k++) {
        for(int l=c; l < c + puzzle_col; l++) {
          if(!checkOverTable(k, l) || board[k][l] != rot_puzzle[k - r][l - c] -1 ) return;
          visit_board[k][l] = true;
        }
      }
    }
  }

  int[][] rotatePuzzle(int[][] puzzle) {

    int n = puzzle.length;
    int m = puzzle[0].length;
    
    int[][] ret = new int[m][n];
    
    for (int i=0;i<n;i++) {
        for (int j=0;j<m;j++) {
            ret[j][n - i - 1] = puzzle[i][j]; 
        }
    }
    return ret;
  }


  public static void main(String[] args) {
    int[][] game_board = new int[][] {{1,1,0,0,1,0},{0,0,1,0,1,0},{0,1,1,0,0,1},{1,1,0,1,1,1},{1,0,0,0,1,0},{0,1,1,1,0,0}};
    int[][] table = new int[][] {{1,0,0,1,1,0},{1,0,1,0,1,0},{0,1,1,0,1,1},{0,0,1,0,0,0},{1,1,0,1,1,0},{0,1,0,0,0,0}};

    FillPuzzle fillPuzzle = new FillPuzzle();
    int answer = fillPuzzle.solution(game_board, table);
    System.out.println("정답은 : " + answer);
  }
}
