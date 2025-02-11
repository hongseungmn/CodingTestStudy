import java.util.LinkedList;

public class SheepsNWolves {
    int N;
    Node[] nodes;
    int maxSheeps;
    
    public int solution(int[] info, int[][] edges) {
        // 초기값 설정
        N = info.length;
        maxSheeps = 0;
        
        // 양과 늑대 노드 생성
        nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            Node node = new Node(i, info[i]);
            nodes[i] = node;
        }
        
        // 간선 연결
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            
            nodes[from].addLink(nodes[to]);
        }
        
        // DFS 탐색 시작 (초기엔 양의 수 1, 늑대 수 0)
        recursive(nodes[0], 1, 0, new boolean[N]);
        
        return maxSheeps;
    }
    
    public void recursive(Node node, int sheeps, int wolves, boolean[] visited) {
        // 양이 늑대보다 적거나 같으면 중단
        if (sheeps <= wolves) return;

        // 최대 양의 수 갱신
        maxSheeps = Math.max(maxSheeps, sheeps);

        // 현재 노드 방문 처리
        visited[node.index] = true;

        // 인접 노드 탐색
        for (Node e : node.adjacent) {
            if (!visited[e.index]) {
                // 방문 배열을 새로 만들지 않고 그대로 전달
                if (e.kind == 0) {
                    recursive(e, sheeps + 1, wolves, visited);
                } else {
                    if (sheeps <= wolves + 1) continue;
                    recursive(e, sheeps, wolves + 1, visited);
                }
            }
        }

        // 백트래킹 - 방문 해제
        visited[node.index] = false;
    }
    
    class Node {
        int kind; // 0: 양, 1: 늑대
        int index;
        LinkedList<Node> adjacent = new LinkedList<Node>();
        
        public Node(int index, int kind) {
            this.index = index;
            this.kind = kind;
        }
        
        public void addLink(Node to) {
            if (!this.adjacent.contains(to)) this.adjacent.add(to);
        }
    }
  
  public static void main(String[] args) {
    /*
     * info	edges	result
[0,0,1,1,1,0,1,0,1,0,1,1]	[[0,1],[1,2],[1,4],[0,8],[8,7],[9,10],[9,11],[4,3],[6,5],[4,6],[8,9]]	5
[0,1,0,1,1,0,1,0,0,1,0]	[[0,1],[0,2],[1,3],[1,4],[2,5],[2,6],[3,7],[4,8],[6,9],[9,10]]	5
     */

    SheepsNWolves sheepsNWolves = new SheepsNWolves();
    int[] info = {0,0,1,1,1,0,1,0,1,0,1,1};
    int[][] edges = {{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}};

    sheepsNWolves.solution(info, edges);

  }
}
