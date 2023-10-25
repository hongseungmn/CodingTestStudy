/*
 * 카펫
 * https://school.programmers.co.kr/learn/courses/30/lessons/42842
 * 홍성민
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Carpet {
  public int[] solution(int brown, int yellow) {
    List<int[]> list = new ArrayList<>();
    //전체 넓이 구하기
    int area = brown + yellow;
    //약수 구할 기준
    int i = area/2+1;
    while(i>2) {
      if(area % i == 0 && (area/i-2)*(i-2) ==yellow ) { //약수 이면서 내부 넓이가 노란색 크기와 같은 경우만 리스트에 추가
        list.add(new int[]{i,area / i});
      }
      i--;
    }
    list.sort(new Comparator<int[]>() { //두 약수의 가장 근접한 약수끼리 정렬
      @Override
      public int compare(int[] o1, int[] o2) {
        int o1_diff = Math.abs(o1[0] - o1[1]);
        int o2_diff = Math.abs(o2[0] - o2[1]);
        return  o1_diff - o2_diff;
      }
    });
    return list.get(0);
  }
  public static void main(String[] args) {
    /*
      brown	    yellow	return
      10	      2	      [4, 3]
      8	        1	      [3, 3]
      24	      24	    [8, 6]
      [18, 6] -> [8, 3] (o)
      [18, 6] -> [6, 4] (x)
    */
    int brown = 18;
    int yellow = 6;
    Carpet carpet = new Carpet();
    System.out.println("정답은 :"+ carpet.solution(brown, yellow)[0]+" " + carpet.solution(brown, yellow)[1]);
  }
  
}
