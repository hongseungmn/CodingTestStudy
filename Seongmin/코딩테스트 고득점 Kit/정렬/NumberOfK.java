/*
    K번째 수
    https://school.programmers.co.kr/learn/courses/30/lessons/42748?language=python3
    배열 array의 i번째 숫자부터 j번째 숫자까지 자르고 정렬했을 때, k번째에 있는 수를 구하려 합니다.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberOfK {
    
    public static int[] solution(int[] array, int[][] commands) {
        List<Integer> answerList = new ArrayList<>();
        for (int[] command : commands) {
            int[] subArray = Arrays.copyOfRange(array, command[0] - 1, command[1]);
            Arrays.sort(subArray);
            answerList.add(subArray[command[2] - 1]);
        }
        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }
}






