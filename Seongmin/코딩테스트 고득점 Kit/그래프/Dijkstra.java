import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class Node {
  int idx;
  int cost;

  Node(int idx, int cost) {
    this.idx = idx;
    this.cost = cost;
  }
}

public class Dijkstra {

  public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 노드와 간선의 개수
		int V = sc.nextInt();
		int E = sc.nextInt();
		// 출발지점
		int start = sc.nextInt();

    //1. 인접리스트를 이용한 그래프 초기화
    List<List<Node>> graph = new ArrayList<>();

    for(int i = 0; i< V + 1; i++) {
      graph.add(new ArrayList<>());
    }

    //그래프에 값을 넣는다
    for(int i =0; i< E; i++) {
      //a로부터 b로 가는 값은 cost이다
      int a = sc.nextInt();
      int b = sc.nextInt();
      int cost = sc.nextInt();

      graph.get(a).add(new Node(b, cost));
    }

    //2. 방문 여부를 확인할 boolean 배열, start 노드부터 end 노드까지의 최소 거리를 저장할 배열
    boolean[] visited = new boolean[V + 1];
    int[] dist = new int[V + 1];

    //3. 최소 거리 정보를 담을 배열을 초기화한다
    for(int i=0; i< V + 1; i++) {
      //출발 지점 외 나머지 지점까지의 최소비용은 최대로 지정해둔다
      dist[i] = Integer.MAX_VALUE;
    }

    //출발지점의 비용은 o으로 시작한다
    dist[start] = 0;

    //4. 다익스트라 알고리즘을 진행한다
    //모든 노드를 방문하면 종료하기 때문에, 노드의 개수 만큼만 반복을 수행
    for(int i=0; i < V; i++) {
      //4-1. 현재 거리 비용 중 최소인 지점을 선택한다
      int nodeValue = Integer.MAX_VALUE;
      //해당 노드의 인덱스(번호)
      int nodeIndex = 0;
      //인덱스 0은 생각 x
      for(int j=1; j < V + 1; j++) {
        //해당 노드를 방문하지 않았고, 현재 모든 거리비용 중 최솟값을 찾는다
        if(!visited[j] && dist[j] < nodeValue) {
          nodeValue = dist[j];
          nodeIndex = j;
        }
      }
      //최종 선택된 노드를 방문처리 한다
      visited[nodeIndex] = true;

      //4-2. 해당 지점을 기준으로 인접 노드의 최소 거리값을 갱신한다
      for(int j=0; j<graph.get(nodeIndex).size(); j++) {
        //인접 노드를 선택한다.
        Node adjNode = graph.get(nodeIndex).get(j);
        //인접 노드가 현재 가지는 최소 비용과 
        //현재 선택된 노드의 값 + 현재 노드에서 인접 노드로 가는 값을 비교해서 더 작은 값으로 갱신한다
        if(dist[adjNode.idx] > dist[nodeIndex] + adjNode.cost) {
          dist[adjNode.idx] = dist[nodeIndex] + adjNode.cost;
        }
      }
    }
  }
}