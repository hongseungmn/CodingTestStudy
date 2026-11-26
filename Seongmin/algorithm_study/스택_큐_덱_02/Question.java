package algorithm_study.스택_큐_덱_02;

import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.Queue;

public class Question {
  public static void main(String[] args) {
    //Question 1 큐가 비어있을 때 poll, remove 동작
    question_1();
    //Question 2 큐 최대 용량을 초과할 때 add, offer 동작
    quesiton_2();
  }

  public static void question_1() {
    Queue<Integer> queue = new LinkedList<>();
    try {
      queue.poll();
      System.out.println("큐가 비어있을 경우 poll() 실행 성공!!");
    } catch (Exception e) {
      System.out.println(e.getClass());
      System.out.println("큐가 비어있을 경우 poll() 실행시 에러 발생");
    }
    try {
      queue.remove();
      System.out.println("큐가 비어있을 경우 remove() 실행 성공");
    } catch (Exception e) {
      System.out.println("큐가 비어있을 경우 remove() 실행시 에러 발생");
    }   
  }

  public static void quesiton_2() {
    //최대 용량이 3개인 큐
    Queue<Integer> queue = new ArrayBlockingQueue<>(3);

    queue.offer(1);
    queue.offer(2);
    queue.offer(3);

    try {
      queue.add(4);
      System.out.println("큐가 용량이 꽉 찼을 경우 add() 실행 성공!!");
    } catch (Exception e) {
      System.out.println(e.getClass());
      System.out.println("큐가 용량이 꽉 찼을 경우 add() 실행시 에러 발생");
    }

    try {
      queue.offer(4);
      System.out.println("큐가 용량이 꽉 찼을 경우 offer() 실행 성공!!");
    } catch (Exception e) {
      System.out.println(e.getClass());
      System.out.println("큐가 용량이 꽉 찼을 경우 offer() 실행시 에러 발생");
    }
    
  }
}
