/*
 * 게임 맵 최단거리
 * https://school.programmers.co.kr/learn/courses/30/lessons/1844
 */
import java.util.LinkedList;
import java.util.Queue;

public class ShortestWayGame {
  int[][] maps; // 크기를 변경한 maps를 저장할 변수
  Node[][] nodes; //노드들을 저장할 배열

  //노드 클래스
  class Node {
    int col, row; //노드의 위치
    int cost; //비용(최단거리를 구하기 위함)
    LinkedList<Node> adjacent; //근접 노드정보들을 저장할 LinkedList
    boolean marked; //방문 여부를 표시
    Node(int col, int row) {
      this.col = col;
      this.row = row;
      this.marked = false;
      adjacent = new LinkedList<Node>();
    }
  }

  //노드를 연결하는 링크를 만드는 함수(양방향)
  void addEdge(int[] p1, int[] p2) {
    Node n1 = nodes[p1[0]][p1[1]];
    Node n2 = nodes[p2[0]][p2[1]];
    if(!n1.adjacent.contains(n2)) {
      n1.adjacent.add(n2);
    }
    if(!n2.adjacent.contains(n1)) {
      n2.adjacent.add(n1);
    }
  }
  //최단 거리는 bfs로 해당 노드에 최초 도착하게 되면 최단 거리가 된다
  int bfs() {
    Node root = nodes[0][0];
    Queue<Node> queue = new LinkedList<Node>();
    queue.add(root);
    int cost =-1; //최단 거리를 계산
    root.marked = true; //방문 시에 true로 변경
    while(!queue.isEmpty()) {
      Node r = queue.poll();
      if(r.adjacent.size()>=1) {
      }
      for(Node n : r.adjacent) {
        if(n.marked == false) {
          n.marked = true;
          n.cost = r.cost+1; //인접 노드로 이동하면서 cost 1씩 증가(후에 최솟값을 구할 변수)
          queue.add(n);
        }
      }
      if(r.col == nodes.length-2 && r.row == nodes.length-2 ) {
        System.out.println("목적지에 도착했습니다(bfs)");
        System.out.println("cost : "+(r.cost+1));
        cost = r.cost+1;
        break;
      }
    }
    System.out.println("목적지에 도달할 수 없습니다");
    return cost;
  }

  public int solution(int [][] maps) {
    this.maps = new int[maps.length+1][maps.length+1]; // 마지막 라인 때문에 1씩 추가

    nodes = new Node[maps.length+1][maps.length+1];// 마지막 라인 때문에 1씩 추가
    //크기를 늘린 map을 class의 map 변수에 옮겨 담는다(복사)
    for(int i=0; i<maps.length;i++) {
      for(int j=0; j<maps.length;j++){
        nodes[i][j] = new Node(i,j);
        this.maps[i][j] = maps[i][j];
      }
    }
    int answer = 0;
    for(int i=0; i< nodes.length-1; i++) {
      for(int j=0; j<nodes.length-1; j++) {
        if((maps[i][j] == 1)) {//해당 노드와 오른쪽, 하단의 노드와 비교를 해 둘 다 1인 경우 양방향 링크 생성
          if(maps[i+1][j] == 1) {
            this.addEdge(new int[]{i,j}, new int[]{i+1,j});
            System.out.printf("생성되는 링크 : (%d,%d)->(%d,%d)\n",i,j,i+1,j);
          }
          if(maps[i][j+1] == 1) {
            this.addEdge(new int[]{i,j}, new int[]{i,j+1});
            System.out.printf("생성되는 링크 : (%d,%d)->(%d,%d)\n",i,j,i,j+1);
          }
        }
      }
    }
    answer = bfs();
    return answer;
  }
}
