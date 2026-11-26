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
     * [원리]
     * - 여는 괄호 '('를 만나면 스택에 push
     * - 닫는 괄호 ')'를 만나면 스택에서 pop하여 가장 최근의 '('와 상쇄
     * - 만약 닫는 괄호인데 스택이 비어있다면 false
     * - 모든 순회가 끝났을 때 스택이 완전히 비어있어야 true
     * 
     * 시간 복잡도: O(N) - 문자열을 1회 순회
     * 공간 복잡도: O(N) - 최악의 경우 '('만 N개 들어와 스택에 저장
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
     * [원리]
     * - 스택에 실제로 문자를 넣는 대신, 스택의 크기(size)만 숫자로 센다.
     * - '(' 이면 count++
     * - ')' 이면 count--
     * - 도중에 count가 음수가 되면 즉시 false (닫는 괄호가 더 먼저 나옴)
     * - 순회 후 count == 0 이어야 true
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
}
