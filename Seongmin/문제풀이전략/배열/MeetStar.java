package Seongmin.문제풀이전략.배열;

/*
 * 교점에 별 만들기
 * https://school.programmers.co.kr/learn/courses/30/lessons/87377
 * 홍성민
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MeetStar {
  List<Point> pointList = new ArrayList<Point>(); // 교점 배열을 저장할 리스트

  public class Point { // 교점
    private long x;
    private long y;

    Point(long x, long y) {
      this.x = x;
      this.y = y;
    }
  }
  /**
   * 교점을 구하는 메소드
   * @param line1 직선 1
   * @param line2 직선 2
   * @return
   */
  public int findMeetPoint(int[] line1, int[] line2) {
    int a, b, e;
    int c, d, f;

    a = line1[0];
    b = line1[1];
    e = line1[2];

    c = line2[0];
    d = line2[1];
    f = line2[2];

    // 교점 좌표 구하기 공식
    float x = (float) (b * f - e * d) / (a * d - b * c);
    float y = (float) (e * c - a * f) / (a * d - b * c);
    System.out.printf("교점 (x,y) : (%.1f, %.1f) \n", x, y);
    if (isInteger(x, y)) { // 정수일 때만 교점 생성후 리스트에 저장
      System.out.printf("정수 교점 (x,y) : (%.1f, %.1f) \n", x, y);
      Point point = new Point((int) x, (int) y);
      pointList.add(point);
    }
    return 0;
  }

  /**
   * 해당 교점이 정수 좌표인지 판별
   * @param x x좌표
   * @param y y좌표
   * @return 정수면 true
   */
  public boolean isInteger(float x, float y) { // 해당 교점이 정수이면 true, 실수면 false 반환
    return x % 1 == 0 && y % 1 == 0;
  }

  /**
   * 가장 작은 좌표값 구하기
   * @return 교점 반환
   */
  public Point getMinPoint() {
    long x = Long.MAX_VALUE;
    long y = Long.MAX_VALUE;
    for (Point p : pointList) {
      if (p.x < x)
        x = p.x;
      if (p.y < y)
        y = p.y;
    }
    return new Point(x, y);
  }
  /**
   * 가장 큰 좌표값 구하기
   * @return 교점 반환
   */
  public Point getMaxPoint() {
    long x = Long.MIN_VALUE;
    long y = Long.MIN_VALUE;
    for (Point p : pointList) {
      if (p.x > x)
        x = p.x;
      if (p.y > y)
        y = p.y;
    }
    return new Point(x, y);
  }

  public String[] solution(int[][] line) {
    // 모든 두 직선 쌍에대한 반복
    for (int i = 0; i < line.length - 1; i++) {
      for (int j = i + 1; j < line.length; j++) {
        int[] line1 = line[i];
        int[] line2 = line[j];
        int meetPoint = findMeetPoint(line1, line2);
      }
    }

    // 최소 좌표값 구하기
    Point minPoint = getMinPoint();
    // 최대 좌표값 구하기
    Point maxPoint = getMaxPoint();

    // 2차원 배열 크기 설정
    int width = (int) (maxPoint.x - minPoint.x + 1);
    int height = (int) (maxPoint.y - minPoint.y + 1);
    char[][] arr = new char[height][width];
    for (char[] row : arr) {
      Arrays.fill(row, '.');
    }

    // 2차원 배열에 별 표시
    for (Point p : pointList) {
      int x = (int) (p.x - minPoint.x);
      int y = (int) (maxPoint.y - p.y);
      arr[y][x] = '*';
    }

    String[] result = new String[arr.length];
    for (int i = 0; i < result.length; i++) {
      result[i] = new String(arr[i]);
    }
    return result;
  }

  public static void main(String[] args) {
    /*
     * Ax + by + C =0 의 정보가 입력
     * #case 1
     * line = {{2,-1,4}, {-2,-1,4}, {0,-1,1}, {5,-8,-12}, {5,8,12}};
     * answer = [
     * "....*....",
     * ".........",
     * ".........",
     * "*.......*",
     * ".........",
     * ".........",
     * ".........",
     * ".........",
     * "*.......*"]
     */
    int[][] line = { { 2, -1, 4 }, { -2, -1, 4 }, { 0, -1, 1 }, { 5, -8, -12 }, { 5, 8, 12 } };
    /*
     * 문제풀이 흐름
     * 1. 모든 직선 쌍에 대해 반복
     * - 교점 좌표 구하기
     * - 정수 좌표만 저장
     * 2. 저장된 정수들에 대해 x, y 좌표의 최댓값, 최솟값 구하기
     * 3. 구한 최댓값, 최솟값을 이용하여 2차원 배열의 크기 결정
     * 4. 2차원 배열에 별 표시
     * 5. 문자열 배열로 변환 후 반환
     */
    MeetStar meetStar = new MeetStar();
    System.out.println("정답은 : " + meetStar.solution(line));
  }
}
