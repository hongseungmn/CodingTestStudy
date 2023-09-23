/*
 * 체육복
 * https://school.programmers.co.kr/learn/courses/30/lessons/42862
 * 
 */
//홍성민

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GymSuit {
  public int solution(int n, int[] lost, int[] reserve) {
    int answer = 0;
    Boolean[] booleanArr = new Boolean[n+1];
    Arrays.fill(booleanArr,true);
    PriorityQueue<Integer> lostQueue = new PriorityQueue<>((o1,o2)-> {return o1 - o2;}); //인덱스 순으로 정렬
    PriorityQueue<Integer> reverseQueue = new PriorityQueue<>((o1,o2)-> {return o1 - o2;}); //인덱스 순으로 정렬
    IntStream.of(lost).forEach((e)->lostQueue.add(e)); //추가
    IntStream.of(reserve).forEach((e)->reverseQueue.add(e)); //추가
    
    for(int i : reserve) { //만약 잃어버린 인원이 여분이 있는 경우 먼저 제거
      if(lostQueue.contains(i)) {
        lostQueue.remove(i);
        reverseQueue.remove(i);
      }
    }

    while(!lostQueue.isEmpty()) {
      int lostIndex = lostQueue.poll(); //잃어버린 인원의 인덱스 
      if(reverseQueue.isEmpty()) {booleanArr[lostIndex] = false;}
      else if(reverseQueue.contains(lostIndex-1)) { //여분의 옷이 있는 인원의 인덱스가 앞이나 뒤인 경우 ->단, 정렬한 순서에 의거해 앞의 인원부터 처리해야 한다
        booleanArr[lostIndex] = true;
        reverseQueue.remove(lostIndex-1);
      }
      else if(reverseQueue.contains(lostIndex+1)) { ////여분의 옷이 있는 인원의 인덱스가 앞이나 뒤인 경우 
        booleanArr[lostIndex] = true;
        reverseQueue.remove(lostIndex+1);
      }
      else {
        booleanArr[lostIndex] = false;
      }
    }
    for(Boolean bool : booleanArr) {
      if(bool) answer++;
    }  
    Stream.of(booleanArr).forEach((e) -> System.out.printf(" %s ",e));
    return answer-1;
  }
  public static void main(String[] args) {
    /*
      n	      lost	      reserve	      return
      5	      [2, 4]	    [1, 3, 5]	    5
      5	      [2, 4]	    [3]	          4
      3	      [3]	        [1]	          2
      5       [4, 5]      [3, 4]        4
      10      [4,7,9]   [2,8]
     */
    GymSuit lostCloth = new GymSuit();
    int n = 10;
    int[] lost = {4,7,9};
    int[] reserve = {2,8}; // 1 2 3 4 5 6 7 8 9 10
                           // o o o x o o x o o o
                           // true  true  true  false  true  true  false  true  false  true 
    System.out.println(lostCloth.solution(n,lost,reserve));
  }
}

