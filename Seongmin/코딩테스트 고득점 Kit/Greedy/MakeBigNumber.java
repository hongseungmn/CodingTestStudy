/*
 * 큰 수 만들기
 * https://school.programmers.co.kr/learn/courses/30/lessons/42883
 * 어떤 숫자에서 k개의 수를 제거했을 때 얻을 수 있는 가장 큰 숫자를 구하려 합니다.
 */
//홍성민

// 문자열 계산은 StringBuilder 객체를 사용하면 시간 복잡도가 감소한다!

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MakeBigNumber {

  public String solution(String number, int k) {
    String answer = "";
    StringBuilder numberBuilder = new StringBuilder(number);
    int index = 0; //인덱스로 사용할 변수
    while(0<k) {
      if(index == numberBuilder.length()-1) { //만약 현재 인덱스가 마지막까지 도달한 경우(큰수부터 정렬됨 ->4321) -> 마지막원소삭제
        numberBuilder.deleteCharAt(numberBuilder.length()-1);
        index--;
        k--;
      }
      else if(numberBuilder.charAt(index) == 9) {//해당 인덱스의 숫자가 9라면 건너뛴다-> 가장 크기 때문에 계산 불필요
        index++;
        continue;
      } 
      else if(numberBuilder.charAt(index) < numberBuilder.charAt(index+1)) {//다음 숫자와 비교해 작을 경우 삭제를 한다
        System.out.println("뒤의 수가 크므로 현재 인덱스 제거 : "+ numberBuilder.charAt(index));
        numberBuilder.deleteCharAt(index);
        k--;
        if(index == 0 ) continue;
        index--;
        System.out.println("k : "+k);
      }
      else { //아무 분기에 걸리지 않은경우 다음 숫자로 인덱스 이동
        index++;
      }
    }
    answer = numberBuilder.toString();
    return answer;
  }
  public static void main(String[] args) {
    /*
      number	          k	      return
      "1924"	          2	      "94"
      "1231234"	        3	      "3234"
      "4177252841"	    4	      "775841"
      "4321"            1       "432"
     */
    String number = "4321";
    int k = 1;
    MakeBigNumber makeBigNumber = new MakeBigNumber();
    System.out.println("정답은 : "+ makeBigNumber.solution(number, k));
  }
}
