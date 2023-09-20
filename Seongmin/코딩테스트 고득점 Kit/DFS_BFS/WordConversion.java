/*
  단어 변환
 * https://school.programmers.co.kr/learn/courses/30/lessons/43163
 * 두 개의 단어 begin, target과 단어의 집합 words가 있습니다. 아래와 같은 규칙을 이용하여 begin에서 target으로 변환하는 가장 짧은 변환 과정을 찾으려고 합니다.
 */
//홍성민

import java.util.LinkedList;
import java.util.Queue;

public class WordConversion {
  Node[] nodes; //단어 정보를 담고 있는 노드배열
  class Node { //단어 정보를 담고 있는 노드 클래스
    String word; //노드에 있는 단어
    boolean visited; //방문 여부를 저장할 변수
    int cost; //최단거리를 측정할 cost변수
    LinkedList<Node> adjacent; //인접 노드 정보를 저장할 LinkedList

    Node(String word) { //노드 생성자 및 변수 초기화
      this.word = word;
      this.visited = false;
      this.cost = 0;
      this.adjacent = new LinkedList<>();
    }
  }

  void addLink(Node toNode, Node atNode) { //노드들 간의 링크를 연결하는 메소드(양방향)
    if(!toNode.adjacent.contains(atNode)) {
      toNode.adjacent.add(atNode);
    }
    if(!atNode.adjacent.contains(toNode)) {
      atNode.adjacent.add(toNode);
    }
  }
  boolean compareWord(Node n1, Node n2) { //두 노드간의 단어를 비교 -> 한 단어가 다른지 판단한다
    String s1 = n1.word;
    String s2 = n2.word;
    int diffCount = 0;
    for(int i=0; i < s1.length(); i++) {
      if(s1.charAt(i) != s2.charAt(i)) {
        diffCount++;
      }
    }
    return diffCount == 1 ? true : false;
  }
  int bfs(String target) { //bfs를 수행
    Node root = nodes[0];
    root.visited = true;
    Queue<Node> queue = new LinkedList<Node>();
    queue.add(root);
    while(!queue.isEmpty()) {
      Node r = queue.poll();
      for(Node n : r.adjacent) {
        if(n.visited == false) {
          n.visited = true;
          queue.add(n);
          n.cost = r.cost+1;
        }
      }
      visit(r);
      if(r.word.equals(target)) {
        System.out.println("정답을 발견했습니다");
        return r.cost;
      }
    }
    return 0;
  }

  void visit(Node r) {
    System.out.println("방문 과정");
    System.out.printf("( "+r.word +" )" + "->");
  }

  public int solution(String begin, String target, String[] words) {
    int answer = 0;
    nodes = new Node[words.length+1];
    nodes[0] = new Node(begin); //시작 노드 설정
    for(int i=0; i<words.length;i++) {
      nodes[i+1] = new Node(words[i]);
    }
    //한 단어가 다른 것끼리 링크 생성
    for(int i=0;i<nodes.length-1;i++) {
      for(int j=i+1;j<nodes.length;j++) {
        if(compareWord(nodes[i],nodes[j])) { //단어들을 비교한 후 조건에 충족할 때만 링크를 생성해 연결한다
          System.out.println("연결된 링크 단어 : "+nodes[i].word + ", "+ nodes[j].word);
          addLink(nodes[i],nodes[j]);
        }
      }
    }
    answer = bfs(target);
    return answer;
  }  
}
