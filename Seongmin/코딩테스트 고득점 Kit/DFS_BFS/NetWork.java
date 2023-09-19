import java.util.LinkedList;
import java.util.Queue;
/*
 * 네트워크
 * https://school.programmers.co.kr/learn/courses/30/lessons/43162
 * 네트워크란 컴퓨터 상호 간에 정보를 교환할 수 있도록 연결된 형태를 의미합니다. 이때 만들어지는 네트워크의 개수를 구하시오
 */

class NetWork {
  Node[] nodes; //네트워크 노드들을 저장할 배열 변수
  class Node {
    int data; //단순 구분용 데이터
    boolean visited; //방문 여부 확인용
    LinkedList<Node> adjacent; //근접 노드 정보를 저장할 배열
    Node(int data) {
      this.data = data;
      this.visited = false;
      adjacent = new LinkedList<>();
    }
    
    void addLink(Node toNode , Node atNode) { //노드를 연결하는 함수 -> 양방향 연결
      if(!toNode.adjacent.contains(atNode)) {
        toNode.adjacent.add(atNode);
      }
      if(atNode.adjacent.contains(toNode)) {
        atNode.adjacent.add(toNode);
      }
    }
  }

  int bfs(int start) { //bfs 수행하는 함수
    Node root = nodes[start];
    if(root.visited == true) { //이미 방문한 루트 노드일 경우 네트워크를 증가시키지 않는다
      return 0;
    }
    Queue<Node> queue = new LinkedList<Node>();
    queue.add(root);
    root.visited = true;
    while(!queue.isEmpty()) {
      Node r = queue.poll();
      for(Node n : r.adjacent) {
        if(n.visited == false) {
          n.visited = true;
          queue.add(n);
        }
      }
      visit(r);
    }
    return 1; //모든 방문이 종료되면 해당 네트워크 1을 리턴
  }

  private void visit(Node r) { //방문시 단순 출력용 함수
    System.out.println("해당 노드를 방문했습니다 : "+r.data); 
  }


  public int solution(int n, int[][] computers) {
    int answer = 0;
    nodes = new Node[computers.length];
    for(int i=0; i< computers.length; i++) {
      nodes[i] = new Node(i);
    }
    for(int i=0; i< computers.length;i++) { //순회를 하며 각 배열의 정보를 토대로 링크 생성
      for(int j=0; j<computers[i].length;j++) {
        if(i==j || computers[i][j] == 0){
          continue;
        }
        else if(computers[i][j] == 1) {
          nodes[i].addLink(nodes[i],nodes[j]);
        }
      }
    }

    for(int i=0;i<computers.length;i++) { //모든 정점에서 bfs를 수행하고 결과값을 누적
      answer += bfs(i);
    }
    System.out.println("정답은 : "+answer);
    return answer;
  }
}