/*
 * 요격 시스템
 * https://school.programmers.co.kr/learn/courses/30/lessons/181188
 */

import java.util.Arrays;
import java.util.Comparator;

public class InterceptSystem {

    public int solution(int[][] targets) {
        // 정렬된 targets 배열을 기준으로 중복된 구간을 세는 로직
        Arrays.sort(targets, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) {
                    return o1[0] - o2[0]; // 끝 점이 같다면 시작 점 기준으로 정렬
                }
                return o1[1] - o2[1]; // 끝 점 기준으로 정렬
            }
        });

        int count = 0;
        int end = 0; // 마지막 구간의 끝 점

        for (int[] target : targets) {
            if (target[0] >= end) {
                // 현재 구간의 시작 점이 마지막 구간의 끝 점 이후라면
                count++;
                end = target[1]; // 현재 구간의 끝 점을 마지막 끝 점으로 업데이트
            }
        }

        System.out.println("count : " + count);

        return count;
    }

    public static void main(String[] args) {
        InterceptSystem solution = new InterceptSystem();
        int[][] targets = {{4,5},{4,8},{10,14},{11,13},{5,12},{3,7},{1,4},{12,16}};
        solution.solution(targets);
    }
}