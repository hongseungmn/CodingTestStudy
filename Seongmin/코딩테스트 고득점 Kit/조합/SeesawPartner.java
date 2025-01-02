/*
  시소짝궁
 * https://school.programmers.co.kr/learn/courses/30/lessons/152996
 */

import java.util.*;

public class SeesawPartner {
  //조합 -> 시간 초과
  // public long solution(int[] weights) {
  //   Set<String> uniqueCombinations = new HashSet<>();
  //   comb1(weights, 0, 2, uniqueCombinations);
  //   long answer = 0;
  //   // 출력
  //   for (String combination : uniqueCombinations) {
        
  //       int pair1 = Integer.parseInt(combination.split(" ")[0]);
  //       int pair2 = Integer.parseInt(combination.split(" ")[1]);
  //       System.out.println(pair1 + " " + pair2);
  //       if(pair1 == pair2 || pair1 * 2 == pair2 || pair1 *3 == pair2 || pair1 *3 == pair2*2 || pair1 * 4 == pair2 || pair1 * 4 == pair2*3) {
  //         answer++;
  //         //System.out.println("시소 짝궁 : "+weights[i] + ", " + weights[j]);
  //       }
  //   }
  //   return answer;
  // }

  // static void comb1(int[] arr, int start, int r, Set<String> uniqueCombinations) {
  //   if (r == 0) {
  //     return;
  //   } else {
  //     for (int i = start; i < arr.length; i++) {
  //       for (int j = i + 1; j < arr.length; j++) {
  //           int[] pair = {arr[i], arr[j]};
  //           java.util.Arrays.sort(pair); // 작은 숫자가 앞에 오도록 정렬
  //           uniqueCombinations.add(pair[0] + " " + pair[1]);
  //         }
  //       comb1(arr, i + 1, r - 1, uniqueCombinations);
  //     }
  //   }
  // }

  public long solution(int[] weights) {
    long answer = 0;

    Map<Double, Integer> map = new HashMap<>();

    double[] divide = new double[]{
        1.0, 2.0/3, 1.0/2, 3.0/4
    };

    Arrays.sort(weights);
    for (int weight : weights) {
        for (double d : divide) {
            if (map.containsKey(weight * d)) {
                answer += map.get(weight * d);
            }
        }
        map.put((double)weight, map.getOrDefault((double)weight, 0) + 1);
    }

    return answer;
}
  public static void main(String[] args) {
    int[] weights = {100, 180, 360, 100, 270};
    SeesawPartner seesawPartner = new SeesawPartner();

    System.out.println("정답은 : " + seesawPartner.solution(weights));
  }
}
