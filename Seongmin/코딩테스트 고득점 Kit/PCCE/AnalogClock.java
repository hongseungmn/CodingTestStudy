public class AnalogClock {
  public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
    int answer = 0;
    //끝나는 시점에서 시작 시점을 빼 초를 구한다
    int totalSec = (h2 * 3600 + m2 * 60 + s2) - (h1 * 3600 + m1 * 60 + s1);
    int curSec = s1; //현재 초침 위치
    int curMin = m1; //현재 분침 위치
    int curHour = h1 * 5 + m1 / 12; //현재 시침 위치
    for(int i=0; i<=totalSec; i++) { // 1초마다 위치를 계산
      //초침이 분침과 겹치는지 판단
      if(curSec == curMin) {
        System.out.printf(String.format("%d 초 후 초침과 분침이 겹침\n",i));
        answer++;
      }
      if(curSec == curHour) {
        System.out.printf(String.format("%d 초 후 초침과 시침이 겹침\n",i));
        answer++;
      }
      if(curSec == 60) { //초침 위치가 60초일 때
        curMin++; //분침 위치 업데이트
        curSec -= 60; //초침 위치 초기화
      }
      if(i % 720 == 0) { //12분(720초)마다 시침 위치 업데이트
        curHour++;
      }
      curSec++;
    }
    return answer;
  }
  public static void main(String[] args) {
    /*
     * h1	m1	s1	h2	m2	s2	result
      0	5	30	0	7	0	2
      12	0	0	12	0	30	1
      0	6	1	0	6	6	0
      11	59	30	12	0	0	1
      11	58	59	11	59	0	1
      1	5	5	1	5	6	2
      0	0	0	23	59	59	2852
     */
    // int h1 = 0;
    // int m1 = 5;
    // int s1 = 30;
    // int h2 = 0;
    // int m2 = 7;
    // int s2 = 0;

    int[] test1 = new int[]{0,	5,	30,	0,	7,	0};
    int[] test2 = new int[]{12, 0, 0, 12, 0, 30};
    int[] test3 = new int[]{0, 6, 1, 0, 6, 6};
    int[] test4 = new int[]{11,	59,	30,	12,	0,	0};
    int[] test5 = new int[]{11,	58,	59,	11,	59,	0};
    int[] test6 = new int[]{1,	5,	5,	1,	5,	6};
    int[] test7 = new int[]{0,	0,	0,	23,	59,	59};
    
    int h1 = test1[0];
    int m1 = test1[1];
    int s1 = test1[2];
    int h2 = test1[3];
    int m2 = test1[4];
    int s2 = test1[5];
    AnalogClock analogClock = new AnalogClock();
    int answer = analogClock.solution(h1, m1, s1, h2, m2, s2);
    System.out.println("정답은 : " + answer);
  }
}
