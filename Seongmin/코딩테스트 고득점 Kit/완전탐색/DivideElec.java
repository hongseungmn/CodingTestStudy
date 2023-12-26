/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/86971
 * 전력망을 둘로 나누기
 * 홍성민
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DivideElec {
  //타워들을 저장할 리스트
  List<Tower> towerList;
  int answer;

  public class Tower {
    int number;//타워 넘버
    boolean visited; //방문 여부
    LinkedList<Tower> adjacent; //인접노드 조사

    Tower(int number) {
      this.number = number;
      visited = false;
      adjacent = new LinkedList<Tower>();
    }

    /**
     * 전선을 추가하는 메소드(t1 - t2 연결을 추가)
     * @param t1 - 시작 타워
     * @param t2 - 종료 타워
     */
    public void addLink(Tower t1, Tower t2) {
      if(!t1.adjacent.contains(t2)) {
        t1.adjacent.add(t2);
      }
      if(!t2.adjacent.contains(t1)) {
        t2.adjacent.add(t1);
      }
    }

    /**
     * 전선을 제거하는 메소드 (t1 - t2 연결을 제거)
     * @param t1 - 시작 타워
     * @param t2 - 종료 타워
     */
    public void removeLink(Tower t1, Tower t2) {
      if(t1.adjacent.contains(t2)) {
        t1.adjacent.remove(t2);
      }
      if(t2.adjacent.contains(t1)) {
        t2.adjacent.remove(t1);
      }
    }
  }

  /**
   * 너비우선탐색
   * @param start 시작 타워번호
   * @return 두개로 분리된 두 네트워크의 타워 수의 차
   */
  public int bfs(int start) {
    Tower root = towerList.get(start);
    int towerCount = 0;
    if(root.visited == true) {
      return 0;
    }
    Queue<Tower> queue = new LinkedList<Tower>();
    queue.add(root);
    root.visited = true;

    while(!queue.isEmpty()) {
      Tower r = queue.poll();
      for(Tower n : r.adjacent) {
        if(n.visited == false) {
          n.visited = true;
          queue.add(n);
        }
      }
      visit(r);
      towerCount++;
    }
    //방문 흔적 초기화
    for(Tower t : towerList) {
      t.visited = false;
    }
    return Math.abs((towerList.size()-1-towerCount)- towerCount);
  }
  private void visit(Tower r) {
    System.out.println("해당 전선탑을 방문했습니다 : "+r.number);
  }

  public int solution(int n, int[][] wires) {
    towerList = new ArrayList<Tower>();
    int answer = n;
    //n의 개수만큼 타워 생성
    for(int i=0;i<=n;i++) {
      Tower tower = new Tower(i);
      towerList.add(tower);
    }
    //전력망 네트워크 연결
    for(int[] wire : wires) {

      Tower to = towerList.get(wire[0]);
      Tower at = towerList.get(wire[1]);
      //System.out.println("연결된 네트워크 : "+ to.number + " <-> " + at.number);
      to.addLink(to, at);
    }

    //모든 전선에 대해 끊어봄
    for(int[] wire : wires) {
      Tower to = towerList.get(wire[0]);
      Tower at = towerList.get(wire[1]);

      to.removeLink(to, at);
      System.out.println("끊은 전선 : "+wire[0] + ", "+ wire[1]);
      //끊어본 후 BFS 수행
      answer = Math.min(answer,bfs(1));
      //다시 연결해줌
      to.addLink(to, at);
      //System.out.println("다시 연결된 네트워크 : "+ to.number + " <-> " + at.number);
    }

    return answer;
  }


  public static void main(String[] args) {
    DivideElec divideElec = new DivideElec();

    /*
     * int n = 4;
     * int[][] wires = {{1,2},{2,3},{3,4}};
     * 
     * result = 0;
     * 
     * int n = 7;
     * int[][] wires = {{1,2},{2,7},{3,7},{3,4},{4,5},{6,7}};
     * 
     * result = 1;
     * 
     * int n = 9;
     * int[][] wires = {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}};
     * 
     * result = 3;
     */
    int n = 4;
    int[][] wires = {{1,2},{2,3},{3,4}};

    System.out.println("정답은 : "+ divideElec.solution(n, wires));
  }


}
