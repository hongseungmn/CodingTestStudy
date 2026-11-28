package algorithm_study.스택_큐_덱_02;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * [프로그래머스 Lv.2 - 올바른 괄호]
 * 
 * 문제 설명:
 * 괄호가 바르게 짝지어졌다는 것은 '(' 문자로 열렸으면 반드시 짝지어서 ')' 문자로 닫혀야 한다는 뜻입니다.
 * 문자열 s가 주어졌을 때, 올바른 괄호이면 true를, 올바르지 않으면 false를 return 하는 solution 함수를 완성해 주세요.
 * 
 * 제한사항:
 * - 문자열 s의 길이: 100,000 이하의 자연수 (O(N) 또는 O(N log N)으로 해결해야 함)
 * - 문자열 s는 '(' 또는 ')' 로만 이루어져 있습니다.
 */
public class 올바른괄호 {

    /**
     * 접근 1. 스택(ArrayDeque)을 활용한 정석 풀이
     * 
     * 시간 복잡도: O(N) - 문자열 1회 순회
     * 공간 복잡도: O(N) - 최악의 경우 '('만 N개 저장
     */
    public boolean solution(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }

        return stack.isEmpty();
    }

    /**
     * 접근 2. 공간 최적화 풀이 (스택 대신 카운터 변수 활용)
     * 
     * 시간 복잡도: O(N)
     * 공간 복잡도: O(1) - 추가 메모리를 거의 쓰지 않음 (최적화!)
     */
    public boolean solutionOptimized(String s) {
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                count++;
            } else {
                count--;
                if (count < 0) {
                    return false;
                }
            }
        }

        return count == 0;
    }

    // ==========================================
    // 🧪 셀프 테스트 실행기 (JUnit 없이 바로 실행)
    // ==========================================
    public static void main(String[] args) {
        올바른괄호 solver = new 올바른괄호();

        TestCase[] testCases = {
            new TestCase("()()", true),
            new TestCase("(())()", true),
            new TestCase(")()(", false),
            new TestCase("(()(", false),
            new TestCase("((((((((((", false),
            new TestCase("))))))))))", false),
            new TestCase("()()((()))", true)
        };

        System.out.println("========== [기본 스택 풀이 테스트] ==========");
        runTests(testCases, solver::solution);

        System.out.println("\n======= [공간 최적화 카운터 풀이 테스트] =======");
        runTests(testCases, solver::solutionOptimized);

        // 엣지 케이스: N = 100,000 대용량 데이터 검증
        System.out.println("\n========== [대용량 데이터(N=100,000) 테스트] ==========");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 50_000; i++) sb.append('(');
        for (int i = 0; i < 50_000; i++) sb.append(')');
        String largeInput = sb.toString();

        long start = System.currentTimeMillis();
        boolean res1 = solver.solution(largeInput);
        long end1 = System.currentTimeMillis();

        boolean res2 = solver.solutionOptimized(largeInput);
        long end2 = System.currentTimeMillis();

        System.out.printf("스택 풀이 결과: %b (%d ms)%n", res1, (end1 - start));
        System.out.printf("최적화 풀이 결과: %b (%d ms)%n", res2, (end2 - end1));
    }

    @FunctionalInterface
    interface SolutionFunction {
        boolean apply(String s);
    }

    private static void runTests(TestCase[] testCases, SolutionFunction func) {
        int passCount = 0;
        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];
            boolean actual = func.apply(tc.input);
            boolean isPassed = (actual == tc.expected);

            if (isPassed) {
                passCount++;
                System.out.printf("  ✅ Case %d 통과 | 입력: \"%s\" -> 결과: %b%n", i + 1, tc.input, actual);
            } else {
                System.out.printf("  ❌ Case %d 실패 | 입력: \"%s\" (기대: %b, 실제: %b)%n", i + 1, tc.input, tc.expected, actual);
            }
        }
        System.out.printf("  👉 결과: %d/%d 통과%n", passCount, testCases.length);
    }

    static class TestCase {
        String input;
        boolean expected;

        TestCase(String input, boolean expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}
