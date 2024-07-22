/*
 * 정수 삼각형
 * https://school.programmers.co.kr/learn/courses/30/lessons/43105
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IntegerTriangle {
    public int solution(int[][] triangle) {
        int answer = 0;
        // for(int i=0;i<triangle.length; i++) {
        // for(int j=0; j<triangle[i].length;j++) {
        // System.out.printf("%d ", triangle[i][j]);
        // }
        // System.out.println();
        // }

        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                // 처음, 마지막 부분
                // System.out.println("좌표 :" + i + " " + j);
                if (j == 0)
                    triangle[i][j] = triangle[i][j] + triangle[i - 1][0];
                else if (j == triangle[i].length - 1)
                    triangle[i][j] = triangle[i][j] + triangle[i - 1][triangle[i - 1].length - 1];

                // 중간 부분
                else {
                    triangle[i][j] += Math.max(triangle[i - 1][j - 1], triangle[i - 1][j]);
                }

            }
        }

        for (int j = 0; j < triangle[triangle.length - 1].length; j++) {
            answer = Math.max(answer, triangle[triangle.length - 1][j]);
        }
        return answer;
    }

    public static void main(String[] args) {
        /*
         * triangle result
         * [[7], [3, 8], [8, 1, 0], [2, 7, 4, 4], [4, 5, 2, 6, 5]] 30
         */
        IntegerTriangle solution = new IntegerTriangle();
        int[][] triangle = new int[][] { { 7 }, { 3, 8 }, { 8, 1, 0 }, { 2, 7, 4, 4 }, { 4, 5, 2, 6, 5 } };
        int answer = solution.solution(triangle);
        System.out.println("정답은 : " + answer);
    }
}
