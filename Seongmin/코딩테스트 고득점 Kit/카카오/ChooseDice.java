/*
 * 주사위 고르기
 * https://school.programmers.co.kr/learn/courses/30/lessons/258709
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ChooseDice {
    public int[] solution(int[][] dice) {
        int n = dice.length;
        int r = n / 2;
        //0 ~ n-1까지 배열 생성 단순화 : IntStream.range(0, n).toArray()
        Set<Set<Integer>> combinations = generateCombinations(IntStream.range(0, n).toArray(), r);

        System.out.println("Total number of combinations: " + combinations.size());

        int maxWinCount = 0;
        Set<Integer> bestCombinationSet = null;

        for (Set<Integer> selectedIndexes : combinations) {
            int[][] diceA = new int[r][6];
            int[][] diceB = new int[n - r][6];

            // 선택된 인덱스를 사용하여 diceA 설정
            int aIndex = 0;
            for (int i = 0; i < n; i++) {
                if (selectedIndexes.contains(i)) {
                    diceA[aIndex++] = dice[i];
                }
            }

            // 나머지 인덱스를 사용하여 diceB 설정
            int bIndex = 0;
            for (int i = 0; i < n; i++) {
                if (!selectedIndexes.contains(i)) {
                    diceB[bIndex++] = dice[i];
                }
            }

            // 결과 출력
            System.out.println("Current combination (0-based indices): " + selectedIndexes);
            System.out.println("diceA:");
            printArray(diceA);

            System.out.println("diceB:");
            printArray(diceB);

            // 각 주사위 세트의 합의 빈도 정보를 계산합니다.
            Map<Integer, Integer> sumMapA = calculateSumFrequencies(diceA);
            Map<Integer, Integer> sumMapB = calculateSumFrequencies(diceB);

            System.out.println("Sum map A: " + sumMapA);
            System.out.println("Sum map B: " + sumMapB);

            // diceA가 diceB를 이기는 경우의 수를 계산합니다.
            int winCount = 0;
            for (Map.Entry<Integer, Integer> entryA : sumMapA.entrySet()) {
                int sumA = entryA.getKey();
                int freqA = entryA.getValue();

                for (Map.Entry<Integer, Integer> entryB : sumMapB.entrySet()) {
                    int sumB = entryB.getKey();
                    int freqB = entryB.getValue();

                    if (sumA > sumB) {
                        winCount += freqA * freqB;
                    }
                }
            }

            System.out.println("Win count for this combination: " + winCount);

            // 가장 많이 이긴 조합을 업데이트합니다.
            if (winCount > maxWinCount) {
                maxWinCount = winCount;
                bestCombinationSet = new HashSet<>(selectedIndexes);
            }
        }

        // 1-based 인덱스로 변환하여 반환합니다.
        if (bestCombinationSet != null) {
            int[] result = bestCombinationSet.stream().map(i -> i + 1).mapToInt(Integer::intValue).toArray();
            System.out.println("Best combination indices (1-based): " + Arrays.toString(result));
            return result;
        } else {
            System.out.println("No combination found.");
            return new int[0];  // No combination found
        }
    }

    /**
     * 조합을 생성하는 메서드
     * @param arr 주사위의 인덱스를 가지고 있는 배열로 인덱스를 이용해 조합을 생성
     * @param r 조합 개수 nCr 에서 r. 총 원소의 절반을 조합하므로 r = arr / 2
     * @return 생성된 조합 Set
     */
    public static Set<Set<Integer>> generateCombinations(int[] arr, int r) {
        Set<Set<Integer>> result = new HashSet<>();
        generateCombinationsHelper(arr, new int[r], 0, 0, r, result);
        return result;
    }

    /**
     * 재귀적으로 주어진 배열 `arr`에서 길이가 `r`인 모든 조합을 생성하여 `result`에 추가
     * @param arr 주사위의 인덱스를 가지고 있는 배열
     * @param data 현재 조합을 임시로 담고 있는 배열
     * @param start 시작 인덱스
     * @param depth 현재 조합의 깊이 (현재까지 선택된 원소의 수)
     * @param r 조합의 길이
     * @param result 모든 조합을 저장할 결과 집합
     */
    private static void generateCombinationsHelper(int[] arr, int[] data, int start, int depth, int r, Set<Set<Integer>> result) {
        // 현재 조합의 길이가 r에 도달하면, 조합을 결과 집합에 추가
        if (depth == r) {
            Set<Integer> combination = new HashSet<>();
            for (int i : data) {
                combination.add(i);
            }
            result.add(combination);
            return;
        }
        // 가능한 모든 시작 인덱스에서 조합을 생성
        for (int i = start; i <= arr.length - (r - depth); i++) {
            // 현재 위치의 원소를 data에 추가
            data[depth] = arr[i];
            // 다음 원소를 선택하기 위해 재귀 호출
            generateCombinationsHelper(arr, data, i + 1, depth + 1, r, result);
        }
    }

    /**
     * 주어진 주사위 배열에 대해 각 합의 빈도 정보를 계산합니다.
     *
     * @param diceArray 주사위의 값들을 가지고 있는 2차원 배열
     * @return 각 합과 그 빈도를 저장하는 맵
     */
    public static Map<Integer, Integer> calculateSumFrequencies(int[][] diceArray) {
        Map<Integer, Integer> sumMap = new HashMap<>();
        calculateSumFrequenciesHelper(diceArray, 0, 0, 1, sumMap);
        return sumMap;
    }

    /**
     * 재귀적으로 주어진 주사위 배열에서 가능한 모든 합의 빈도 정보를 계산하여 맵에 저장합니다.
     *
     * @param diceArray 주사위의 값들을 가지고 있는 2차원 배열
     * @param diceIndex 현재 처리 중인 주사위의 인덱스
     * @param currentSum 현재까지의 합
     * @param currentCount 현재 합의 발생 빈도
     * @param sumMap 각 합과 그 빈도를 저장하는 맵
     */
    private static void calculateSumFrequenciesHelper(int[][] diceArray, int diceIndex, int currentSum, int currentCount, Map<Integer, Integer> sumMap) {
        // 모든 주사위에 대해 처리가 끝나면, 합의 빈도를 맵에 추가
        if (diceIndex == diceArray.length) {
            sumMap.put(currentSum, sumMap.getOrDefault(currentSum, 0) + currentCount);
            return;
        }
        // 현재 주사위의 각 면에 대해 재귀 호출
        for (int i = 0; i < diceArray[diceIndex].length; i++) {
            calculateSumFrequenciesHelper(diceArray, diceIndex + 1, currentSum + diceArray[diceIndex][i], currentCount, sumMap);
        }
    }

    //출력문
    public static void printArray(int[][] array) {
        for (int[] row : array) {
            System.out.print("[ ");
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println("]");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        ChooseDice solution = new ChooseDice();
        
        int[][] dice = {{1,2,3,4,5,6},{3,3,3,3,4,4},{1,3,3,4,4,4},{1,1,4,4,5,5}};
        //System.out.println(combination(3, 2));
        
        int[] bestCombination = solution.solution(dice);
        System.out.println("Best combination indices (1-based): " + Arrays.toString(bestCombination));
    }
}