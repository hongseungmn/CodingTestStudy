package algorithm_study._2026.week01_자료구조_배열_연결리스트;

import java.util.Arrays;

/**
 * [백준 10818 - 최소, 최대] (Bronze 3)
 * https://www.acmicpc.net/problem/10818
 * 
 * [문제 설명]
 * N개의 정수가 주어진다. 이때, 최솟값과 최댓값을 구하는 프로그램을 작성하시오.
 * 
 * [제한사항]
 * - 1 <= N <= 1,000,000 -> O(N)으로 1회 순회하며 해결해야 함
 * - 모든 정수는 -1,000,000보다 크거나 같고, 1,000,000보다 작거나 같은 정수이다.
 */
public class boj_10818_최소최대 {

    /**
     * [TODO] 직접 구현해보세요!
     * @param n 원소의 개수
     * @param arr 크기가 N인 정수 배열
     * @return [최솟값, 최댓값] 크기 2의 배열
     */
    public static int[] solve(int n, int[] arr) {
        // TODO: 여기에 코드를 작성하세요.
        int min = 0;
        int max = 0;

        return new int[]{min, max};
    }

    // ==========================================
    // 🧪 셀프 테스트 실행기
    // ==========================================
    public static void main(String[] args) {
        System.out.println("========== [백준 10818 - 최소, 최대 테스트] ==========");

        TestCase[] testCases = {
            new TestCase(5, new int[]{20, 10, 35, 30, 7}, new int[]{7, 35}),
            new TestCase(1, new int[]{42}, new int[]{42, 42}),
            new TestCase(3, new int[]{-10, -99, -2}, new int[]{-99, -2})
        };

        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];
            int[] actual = solve(tc.n, tc.arr);
            boolean passed = Arrays.equals(actual, tc.expected);

            if (passed) {
                System.out.printf("  ✅ Case %d 통과 | 최소: %d, 최대: %d%n", i + 1, actual[0], actual[1]);
            } else {
                System.out.printf("  ❌ Case %d 실패 | 기대: %s, 실제: %s%n",
                        i + 1, Arrays.toString(tc.expected), Arrays.toString(actual));
            }
        }
    }

    static class TestCase {
        int n;
        int[] arr;
        int[] expected;

        TestCase(int n, int[] arr, int[] expected) {
            this.n = n;
            this.arr = arr;
            this.expected = expected;
        }
    }
}
