/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/250136
 * 석유 시추
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class OilDrilling {

  public static List<List<Boolean>> convertToList(int[][] array) {
    return Arrays.stream(array)
        .map(row -> Arrays
            .stream(row)
            .boxed()
            .map(value -> {
              return false;
            })
            .collect(Collectors.toList()))
        .collect(Collectors.toList());

  }

  public int[][] expandArray(int[][] original) {
    int originalRows = original.length;
    int originalCols = original[0].length;

    // 새로운 배열 만들기
    int newRows = originalRows + 2;
    int newCols = originalCols + 2;

    int[][] expanded = new int[newRows][newCols];

    // 원본 배열을 새 배열의 중앙에 복사
    for (int i = 0; i < originalRows; i++) {
      for (int j = 0; j < originalCols; j++) {
        expanded[i + 1][j + 1] = original[i][j];
      }
    }

    return expanded;
  }

  public void printArray(int[][] array) {
    for (int[] row : array) {
      for (int value : row) {
        System.out.print(value + " ");
      }
      System.out.println();
    }
  }

  // List<List<Boolean>>를 0과 1로 변환하여 출력
  public void printBooleanList(List<List<Boolean>> list) {
    for (List<Boolean> row : list) {
      for (Boolean value : row) {
        System.out.print(value ? 1 + " " : 0 + " ");
      }
      System.out.println();
    }
  }

  public int solution(int[][] land) {
    int maxAmount = 0;
    int[][] moves = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };
    int[][] expandedLand = expandArray(land);

    List<List<Boolean>> landList = convertToList(expandedLand);

    printArray(expandedLand);

    for (int col = 1; col < expandedLand[0].length - 1; col++) {
      landList = convertToList(expandedLand);
      int amount = 0;
      for (int row = 1; row < expandedLand.length - 1; row++) {
        // for 문을 돌면서 1을 만나면(석유 있음) 해당 석유의 크기를 구하고 리스트의 원소를 true(이미 발견)으로 바꿈
        if (expandedLand[row][col] == 1) {
          // 만약 해당 석유를 이미 발견했다면
          if (landList.get(row).get(col)) {
            continue;
          }
          // 그렇지 않으면 시추 시행
          else {
            // 그렇지 않으면 시추 시행
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[] { row, col });
            landList.get(row).set(col, true); // 발견 시작점 표시

            while (!queue.isEmpty()) {
              int[] loc = queue.poll();
              amount++;
              for (int[] move : moves) {
                int newRow = loc[0] + move[0];
                int newCol = loc[1] + move[1];
                if (expandedLand[newRow][newCol] == 1 && !landList.get(newRow).get(newCol)) {
                  queue.add(new int[] { newRow, newCol });
                  landList.get(newRow).set(newCol, true); // 새 위치 표시
                }
              }
            }
            System.out.println(String.format("[%d, %d] 에서 시추 발견, 시추량 : %d", row, col, amount));
          }
        }
      }
      System.out.println(String.format("[%d] 총 시추량 : %d", col, amount));

      maxAmount = Math.max(maxAmount, amount);
    }
    // printBooleanList(landList);

    return maxAmount;
  }

  public static void main(String[] args) {

    int[][] land = { { 0, 0, 0, 1, 1, 1, 0, 0 }, { 0, 0, 0, 0, 1, 1, 0, 0 }, { 1, 1, 0, 0, 0, 1, 1, 0 },
        { 1, 1, 1, 0, 0, 0, 0, 0 }, { 1, 1, 1, 0, 0, 0, 1, 1 } };
    // int[][] land = {{1, 0, 1, 0, 1, 1}, {1, 0, 1, 0, 0, 0}, {1, 0, 1, 0, 0, 1},
    // {1, 0, 0, 1, 0, 0}, {1, 0, 0, 1, 0, 1}, {1, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1,
    // 1}};

    OilDrilling solution = new OilDrilling();
    int answer = solution.solution(land);
    System.out.println("정답은 : " + answer);
  }
}
