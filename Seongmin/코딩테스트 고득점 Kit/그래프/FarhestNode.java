/*
 * 가장 먼 노드
 * https://school.programmers.co.kr/learn/courses/30/lessons/49189
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class FarhestNode {
  List<Node> nodes = new ArrayList<>();
    List<Integer> weights = new ArrayList<>();
    int maxWeight = 0;
    int answer = 0;

    public int solution(int n, int[][] edges) {
        // 그래프 초기화
        for (int i = 0; i < n; i++) {
            nodes.add(new Node(i + 1));
        }

        // 그래프에 간선 추가
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            nodes.get(from - 1).addLink(nodes.get(to - 1));
        }

        // BFS를 사용하여 모든 노드의 최단 경로 계산
        bfs(nodes.get(0)); // 여기서는 첫 번째 노드(노드 1)를 시작으로 설정

        // 모든 노드의 최단 경로 출력
        for (Node node : nodes) {
            System.out.println("Node " + node.nodeIdx + "의 최단 경로: " + node.distance);
            int prev = maxWeight;
            maxWeight = Math.max(node.distance, maxWeight);
            weights.add(node.distance);
        }
       
        answer = (int)weights.stream()
        		.filter(weight -> weight >= maxWeight)
        		.count();
       
       

        return answer; // 실제로는 필요에 따라 반환값 지정
    }

    public void bfs(Node start) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        start.visited = true;
        start.distance = 0;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (Node neighbor : current.adjacents) {
                if (!neighbor.visited) {
                    neighbor.visited = true;
                    neighbor.distance = current.distance + 1;
                    queue.add(neighbor);
                }
            }
        }
    }

    class Node {
        int nodeIdx;
        boolean visited;
        int distance;
        LinkedList<Node> adjacents = new LinkedList<>();

        Node(int idx) {
            this.nodeIdx = idx;
            this.visited = false;
            this.distance = Integer.MAX_VALUE; // 초기에는 최단 경로를 무한대로 설정
        }

        void addLink(Node to) {
            this.adjacents.add(to);
            to.adjacents.add(this);
        }
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] edges = new int[][]{{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
        FarhestNode solution = new FarhestNode();
        solution.solution(n, edges);
    }
}
