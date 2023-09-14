/*
    H-index
    https://school.programmers.co.kr/learn/courses/30/lessons/42747
    H-Index는 과학자의 생산성과 영향력을 나타내는 지표
 */

//0914 홍성민 풀이


import java.util.Arrays;

public class H_Index {
    public int solution(int[] citations) {
    int answer = 0;
    //정렬
    Arrays.sort(citations);
    //논문 인용수와 배열의 인덱스로 구분
    for(int i = 0;i<citations.length;i++) {
      int count = citations.length - i;
      if(citations[i] >= count) {
        answer = count;
        break;
      }
    }
    return answer;
  }
}
