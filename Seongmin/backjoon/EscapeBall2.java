package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/13460
//구슬탈출 2

/* 기울임과 동시에 먼저 움직이는 것까지 구현했으나 bfs에서 로직이 꼬임
 * + 먼저 움직이는 것에 대한 로직의 복잡함 문제
 * + 전체 맵을 이용해 bfs를 구현하는데 있어 어려움 -> 단순화 하여 공의 좌표만 가지고 수행하자
 */
// public class EscapeBall2 {
//   static int N, M; //좌표 크기
//   static char[][] maze; //미로 
//   static int[][] tiltType = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; //기울이기 -> 오른쪽, 왼쪽, 아래, 위 방향
//   static int[] redLoc;
//   static int[] blueLoc;
//   static int[] holeLoc;

//   //구멍에 빠지는 여부 확인
//   static boolean redFlag = false;
//   static boolean blueFlag = false;
  

//   public static void main(String[] args) throws IOException {
//     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
// 		StringTokenizer st = new StringTokenizer(br.readLine());

//     //초기 값 세팅
//     N = Integer.parseInt(st.nextToken());		
// 		M = Integer.parseInt(st.nextToken());	
    
//     maze = new char[N][M];

//     //지도 초기화 및 위치 정의
//     for(int i=0; i<N; i++) {
//       String str = br.readLine();
//       for(int j=0; j<M; j++) {
//         maze[i][j] = str.charAt(j);
//         if(str.charAt(j) == 'B') {
//           blueLoc = new int[] {i, j};
//           maze[i][j] = '.';
//         } else if(str.charAt(j) == 'R') {
//           redLoc = new int[] {i, j};
//           maze[i][j] = '.';
//         } else if(str.charAt(j) == '0') {
//           holeLoc = new int[] {i, j};
//         }
//       }
//     }    
//     // System.out.println("--------------");
//     // printMaze();
//     // System.out.println("--------------");
//     // tilt(maze, 0); //오른쪽으로 기울이기
//     // printMaze();
//     // System.out.println("--------------");
//     // tilt(maze, 1); //왼쪽으로 기울이기
//     // printMaze();
//     // System.out.println("--------------");
//     // tilt(maze, 2); //아래쪽으로 기울이기
//     // printMaze();
//     // System.out.println("--------------");
//     // tilt(maze, 3); //위쪽으로 기울이기
//     // printMaze();
//     // System.out.println("--------------");

//     //이제 BFS를 수행한다
//     //red공이 기울였을 때 움직일 수 있는 방향으로 ->

//     Queue<int[]> queue = new LinkedList<>();
//     //최초 기울임 정의
//     for(int i=0; i<tiltType.length; i++) {
//       int newRedX = redLoc[1] + tiltType[i][1];
//       int newRedY = redLoc[0] + tiltType[i][0];
//       if(maze[newRedY][newRedX] != '#') {
//         queue.add(new int[]{i , 0}); //움직일 수 있는 방향 및 기울인 횟수 넣기
//       }
//     }
//     //방문 추적 
//     boolean[][] visited = new boolean[N][M];
//     visited[redLoc[0]][redLoc[1]] = true;
//     while(!queue.isEmpty()) {
//       int[] temp = queue.poll();
//       int direction = temp[0]; //기울일 방향 가져오기
//       int cnt = temp[1]; //기울인 횟수 가져오기
//       tilt(maze, direction); //기울이기
//       //기울인 결과 방문한 적이 있는 경우 수행하지 않는다
//       if(visited[redLoc[0]][redLoc[1]]) {
//         continue;
//       }
//       visited[redLoc[0]][redLoc[1]] = true;
//       if(redFlag || blueFlag) { //공이 구멍을 들어갔을 경우
//         if(redFlag && !blueFlag) { //빨간 공만 들어간 경우
//           System.out.println(cnt);
//           return;
//         } else { //동시에 들어가거나 파란공만 들어갔을 경우
//           System.out.println(-1);
//           return;
//         }
//       }
//       //구멍을 들어가지 않았을 경우 현재 업데이트된 빨간 공 위치에서 기울일 수 있는 방향 추가
//       for(int i=0; i<tiltType.length; i++) {
//         int newRedX = redLoc[1] + tiltType[i][1];
//         int newRedY = redLoc[0] + tiltType[i][0];
//         if(maze[newRedY][newRedX] != '#') {
//           queue.add(new int[]{i , cnt + 1}); //움직일 수 있는 방향 및 기울인 횟수 넣기
//         }
//       }
//     }
//   }

