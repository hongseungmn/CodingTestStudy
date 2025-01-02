package backjoon;

public class EscapeBall2 {

  //초기 정보로 보드 초기화하는 함수
  public int[][] getInitBoard(int n, int m, String str) {
    int[][] board = new int[n+1][m+1];
    int[] redLoc = new int[2];
    int[] blueLoc = new int[2];
    int[] destination = new int[2];
    char[] ar = str.toCharArray();
    for(int i = 0; i<m; i++) {
      for(int j = 0; j<n; j++) {
        switch (ar[i+j]) {
          case '#':
            board[i][j] = -1;
            break;
          case '.':
            board[i][j] = 0;
            break;
          case 'B':
            board[i][j] = 2;
            blueLoc[0] = i;
            blueLoc[1] = j;
            break;
          case 'R':
            board[i][j] = 1;
            redLoc[0] = i;
            redLoc[1] = j;
            break;
          case '0':
            board[i][j] = 3;
            destination[0] = i;
            destination[1] = j;
            break;
          default:
            break;
        }
      }
    }
    return board;
  }

  public void printBoard(int[][] board) {
    for(int[] arr : board) {
      for(int a : arr) {
        System.out.print(a + "\t");
      }
      System.out.println();
    }
  }

  public void solution(int n, int m, String str) {
    //보드 정보 초기화
    int[][] board = getInitBoard(n, m, str);
    printBoard(board);
  }
  public static void main(String[] args) {
    int n = 5;
    int m = 5;

    String str = "######..B##.#.##RO.######";
    EscapeBall2 escapeBall2 = new EscapeBall2();
    escapeBall2.solution(n, m, str);  
  }
}
