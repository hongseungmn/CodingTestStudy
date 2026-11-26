package algorithm_study.스택_큐_덱_02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class 올바른괄호Test {

    private final 올바른괄호 solver = new 올바른괄호();

    @ParameterizedTest(name = "[{index}] input=\"{0}\" -> expected={1}")
    @CsvSource({
        "()(), true",
        "(())__REPLACE__, true", // (())()
        ")()(, false",
        "(()(, false",
        "((((((((((, false",
        "))))))))))), false",
        "()()((())), true"
    })
    @DisplayName("기본 스택 풀이 테스트")
    void testStackSolution(String input, boolean expected) {
        String actualInput = input.replace("__REPLACE__", "()");
        boolean result = solver.solution(actualInput);
        assertEquals(expected, result);
    }

    @ParameterizedTest(name = "[{index}] input=\"{0}\" -> expected={1}")
    @CsvSource({
        "()(), true",
        "(())__REPLACE__, true",
        ")()(, false",
        "(()(, false",
        "((((((((((, false",
        "))))))))))), false",
        "()()((())), true"
    })
    @DisplayName("공간 최적화 카운터 풀이 테스트")
    void testOptimizedSolution(String input, boolean expected) {
        String actualInput = input.replace("__REPLACE__", "()");
        boolean result = solver.solutionOptimized(actualInput);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("극한의 엣지 케이스: 길이가 최대(100,000)인 경우 시간 및 정상 동작 검증")
    void testLargeInput() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 50_000; i++) sb.append('(');
        for (int i = 0; i < 50_000; i++) sb.append(')');
        String largeInput = sb.toString();

        assertTrue(solver.solution(largeInput));
        assertTrue(solver.solutionOptimized(largeInput));
    }
}
