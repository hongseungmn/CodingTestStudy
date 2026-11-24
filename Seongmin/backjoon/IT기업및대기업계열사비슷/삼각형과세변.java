package backjoon.IT기업및대기업계열사비슷;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class 삼각형과세변 {
  public static void main(String[] args) {
    // int[][] array = {
    //   {7, 7, 7},
    //   {6, 5, 4},
    //   {3, 2, 5},
    //   {6, 2, 6},
    //   {0, 0, 0}
    // };

    Scanner sc = new Scanner(System.in);

    List<int[]> list = new ArrayList<>();

    while (true) {
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        // 종료 조건
        if (a == 0 && b == 0 && c == 0) {
            break;
        }

        list.add(new int[]{a, b, c});
    }

    // List → 2차원 배열 변환
    int[][] array = new int[list.size()][3];
    for (int i = 0; i < list.size(); i++) {
        array[i] = list.get(i);
    }

    

    for(int[] arr : array) {
      if(arr[0] == 0 && arr[1] == 0 && arr[2] == 0) return;
      else {
        //int 정렬 수행
        Arrays.sort(arr);
        if(arr[0] + arr[1] <= arr[2]) System.out.println("Invalid");
        else if(arr[0] == arr[1] && arr[1] == arr[2]) System.out.println("Equilateral");
        else if(arr[0] == arr[1] || arr[1] == arr[2]) System.out.println("Isosceles");
        else System.out.println("Scalene");
      }
    }
  }
}
