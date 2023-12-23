
/*
 * 피로도
 * https://school.programmers.co.kr/learn/courses/30/lessons/87946
 * 홍성민
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Fatigue {
  public int answer;
  public boolean[] visited;

  public int solution(int k, int[][] dungeons) {

    //방문배열 초기화
    visited = new boolean[dungeons.length];
    //dfs 실행
    dfs(0, k, dungeons);

    //결과 반환
    return answer;
  }

  /**
   * @param depth 깊이
   * @param k 현재 피로도
   * @param dungeons 던전들
   */
  public void dfs(int depth, int k, int[][] dungeons) {
    for(int i=0; i<dungeons.length; i++) {
      //방문하지 않고 k(현재 피로도가 최소 필요 피로도보다 크거나 같은 경우)
      if(!visited[i] && dungeons[i][0] <= k) {
        visited[i] = true; //방문 처리
        dfs(depth + 1, k - dungeons[i][1], dungeons);//재귀 호출
        visited[i] = false; //방문 초기화
      }
    }
    answer = Math.max(answer, depth);
  }




  public static void main(String[] args) {
    /*
    k	          dungeons	                        result
    80	        [[80,20],[50,40],[30,10]]	        3
    */
    Fatigue fatigue = new Fatigue();
    int k = 80;
    int[][] dungeons = {{80,20},{50,40},{30,10}};
    System.out.println("정답은 : "+ fatigue.solution(k,dungeons));
  }
}
