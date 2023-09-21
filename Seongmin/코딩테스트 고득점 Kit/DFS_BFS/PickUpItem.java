/*
 * 아이템 줍기
 * https://school.programmers.co.kr/learn/courses/30/lessons/87694
 * 아이템을 줍기위해 캐릭터가 이동해야하는 최단거리 구하기
 */
//홍성민

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PickUpItem {
  int maps[][] = new int[105][105]; //maps 데이터를 저장할 2차원 배열 -> 최대 좌표가 50 -> 확장을 위해 *2 -> 추가 여유공간위해 105로 설정
  Node[][] nodes = new Node[105][105]; //maps에 대응하는 2차원 노드배열 -> 최대 좌표가 50 -> 확장을 위해 *2 -> 추가 여유공간위해 105로 설정
  class Node{ //노드 클래스 생성
    int cost; //최단거리를 구하기 위한 변수 
    int x; //노드의 x좌표를 저장할 변수
    int y; //노드의 y좌표를 저장할 변수
    boolean visit; //노드의 방문 여부를 확인할 변수
    LinkedList<Node> adjacent; //노드의 인접 노드 정보를 저장할 LinkedList

    Node(int x,int y) { //Node 생성자로 초기화 및 x,y좌표를 입력받아 저장한다
      this.x = x;
      this.y = y;
      this.cost = 0;
      this.visit = false;
      this.adjacent = new LinkedList<Node>();
    }
  }
  public void addLink(Node atNode, Node toNode) { //노드를 연결하는 함수 (양방향)
    if(!atNode.adjacent.contains(toNode)) {
      atNode.adjacent.add(toNode);
    }
    if(!toNode.adjacent.contains(atNode)) {
      atNode.adjacent.add(toNode);
    }
  }

  
  //사각형 그리기(빈 공간 -> 0, 사각형 테두리 -> 1, 사각형 내부 -> 2)사각형 내부가 2인 것을 이용해 포함된 테두리 구분
  //기존 좌표 *2를 해 테두리길이를 늘린다(확장)
  public void drawRectangle(int[] rectangle) { //입력받은 사각형을 2차원 공간에 정보를 저장한다
    int leftTopX = rectangle[0]*2;
    int leftTopY = rectangle[1]*2;
    int rightBottomX = rectangle[2]*2;
    int rightBottomY = rectangle[3]*2;
    for(int i = leftTopX; i<= rightBottomX;i++) {
      for(int j = leftTopY; j <= rightBottomY; j++) {
        if(i == leftTopX || i == rightBottomX || j == leftTopY || j == rightBottomY) { //map에 정보 저장
          if(maps[i][j] == 2) {
            maps[i][j] = 2;
          }
          else {
            maps[i][j] = 1;
          }
        }
        else {
          maps[i][j] = 2;
        }
      }
    }
  }

  //maps를 토대로 노드를 생성한다
  public void createNode() {
    for(int i=0; i<maps.length;i++) {
      for(int j=0; j<maps[0].length;j++) {
        nodes[i][j] = new Node(i, j);
      }
    }
    for(int i=0; i<maps.length;i++) { //상,하,좌,우 가 연결되어 있을 경우 링크 생성해 연결
      for(int j=0; j<maps[0].length;j++) {
        if(maps[i][j] == 1) {
          if(maps[i+1][j] == 1) {
            addLink(nodes[i][j], nodes[i+1][j]);
          }
          if(maps[i][j+1] == 1) {
            addLink(nodes[i][j], nodes[i][j+1]);
          }
          if(maps[i-1][j] == 1) {
            addLink(nodes[i][j],nodes[i-1][j]);
          }
          if(maps[i][j-1] == 1) {
            addLink(nodes[i][j],nodes[i][j-1]);
          }
        }
      }
    }
  }

  public int bfs(int characterX, int characterY, int itemX, int itemY) { //bfs를 수행 -> 목표지점에 도착하면 return -> bfs는 도착할 때 값이 최단거리가 됨
    Node root = nodes[characterX][characterY];
    Queue<Node> queue = new LinkedList<Node>();
    queue.add(root);
    root.visit = true;
    while(!queue.isEmpty()){
      Node r = queue.poll();
      for(Node n : r.adjacent) {
        if(n.visit == false) {
          n.visit = true;
          n.cost = r.cost+1;
          queue.add(n);
        }
      }
      visit(r);
      if(r.x == itemX && r.y == itemY) {
        System.out.println("아이템을 주웠습니다");
        return r.cost;
      }
    }
    return 0;
  }

  public void visit(Node r) {
    System.out.printf("해당 노드를 방문했습니다 : (%d,%d) cost : %d \n",r.x,r.y,r.cost);
  }


  public void printMaps() { //현재 좌표공간을 출력하는 함수
    for(int i=0; i< maps.length; i++) {
      for(int j=0; j< maps.length; j++) {
        System.out.printf("%d ",maps[i][j]);
      }
      System.out.println();
    }
  }

  public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
    int answer = 0;
    Arrays.stream(maps).forEach(row -> Arrays.setAll(row, i -> 0)); // 각 요소를 0으로 초기화
    for(int i=0;i<rectangle.length;i++) {
      drawRectangle(rectangle[i]); //입력받은 사각형을 그림
    }
    createNode();
    answer = bfs(characterX*2,characterY*2,itemX*2,itemY*2); //확장을 위해 모든 변수를 *2를 한 후 입력
    printMaps();
    return answer/2; //출력은 다시 2로 나눈후 리턴
  }
}
