package algorithm_study._2026.week01_자료구조_배열_연결리스트;

import java.util.ArrayList;
import java.util.List;

/**
 * [백준 1158 - 요세푸스 문제] (Silver 4)
 * https://www.acmicpc.net/problem/1158
 * 
 * [문제 설명]
 * 1번부터 N번까지 N명의 사람이 원을 이루면서 앉아있고, 양의 정수 K(<= N)가 주어진다.
 * 순서대로 K번째 사람을 제거한다. 한 사람이 제거되면 남은 사람들로 이루어진 원을 따라 이 과정을 계속해 나간다.
 * N명의 사람이 모두 제거될 때까지 계속된다. 제거되는 순서를 (N, K)-요세푸스 순열이라고 한다.
 * 
 * [예시]
 * N = 7, K = 3 -> <3, 6, 2, 7, 5, 1, 4>
 */
public class boj_1158_요세푸스문제 {

  /**
   * [TODO] 직접 구현해보세요!
   * 
   * @param n 사람의 수 (1 <= N <= 5,000)
   * @param k 제거할 순번 (1 <= K <= N)
   * @return 제거되는 순서 리스트
   */
  public static List<Integer> solve(int n, int k) {
    // List 생성
    List<Integer> circle = new ArrayList<>();

    List<Integer> answer = new ArrayList<>();

    for (int i = 1; i <= n; i++) {
      circle.add(i); 
    }

    int removeIndex = k - 1;
    while(!circle.isEmpty()) {
      System.out.println("제거할 인덱스 before: " + removeIndex);
      removeIndex = removeIndex > circle.size() - 1 ? 0 : removeIndex;
      if(removeIndex < 0) removeIndex = 0;
      System.out.println("제거할 인덱스 : " + removeIndex);
      int remove = circle.remove(removeIndex);
      answer.add(remove);

      for(int e : circle) System.out.printf("%d ", e);
      System.out.println();
      removeIndex = removeIndex + k - 1;

      System.out.println("제거할 인덱스 after : " + removeIndex);
    }

    return answer;
  }

  // ==========================================
  // 🧪 자체 테스트 실행기
  // ==========================================
  public static void main(String[] args) {
    System.out.println("========== [백준 1158 - 요세푸스 문제 테스트] ==========");

    TestCase[] testCases = {
        new TestCase(7, 3, List.of(3, 6, 2, 7, 5, 1, 4)),
        new TestCase(1, 1, List.of(1)),
        new TestCase(5, 2, List.of(2, 4, 1, 5, 3))
    };

    for (int i = 0; i < testCases.length; i++) {
      try {
        TestCase tc = testCases[i];
        List<Integer> actual = solve(tc.n, tc.k);
        boolean passed = actual.equals(tc.expected);

        if (passed) {
          System.out.printf("  ✅ Case %d 통과 | N=%d, K=%d -> 결과: %s%n",
              i + 1, tc.n, tc.k, actual);
        } else {
          System.out.printf("  ❌ Case %d 실패 | 기대: %s, 실제: %s%n",
              i + 1, tc.expected, actual);
        }
      } catch (Exception e) {
        System.out.printf("  ⚠️  Case %d 실패 에러 발생 : %s%n",
              i + 1, e.getMessage());
      }
    }
  }

  static class TestCase {
    int n;
    int k;
    List<Integer> expected;

    TestCase(int n, int k, List<Integer> expected) {
      this.n = n;
      this.k = k;
      this.expected = expected;
    }
  }
}
