/*
 * 순위
 * https://school.programmers.co.kr/learn/courses/30/lessons/49191
 */


 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.HashSet;
 import java.util.LinkedList;
 import java.util.List;
 import java.util.Map;
 import java.util.Queue;
 import java.util.Set;
 import java.util.Stack;
 import java.util.stream.Stream;
 
 public class Grade {
   
   int answer = 0;
   List<Node> nodes = new ArrayList<>();
 
   public int solution(int n, int[][] results) {
     for(int i =0; i<n; i++) {
       Node node = new Node(i+1);
       nodes.add(node);
     }
     
     for(int i=0; i<results.length; i++) {
       int to = results[i][0];
       int from = results[i][1];
       
       Node toNode = nodes.get(to-1);
       Node fromNode = nodes.get(from -1);
       
       toNode.addLink(fromNode);
           
     }
     
     
     for(Node root : nodes) {
       //모든 방문 기록 지우기
       for(Node e : nodes) e.visited = false;
       if(bfs(root,n)) answer++;
     }
     
 //		bfs(nodes.get(4), n);
     
     
     
         return answer;
     }
   
   boolean bfs(Node root, int n) {
     root.visit();
     
     String asc = "방문 경로 : ";
     int ascCnt = 0;
     
     String desc ="방문 경로 : ";
     int descCnt = 0;
     
     
     if(!root.adjcentNodeASC.isEmpty()) {
       Queue<Node> queue = new LinkedList<Node>();
       queue.add(root);
       
       while(!queue.isEmpty()) {
         Node e = queue.poll();
         e.visit();
         asc += e.idx + "->";
         
         for(Node r : e.adjcentNodeASC) {
           if(!r.visited) {
             ascCnt += 1;
             queue.add(r);
           }
         }
         
       }
     }
     
     if(!root.adjcentNodeDESC.isEmpty()) {
       Queue<Node> queue = new LinkedList<Node>();
       queue.add(root);
       
       while(!queue.isEmpty()) {
         Node e = queue.poll();
         e.visit();
         desc += e.idx + "->";
         
         for(Node r : e.adjcentNodeDESC) {
           if(!r.visited) {
             descCnt += 1;
             queue.add(r);
           }
         }
       }
     }
     
 //		System.out.println("asc : "+asc);
 //		System.out.println("desc : "+desc);
     int visitedCount = 0;
     for(Node e : nodes) {
       if(e.visited) visitedCount++;
     }
     System.out.println("return :" + (n == visitedCount ? true : false));
     return n == visitedCount ? true : false;
   }
   
   
   class Node {
     int depth;
     boolean visited;
     int idx;
     LinkedList<Node> adjcentNodeASC;
     LinkedList<Node> adjcentNodeDESC;
     
     Node(int idx) {
       this.depth = 0;
       this.visited = false;
       this.idx = idx;
       this.adjcentNodeASC = new LinkedList<>();
       this.adjcentNodeDESC = new LinkedList<>();
     }
     
     
     /**
      * 대상 노드가 현재 노드와의 관계 링크 생성
      * @param from 대상 노드
      */
     void addLink(Node from) {
       this.adjcentNodeASC.add(from);
       from.adjcentNodeDESC.add(this);
     }
 
     
     void visit() {
       this.visited = true;
 //			System.out.println("IDX : " + this.idx + " 노드를 방문했습니다.");
 //			System.out.print("해당 노드보다 큰 노드: ");
 //			this.adjcentNodeDESC.stream()
 //		    					.map(element -> element.idx)
 //		    						.forEach(idx -> System.out.print(idx + " "));
 //			System.out.println();
 //			
 //			System.out.print("해당 노드보다 작은 노드: ");
 //			this.adjcentNodeASC.stream()
 //		    					.map(element -> element.idx)
 //		    						.forEach(idx -> System.out.print(idx + " "));
 //			System.out.println();
     }
   }
 
     public static void main(String[] args) {
  
       /*
        * 			n		results										return
           5		[[4, 3], [4, 2], [3, 2], [1, 2], [2, 5]]	2
        */
       
       int n = 5;
       int[][] results = new int[][] {{4,3},{4,2},{3,2},{1,2},{2,5}};
       
       Grade solution = new Grade();
       int result = solution.solution(n, results);
       System.out.println("answer : " + result);
     }
 }
 
