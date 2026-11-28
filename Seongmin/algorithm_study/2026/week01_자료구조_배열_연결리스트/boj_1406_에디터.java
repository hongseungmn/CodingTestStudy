package algorithm_study._2026.week01_자료구조_배열_연결리스트;

/**
 * [백준 1406 - 에디터] (Silver 2)
 * https://www.acmicpc.net/problem/1406
 * 
 * [문제 설명]
 * 간단한 텍스트 에디터를 구현하려고 한다. 커서는 문장의 맨 앞, 맨 뒤, 또는 중간에 위치할 수 있다.
 * 명령어:
 * - L: 커서를 왼쪽으로 한 칸 옮김 (맨 앞이면 무시)
 * - D: 커서를 오른쪽으로 한 칸 옮김 (맨 뒤면 무시)
 * - B: 커서 왼쪽의 문자를 삭제 (맨 앞이면 무시)
 * - P $: 문자 $를 커서 왼쪽에 추가
 * 
 * [제한사항 & 힌트]
 * - 문자열 길이 N, 명령어 개수 M <= 500,000
 * - ArrayList나 일반 LinkedList에서 임의 인덱스 삽입/삭제를 하면 O(N)으로 전체 O(N * M) -> 반드시 시간 초과!
 * - 힌트: 커서를 기준으로 왼쪽과 오른쪽을 나눌 수 있는 구조를 고민해보세요.
 */
public class boj_1406_에디터 {

    /**
     * [TODO] 직접 구현해보세요!
     * @param initial 초기 문자열 (소문자, 길이 최대 10만)
     * @param commands 수행할 명령어 배열 (길이 최대 50만)
     * @return 모든 명령어를 수행한 후의 문자열
     */
    public static String solve(String initial, String[] commands) {
        // TODO: 여기에 코드를 작성하세요.
        return "";
    }

    // ==========================================
    // 🧪 셀프 테스트 실행기
    // ==========================================
    public static void main(String[] args) {
        System.out.println("========== [백준 1406 - 에디터 테스트] ==========");

        TestCase[] testCases = {
            new TestCase("abcd", new String[]{"P x", "L", "P y"}, "abcdyx"),
            new TestCase("abc", new String[]{"L", "L", "L", "L", "L", "P x"}, "xabc"),
            new TestCase("dmih", new String[]{"B", "B", "P x", "L", "B", "B", "B", "P y", "D", "D", "P z"}, "yxz")
        };

        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];
            String actual = solve(tc.initial, tc.commands);
            boolean passed = actual.equals(tc.expected);

            if (passed) {
                System.out.printf("  ✅ Case %d 통과 | 초기: \"%s\" -> 결과: \"%s\"%n",
                        i + 1, tc.initial, actual);
            } else {
                System.out.printf("  ❌ Case %d 실패 | 기대: \"%s\", 실제: \"%s\"%n",
                        i + 1, tc.expected, actual);
            }
        }
    }

    static class TestCase {
        String initial;
        String[] commands;
        String expected;

        TestCase(String initial, String[] commands, String expected) {
            this.initial = initial;
            this.commands = commands;
            this.expected = expected;
        }
    }
}