//   static public void printMaze() {
//     char[][] m = new char[maze.length][];
//     for (int i = 0; i < maze.length; i++) {
//         m[i] = Arrays.copyOf(maze[i], maze[i].length); // 각 행 복사
//     }
//     m[redLoc[0]][redLoc[1]] = 'R';
//     m[blueLoc[0]][blueLoc[1]] = 'B';
//     for(int i =0; i<m.length; i++) {
//       for(int j=0; j<m[i].length; j++) {
//         System.out.print(" " + m[i][j]);
//       }
//       System.out.println();
//     }
//   }

//   static void tilt(char[][] maze, int type) {
//     int[] move = tiltType[type]; //기울이기 방향 정의
//     //해당 방향으로 끝까지 공 이동 먼저 이동할 공 설정 
//     boolean redMoveFirst = findMoveFirst(move);

//     if(redMoveFirst) {
//       //빨간공 먼저 움직이기
//       int x = redLoc[1];
//       int y = redLoc[0];
//       while(true) {
//         if(maze[y + move[0]][x + move[1]] == '#') break; //벽을 만나면 이동 멈춤
//         //만약 구멍에 빠진다면 빨간공의 위치를 지워버리고 flag 변경
//         if(maze[y + move[0]][x + move[1]] == 'O') {
//           redFlag = true;
//           break;
//         }
//         x += move[1];
//         y += move[0];
//       }
      
//       redLoc[1] = x;
//       redLoc[0] = y;

//       if(redFlag) {
//         redLoc[1] = -1;
//         redLoc[0] = -1;
//       }
//       //파란공 움직이기
//       x = blueLoc[1];
//       y = blueLoc[0];
//       while(true) {
//         //파란 공은 빨간 공의 위치에 있을 수 없다
//         if(maze[y + move[0]][x + move[1]] == '#' || (y + move[0] == redLoc[0] && x + move[1] == redLoc[1])) break;
//         //파란 공이 구멍에 빠진다면 flag 변경
//         if(maze[y + move[0]][x + move[1]] == 'O') {
//           blueFlag = true;
//           break;
//         }
//         x += move[1];
//         y += move[0];
//       }
//       blueLoc[1] = x;
//       blueLoc[0] = y;
      
//     } else {
//       //파란공 먼저 움직이기
//       int x = blueLoc[1];
//       int y = blueLoc[0];
//       while(true) {
//         if(maze[y + move[0]][x + move[1]] == '#') break;
//         if(maze[y + move[0]][x + move[1]] == 'O') { //파란공이 바로 빠지면 flag 변경
//           blueFlag = true;
//           break;
//         }
//         x += move[1];
//         y += move[0];
//       }
//       blueLoc[1] = x;
//       blueLoc[0] = y;
//       //파란공 움직이기
//       x = redLoc[1];
//       y = redLoc[0];
//       while(true) {
//         //빨간 공은 파란 공의 위치에 있을 수 없다
//         if(maze[y + move[0]][x + move[1]] == '#' || (y + move[0] == blueLoc[0] && x + move[1] == blueLoc[1])) break;
//         //만약 구멍에 빠진다면 빨간공의 위치를 지워버리고 flag 변경
//         if(maze[y + move[0]][x + move[1]] == 'O') {
//           System.out.println(maze[y + move[0]][x + move[1]]);
//           redFlag = true;
//           break;
//         }
//         x += move[1];
//         y += move[0];
//       }
//       redLoc[1] = x;
//       redLoc[0] = y;
//     }

//     System.out.println("--------------");
//     printMaze();
//   }

//   static boolean findMoveFirst(int[] move) {
//     /*
//      * 만약 방향이 오른쪽으로 기울이기 (0, 1) 이고 redLoc = (0, 1), blueLoc = (0, 2) 인 경우
//      * 먼저 움직여야 하는 공은 파란색 공이다 (움직이는 방향 * 위치 에서 큰 값, 반대의 경우도 성립)
//      */
//     int red = move[0] * redLoc[0] + move[1] * redLoc[1];
//     int blue = move[0] * blueLoc[0] + move[1] * blueLoc[1];

//     if(red > blue) {
//       return true;
//     } else return false;
//   }
// }

public class EscapeBall2 {
  static int N, M; //좌표 크기
  static char[][] maze; //미로 
  static int[][] tiltType = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; //기울이기 -> 오른쪽, 왼쪽, 아래, 위 방향
  static int[] redLoc;
  static int[] blueLoc;

  //구멍에 빠지는 여부 확인
  static boolean redFlag = false;
  static boolean blueFlag = false;
  

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

