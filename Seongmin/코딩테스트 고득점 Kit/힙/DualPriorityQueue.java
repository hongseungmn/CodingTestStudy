/*
 * 이중우선순위큐
 * https://school.programmers.co.kr/learn/courses/30/lessons/42628
 * 홍성민
 */

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class DualPriorityQueue {

  public int[] solution(String[] operations) {
    //우선순위 큐 생성
    //최솟값을 계산하는 최소힙(기본적으로 최소값이 우선순위 형태)
    PriorityQueue<Integer> priorityMinQueue = new PriorityQueue<Integer>();
    //최댓값을 계산하는 최대힙(생성 파라미터 값에 반대reverse 추가)
    PriorityQueue<Integer> priorityMaxQueue = new PriorityQueue<Integer>(Collections.reverseOrder());

    //Operation의 값을 가져와 계산
    for(String operation : operations) {
      //기본값  ' '으로 구분
      StringTokenizer st = new StringTokenizer(operation);
      String oper = st.nextToken();
      int number = Integer.parseInt(st.nextToken());
      System.out.println("oper : " + oper);
      System.out.println("number : " + number);

      //빈 큐에 삭제요청시 연산 무시
      if(priorityMinQueue.size() < 1 && oper.equals("D")) continue;

      //I인 경우 각 힙에 연산 시행
      else if(oper.equals("I")) {
        priorityMaxQueue.add(number);
        priorityMinQueue.add(number);
      }
      //큐가 비어있지 않고 삭제 요청인 경우 각각의 힙 삭제연산 시행
      else if(oper.equals("D")) {
        if(number == 1) {
          priorityMinQueue.remove(priorityMaxQueue.peek());
          priorityMaxQueue.remove();
        }
        else {
          priorityMaxQueue.remove(priorityMinQueue.peek());
          priorityMinQueue.remove();
        }
      }
    }
    System.out.println("최댓값 : " + priorityMaxQueue.peek());
    System.out.println("최솟값 : " + priorityMinQueue.peek());
    //3항 연산자로 값이 비어있을 경우 0 리턴
    return new int[]{priorityMaxQueue.size() ==0 ? 0 : priorityMaxQueue.peek(), priorityMinQueue.size() ==0 ? 0 : priorityMinQueue.peek()};
  }
  public static void main(String[] args) {
    /*
     * - operation                                                    
     * - return 
     * ["I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"]
     * [0,0]
     * 
     * ["I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"]
     * [333, -45]
     */

    String[] operations = {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"};
    DualPriorityQueue dualPriorityQueue = new DualPriorityQueue();
    IntStream.of(dualPriorityQueue.solution(operations)).forEach(e->System.out.printf("%d ",e));
  }
}
