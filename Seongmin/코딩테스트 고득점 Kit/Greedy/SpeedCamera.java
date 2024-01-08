/*
 * 단속 카메라
 * https://school.programmers.co.kr/learn/courses/30/lessons/42884
 * 홍성민
 */

import java.util.PriorityQueue;

public class SpeedCamera {
  class Car implements Comparable<Car>{
    int in; //진입시점
    int out; //진출시점
    boolean isChecked; //단속카메라 확인여부
    Car(int in, int out) {
      this.in = in;
      this.out = out;
      isChecked = false;
    }
    @Override //진입시점을 기준으로 비교하기위한 비교인터페이스 구현
    public int compareTo(Car o) {
      return this.in - o.in;
    } 
  }
  public int solution(int[][] routes) {
    int answer = 0;
    PriorityQueue<Car> pq = new PriorityQueue<Car>(); //진입시점순으로 큐에 삽입
    for(int i=0; i<routes.length;i++) {
      pq.offer(new Car(routes[i][0],routes[i][1]));
    }
    while(!pq.isEmpty()) {//큐가 빌 때까지 반복
      Car car = pq.poll();  //진입시점순으로 차량 추출
      int out = car.out;
      while(!pq.isEmpty() && pq.peek().in <=out) { //해당 차량의 진출시점 전까지 해당되는 모든 차량을 큐에서 추출
        if(pq.peek().out < out) out = pq.peek().out; //만약 현재 진입시점의 차량의 진입 진출 사이에 중복된 차량 발생시 진출 시점을 당기기 위한 로직(마지막 케이스)
        pq.poll();
      }
      answer++;
    }
    return answer;
  }
  public static void main(String[] args) {
  /*
    routes	                                                            return
    {{-20,-15}, {-14,-5}, {-18,-13}, {-5,-3}}	                          2

    {{-2,-1}, {1,2},{-3,0}}                                             2

    {{0,0}}                                                             1

    {{0,1}, {0,1}, {1,2}}                                               1

    {{0,1}, {2,3}, {4,5}, {6,7}}                                        4

    {{-20,-15}, {-14,-5}, {-18,-13}, {-5,-3}}                           2

    {{-20,15}, {-14,-5}, {-18,-13}, {-5,-3}}                            2

    {{-20,15}, {-20,-15}, {-14,-5}, {-18,-13}, {-5,-3}}                 2

    {{-7,0}, {-6,-4}, {-5,-3}, {-3,-1}, {-1,4}, {1,2}, {3,4}}           4

    {{-5,-3}, {-4,0}, {-4,-2}, {-1, 2}, {0,3}, {1,5}, {2,4} }           2

    {{0,1}, {1,2}, {2,3} ,{3,4}, {5,4}, {5,6}, {6,7} , {8,7},           
    {8,9} ,{9,10}, {10,11}, {11,12}, {12,13}, {13,14} ,{14,15} }        8

    {{0,12},{1,12},{2,12},{3,12},{5,6},{6,12},{10,12}}                  2

  */
    SpeedCamera speedCamera = new SpeedCamera();
    int[][] routes = 
    {{0,12},{1,12},{2,12},{3,12},{5,6},{6,12},{10,12}} ;                                    
    System.out.println("정답은 : "+speedCamera.solution(routes));

  }
}
