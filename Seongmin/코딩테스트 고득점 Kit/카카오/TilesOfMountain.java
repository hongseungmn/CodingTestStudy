/*
  해설
 * https://tech.kakao.com/posts/610 
 */

public class TilesOfMountain {
  public int solution(int n, int[] tops) {
    int answer = 0;
    //해설 참고
    //아래방향 삼각형을 기준으로 경우의 수를 따진다
    /*
      1. 위쪽 정삼각형과 함께 마름모 타일로 덮기
      2. 왼쪽 정삼각형과 함께 마름모 타일로 덮기
      3. 오른쪽 정삼각형과 함께 마름모 타일로 덮기
      4. 정삼각형 타일로 덮기
     */
    for(int i =0; i<n; i++) {
      if(tops[i] == 0) { //해당 아래방향 삼각형의 위 방향이 없다면 -> 1번 불가

      } else { //해당 아래방향 삼각형의 위 방향이 있다면

      }
    } 
    return answer;
  }
  public static void main(String[] args) {
    /*
     * n	tops	result
      4	[1, 1, 0, 1]	149
      2	[0, 1]	11
      10	[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]	7704
     */

    TilesOfMountain tilesOfMountain = new TilesOfMountain();
    int n = 4;
    int[] tops = {1, 1, 0, 1};
    int answer = tilesOfMountain.solution(n, tops);
    System.out.println("정답은 : " + answer);
  }
}
