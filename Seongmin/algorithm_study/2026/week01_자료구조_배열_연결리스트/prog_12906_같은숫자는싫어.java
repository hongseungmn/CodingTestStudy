package algorithm_study._2026.week01_자료구조_배열_연결리스트;

import java.util.Arrays;

/**
 * [프로그래머스 Lv.1 - 같은 숫자는 싫어]
 * https://school.programmers.co.kr/learn/courses/30/lessons/12906
 * 
 * [문제 설명]
 * 배열 arr가 주어집니다. 배열 arr의 각 원소는 숫자 0부터 9까지로 이루어져 있습니다.
 * 이때, 배열 arr에서 연속적으로 나타나는 숫자는 하나만 남기고 전부 제거하려고 합니다.
 * 단, 제거된 후 남은 수들을 반환할 때는 배열 arr의 원소들의 순서를 유지해야 합니다.
 * 
 * [제한사항]
 * - 배열 arr의 크기: 1,000,000 이하의 자연수 -> O(N) 필수
 * - 배열 arr의 원소의 크기: 0보다 크거나 같고 9보다 작거나 같은 정수
 */
public class prog_12906_같은숫자는싫어 {

    /**
     * [TODO] 직접 구현해보세요! (프로그래머스 제출용)
     * @param arr 입력 배열 (크기 최대 100만)
     * @return 연속된 중복이 제거된 배열
     */
    public int[] solution(int[] arr) {
        // TODO: 여기에 코드를 작성하세요.
        return new int[]{};
    }

    // ==========================================
    // 🧪 셀프 테스트 실행기
    // ==========================================
    public static void main(String[] args) {
        prog_12906_같은숫자는싫어 solver = new prog_12906_같은숫자는싫어();

        TestCase[] testCases = {
            new TestCase(new int[]{1, 1, 3, 3, 0, 1, 1}, new int[]{1, 3, 0, 1}),
            new TestCase(new int[]{4, 4, 4, 3, 3}, new int[]{4, 3}),
            new TestCase(new int[]{0, 0, 0, 0}, new int[]{0}),
            new TestCase(new int[]{1, 2, 3, 4}, new int[]{1, 2, 3, 4})
        };

        System.out.println("========== [프로그래머스 Lv.1 - 같은 숫자는 싫어 테스트] ==========");
        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];
            int[] actual = solver.solution(tc.input);
            boolean passed = Arrays.equals(actual, tc.expected);

            if (passed) {
                System.out.printf("  ✅ Case %d 통과 | 입력: %s -> 결과: %s%n",
                        i + 1, Arrays.toString(tc.input), Arrays.toString(actual));
            } else {
                System.out.printf("  ❌ Case %d 실패 | 기대: %s, 실제: %s%n",
                        i + 1, Arrays.toString(tc.expected), Arrays.toString(actual));
            }
        }
    }

    static class TestCase {
        int[] input;
        int[] expected;

        TestCase(int[] input, int[] expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}
