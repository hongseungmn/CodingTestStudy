
/*
 * 섬 연결하기
 * https://school.programmers.co.kr/learn/courses/30/lessons/42861
 */
//홍성민

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

//이 문제는 프림 알고리즘을 사용해 구현하였다
//처음 문제 해결 방법이 익숙하지 않아 찾아보았다

public class ConnectIsland {

  class Edge implements Comparable<Edge> {
    int w;
    int cost;
    Edge(int w, int cost) {
      this.w = w;
      this.cost = cost;
    }
    @Override
    public int compareTo(Edge o) {
      return this.cost - o.cost;
    }
  }

  List<Edge>[] graph;

  public int prim(int start, int n) {
    boolean[] visit = new boolean[n + 1];
    PriorityQueue<Edge> pq = new PriorityQueue<>();
    pq.offer(new Edge(start, 0));// 시작 엣지를 삽입

    int total = 0;
    while(!pq.isEmpty()) {
      Edge edge = pq.poll(); //큐가 빈 상태가 아니라면 poll
      int v = edge.w; //연결된 노드를 v에 저장
      int cost = edge.cost;

      if(visit[v]) continue; //방문을 이미 했다면 건너뛴다
      visit[v] = true; //해당 엣지를 방문한 것을 변경
      total += cost; //가중치값 누적
      for(Edge e : graph[v]) {
        if(!visit[e.w]) {
          pq.add(e); //방문하지 않은 엣지를 큐에 삽입
        }
      }
    }
    System.out.println("가중치 : "+total);
    return total;
  }
  
  public int solution(int n, int[][] costs) {
    int answer = 0;
    graph = new ArrayList[n+1];
    for(int i=0; i< graph.length; i++) graph[i] = new ArrayList<>();

    for(int i = 0; i < costs.length; i++) {
      int v = costs[i][0];
      int w = costs[i][1];
      int cost = costs[i][2];
      graph[v].add(new Edge(w, cost));
      graph[w].add(new Edge(v, cost));
    }

    answer = prim(0, n);
    return answer;
  }

  /*
      n	          costs	                                              return
      4	          [[0,1,1],[0,2,2],[1,2,5],[1,3,1],[2,3,8]]	          4
      5           [[0, 1, 1], [3, 4, 1], [1, 2, 2], [2, 3, 4]]        8
      5           [[0, 1, 5], [1, 2, 3], [2, 3, 3], [3, 1, 2],        15
                  [3, 0, 4], [2, 4, 6], [4, 0, 7]]

  */
  public static void main(String[] args) {
    ConnectIsland connectIsland = new ConnectIsland();
    int n = 5;
    int[][] costs = {{0,1,5},{1,2,3},{2,3,3},{3,1,2},{3,0,4},{2,4,6},{4,0,7}};
    System.out.println("정답은 : "+connectIsland.solution(n, costs));
  }
}
