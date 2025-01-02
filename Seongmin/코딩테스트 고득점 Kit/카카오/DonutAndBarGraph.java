/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/258711
 * 도넛과 막대 그래프
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DonutAndBarGraph {

  Map<Integer, Node> nodeMap = new HashMap<>();
  List<Node> addedNodeList = new ArrayList<>();

  int donut = 0;
  int bar = 0;
  int eight = 0;
  int currentAddedIndex = 0;
  int[] answer = new int[4];

  LinkedList<Node> removedNodes = new LinkedList<>();

  class Node {
      int num;
      boolean visited;
      LinkedList<Node> outAdjacent;
      LinkedList<Node> inAdjacent;

      public Node(int num) {
          this.num = num;
          this.visited = false;
          this.outAdjacent = new LinkedList<Node>();
          this.inAdjacent = new LinkedList<Node>();
      }

      void addLink(Node to) {
          if(!this.outAdjacent.contains(to)) this.outAdjacent.add(to);
          if(!to.inAdjacent.contains(this)) to.inAdjacent.add(this);
      }

      void visit() {
          this.visited = true;
          System.out.println(this.num + " 노드를 방문");
          System.out.println("해당 노드의 inAdjacent : " + this.inAdjacent.stream().map(e -> e.num + " ").collect(Collectors.joining()));
          System.out.println("해당 노드의 outAdjacent : " + this.outAdjacent.stream().map(e -> e.num + " ").collect(Collectors.joining()));
      }
  }

  public void removeLink(Node to) {
      List<Node> toRemove = new ArrayList<>(to.outAdjacent);
      for(Node node : toRemove) {
          node.inAdjacent.remove(to);
          to.outAdjacent.remove(node);
          removedNodes.add(node);
      }
  }

  public void undoLink(Node to) {
      List<Node> toUndo = new ArrayList<>(removedNodes);
      for(Node node : toUndo) {
          node.inAdjacent.add(to);
          to.outAdjacent.add(node);
      }
      removedNodes.clear();
  }

  public void findAddedNode() {
      for(int nodeIdx : nodeMap.keySet()) {
          Node node = nodeMap.get(nodeIdx);
          if(node.inAdjacent.size() == 0 && node.outAdjacent.size() != 0) {
              addedNodeList.add(node);
              System.out.println("노드 후보군 : " + node.num);
          }
      }
  }

  public void findGraph() {
      for(Node addedNode : addedNodeList) {
          donut = 0;
          bar = 0 ;
          eight = 0;
          
          System.out.println(addedNode.num + " 추가된 정점일 때 =============================== rootNode 설정 시작");
          List<Node> rootList = new ArrayList<>();
          for(Node root : addedNode.outAdjacent) {
              if(!rootList.contains(root)) {
                  rootList.add(root);
                  System.out.println("추가된 rootNode : " + root.num);
              }
          }

          removeLink(addedNode);
          System.out.println(addedNode.num + " 추가된 정점일 때 ================================ rootNode 설정 끝");
          int totalWeight = 0;
          for(Node root: rootList) {
              totalWeight += graph(root);
          }

          undoLink(addedNode);
          System.out.println("도넛 모양 그래프 수 : " + donut);
          System.out.println("바 모양 그래프 수 : " + bar);
          System.out.println("8자 모양 그래프 수 : " + eight);
          
          System.out.println("totalWeight : "+ totalWeight);
          System.out.println("nodeMap.size() : "+ nodeMap.size());
          if(totalWeight == nodeMap.size() - 1) {
              answer[0] = addedNode.num;
              answer[1] = donut;
              answer[2] = bar;
              answer[3] = eight;
              return;
          }
      }
  }

  public int graph(Node root) {
      Queue<Node> queue = new LinkedList<Node>();
      int weight = 0;
      int edgeNode = 0;
      
      boolean barFlag = false;
      queue.add(root);
      while(!queue.isEmpty()) {
          Node e = queue.poll();
          if(!e.visited) {
              weight++;
              edgeNode += e.outAdjacent.size() + e.inAdjacent.size();
              e.visit();
              if(e.outAdjacent.isEmpty() || e.inAdjacent.isEmpty()) barFlag = true;
              for(Node j : e.outAdjacent) {
                  if(!j.visited) queue.add(j);
              }
              for(Node j : e.inAdjacent) {
                  if(!j.visited) queue.add(j);
              }
          }
          
      }

      if(barFlag) {
          bar++;
      } else {
          System.out.println("weight : " + weight);
          System.out.println("edgeNode : " + edgeNode);
          if(weight == edgeNode / 2) donut++; 
          else eight++;
      }

      System.out.println("총 가중치 : " + weight);
      System.out.println("총 간선 수 : " + edgeNode);
      return weight;
  }

  public int[] solution(int[][] edges) {
      

      for(int i=0; i<edges.length; i++) {
          int fromIdx = edges[i][0];
          int toIdx = edges[i][1];

          Node fromNode;

          if(!nodeMap.containsKey(fromIdx)) {
              fromNode = new Node(fromIdx);
              nodeMap.put(fromIdx, fromNode);
          } else {
              fromNode = nodeMap.get(fromIdx);
          }

          Node toNode;

          if(!nodeMap.containsKey(toIdx)) {
              toNode = new Node(toIdx);
              nodeMap.put(toIdx, toNode);
          } else {
              toNode = nodeMap.get(toIdx);
          }

          fromNode.addLink(toNode);
      }

      findAddedNode();
      findGraph();

      return answer;
  }
  
  public static void main(String[] args) {
      /*
       * [[2, 3], [4, 3], [1, 1], [2, 1]]
       * [[4, 11], [1, 12], [8, 3], [12, 7], [4, 2], [7, 11], [4, 8], [9, 6], [10, 11], [6, 10], [3, 5], [11, 1], [5, 3], [11, 9], [3, 8]]
       */
      int[][] edges1 = {{2, 3}, {4, 3}, {1, 1}, {2, 1}};
      int[][] edges2 = {{4, 7}, {1, 12}, {8, 3}, {12, 7}, {4, 2}, {7, 11}, {4, 8}, {9, 6}, {10, 11}, {6, 10}, {3, 5}, {11, 1}, {5, 3}, {11, 9}, {3, 8}, {2, 13}};

      DonutAndBarGraph solution = new DonutAndBarGraph();
      int[] answer = solution.solution(edges1);
      System.out.println("정답은 : "+Arrays.toString(answer));
  }
}
