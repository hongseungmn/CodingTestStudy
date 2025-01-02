public class ClearWallPaper {
  public int[] solution(String[] wallpaper) {
    int[] answer = {};
    //가장 왼쪽 상단과 오른쪽 하단의 좌표값을 저장할 변수
    int minX = Integer.MAX_VALUE;
    int maxX = Integer.MIN_VALUE;
    int minY = Integer.MAX_VALUE;
    int maxY = Integer.MIN_VALUE;

    for(int row = 0; row < wallpaper.length; row++) {
      for(int col = 0; col < wallpaper[row].length(); col++) {
        if(wallpaper[row].charAt(col) == '#') {
          System.out.printf(String.format("(%d, %d)\n", row, col));
          minX = Math.min(minX, col);
          maxX = Math.max(maxX, col+1);
          minY = Math.min(minY, row);
          maxY = Math.max(maxY, row+1);
        }
      }
    }

    return new int[] {minY, minX, maxY, maxX};
  }
  public static void main(String[] args) {
    /*
     * wallpaper	result
      [".#...", "..#..", "...#."]	[0, 1, 3, 4]
      ["..........", ".....#....", "......##..", "...##.....", "....#....."]	[1, 3, 5, 8]
      [".##...##.", "#..#.#..#", "#...#...#", ".#.....#.", "..#...#..", "...#.#...", "....#...."]	[0, 0, 7, 9]
      ["..", "#."]	[1, 0, 2, 1]
     */

    String[] wallpaper = {".#...", "..#..", "...#."};
    ClearWallPaper clearWallPaper = new ClearWallPaper();
    int[] arr = clearWallPaper.solution(wallpaper);
    System.out.printf(String.format("시작점 : (%d, %d) , 끝 점 : (%d, %d)", arr[0], arr[1], arr[2], arr[3]));
  }
}