    //초기 값 세팅
    N = Integer.parseInt(st.nextToken());		
		M = Integer.parseInt(st.nextToken());	
    
    maze = new char[N][M];

    //지도 초기화 및 위치 정의
    for(int i=0; i<N; i++) {
      String str = br.readLine();
      for(int j=0; j<M; j++) {
        maze[i][j] = str.charAt(j);
        if(str.charAt(j) == 'B') {
          blueLoc = new int[] {i, j};
          maze[i][j] = '.';
        } else if(str.charAt(j) == 'R') {
          redLoc = new int[] {i, j};
          maze[i][j] = '.';
        }
      }
    }   
    
    bfs()

  }

  static public void printMaze() {
    char[][] m = new char[maze.length][];
    for (int i = 0; i < maze.length; i++) {
        m[i] = Arrays.copyOf(maze[i], maze[i].length); // 각 행 복사
    }
    m[redLoc[0]][redLoc[1]] = 'R';
    m[blueLoc[0]][blueLoc[1]] = 'B';
    for(int i =0; i<m.length; i++) {
      for(int j=0; j<m[i].length; j++) {
        System.out.print(" " + m[i][j]);
      }
      System.out.println();
    }
  }

  static void tilt(char[][] maze, int type) {
    int[] move = tiltType[type]; //기울이기 방향 정의
    //해당 방향으로 끝까지 공 이동 먼저 이동할 공 설정 
    boolean redMoveFirst = findMoveFirst(move);

    if(redMoveFirst) {
      //빨간공 먼저 움직이기
      int x = redLoc[1];
      int y = redLoc[0];
      while(true) {
        if(maze[y + move[0]][x + move[1]] == '#') break; //벽을 만나면 이동 멈춤
        //만약 구멍에 빠진다면 빨간공의 위치를 지워버리고 flag 변경
        if(maze[y + move[0]][x + move[1]] == 'O') {
          redFlag = true;
          break;
        }
        x += move[1];
        y += move[0];
      }
      
      redLoc[1] = x;
      redLoc[0] = y;

      if(redFlag) {
        redLoc[1] = -1;
        redLoc[0] = -1;
      }
      //파란공 움직이기
      x = blueLoc[1];
      y = blueLoc[0];
      while(true) {
        //파란 공은 빨간 공의 위치에 있을 수 없다
        if(maze[y + move[0]][x + move[1]] == '#' || (y + move[0] == redLoc[0] && x + move[1] == redLoc[1])) break;
        //파란 공이 구멍에 빠진다면 flag 변경
        if(maze[y + move[0]][x + move[1]] == 'O') {
          blueFlag = true;
          break;
        }
        x += move[1];
        y += move[0];
      }
      blueLoc[1] = x;
      blueLoc[0] = y;
      
    } else {
      //파란공 먼저 움직이기
      int x = blueLoc[1];
      int y = blueLoc[0];
      while(true) {
        if(maze[y + move[0]][x + move[1]] == '#') break;
        if(maze[y + move[0]][x + move[1]] == 'O') { //파란공이 바로 빠지면 flag 변경
          blueFlag = true;
          break;
        }
        x += move[1];
        y += move[0];
      }
      blueLoc[1] = x;
      blueLoc[0] = y;
      //파란공 움직이기
      x = redLoc[1];
      y = redLoc[0];
      while(true) {
        //빨간 공은 파란 공의 위치에 있을 수 없다
        if(maze[y + move[0]][x + move[1]] == '#' || (y + move[0] == blueLoc[0] && x + move[1] == blueLoc[1])) break;
        //만약 구멍에 빠진다면 빨간공의 위치를 지워버리고 flag 변경
        if(maze[y + move[0]][x + move[1]] == 'O') {
          System.out.println(maze[y + move[0]][x + move[1]]);
          redFlag = true;
          break;
        }
        x += move[1];
        y += move[0];
      }
      redLoc[1] = x;
      redLoc[0] = y;
    }

    //System.out.println("--------------");
    //printMaze();
  }

  static boolean findMoveFirst(int[] move) {
    /*
     * 만약 방향이 오른쪽으로 기울이기 (0, 1) 이고 redLoc = (0, 1), blueLoc = (0, 2) 인 경우
     * 먼저 움직여야 하는 공은 파란색 공이다 (움직이는 방향 * 위치 에서 큰 값, 반대의 경우도 성립)
     */
    int red = move[0] * redLoc[0] + move[1] * redLoc[1];
    int blue = move[0] * blueLoc[0] + move[1] * blueLoc[1];

    if(red > blue) {
      return true;
    } else return false;
  }
}

