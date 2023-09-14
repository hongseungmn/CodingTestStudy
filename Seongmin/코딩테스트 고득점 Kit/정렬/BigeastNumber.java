/*
    가장 큰 수
    https://school.programmers.co.kr/learn/courses/30/lessons/42746
    0 또는 양의 정수가 주어졌을 때, 정수를 이어 붙여 만들 수 있는 가장 큰 수를 알아내 주세요.
 */

//0914 홍성민 풀이

import java.util.Arrays;
import java.util.Comparator;

public class BigeastNumber {
  public String solution(int[] numbers) {
    String answer = "";
    //문자열배열로 변환
    String[] strArray = new String[numbers.length];
    for (int i = 0; i < numbers.length; i++) {
        strArray[i] = String.valueOf(numbers[i]);
    }
    //두 숫자를 순서에 따라 결합한 두 수 중 큰 숫자로 만들어지도록 Comparator 구현
    Arrays.sort(strArray,new Comparator<String>() {
      @Override
      public int compare(String o1, String o2) {
      //parseInt에서 변환시 범위를 벗어나 런타임 에러 발생
        // if(Integer.parseInt(o1+o2) >= Integer.parseInt(o2+o1)) {
        //   return -1;
        // }
        // else return 1;
        return (o2 + o1).compareTo(o1 + o2);
      }
      
    });
    //출력용
    //Stream<String> sortedArray = Stream.of(strArray);
    //sortedArray.forEach(System.out::println);
    answer = String.join("", strArray);
    return answer.startsWith("0") ? "0" : answer;
  }
  
}
