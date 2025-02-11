package algorithm.array;

import java.util.ArrayList;

public class GeneratingAllSubarrays {
    //Prints all subarrays in arr[0..n-1]

    //반복적 접근 방법
    static void subArray(ArrayList<Integer> arr) {
        int n = arr.size();

        //시작지점 선택
        for (int i = 0; i < n; i++) {
          
            //끝지점 선택
            for (int j = i; j < n; j++) {
              
                //위 두 지점 사이의 값을 출력한다
                for (int k = i; k <= j; k++) {
                    System.out.print(arr.get(k) + " ");
                }
                System.out.println();
            }
        }
    }

    //재귀적 접근 방법
    static void printSubArrays(int[] arr, int start, int end) {
      if(end == arr.length) return;
      else if(start > end) printSubArrays(arr, 0, end + 1);
      else {
        System.out.print("[");
        for(int i=start; i<end; i++) {
          System.out.print(arr[i] + ", ");
        }
        System.out.println(arr[end] + "]");
        printSubArrays(arr, start + 1, end);
      }

      return;
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);
        System.out.println("All Non-empty Subarrays:");
        subArray(arr);

        int[] array = {1, 2, 3};
        printSubArrays(array, 0, 0);

    }
}
