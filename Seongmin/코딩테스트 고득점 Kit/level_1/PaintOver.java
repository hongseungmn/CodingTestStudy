public class PaintOver {
  public int solution(int n, int m, int[] section) {
    int answer = 1;
    //가장 처음 발견된 칠해지지 않은 부분부터 m만큼 칠한다
    //section의 경우 정렬되어 있다
    int secStart = section[0]; //시작부분
    
    for(int i = 1; i<section.length; i++) {
      if(secStart + m - 1 >= section[i]) {
        continue;
      }
      else {
        secStart = section[i];
        answer++;
      }
    }
    return answer;
  }
  public static void main(String[] args) {
    /*
     * n	m	section	result
      8	4	[2, 3, 6]	2
      5	4	[1, 3]	1
      4	1	[1, 2, 3, 4]	4
      10, 3, [1, 3, 6, 7] 2
     */

    int n = 10;
    int m = 3;
    int[] section = {1, 3, 6, 7};

    PaintOver paintOver = new PaintOver();
    int answer = paintOver.solution(n, m, section);
    System.out.println("정답은 : " +answer);
  }
}
