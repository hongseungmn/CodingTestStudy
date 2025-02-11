package algorithm.array;

import java.util.Arrays;
import java.util.Collections;

public class ReverseArray {
  /**
   * 기본적 접근 방식
   * - 원래 배열과 같은 크기의 임시 배열을 생성한다
   * - 원래 배열의 모든 요소를 임시 배열의 역순으로 복사한다
   * - 마지막으로 임시 배열의 모든 요소를 원래 배열로 다시 복사한다
   */
  public static void basic(int[] arr) {
    //임시 배열 생성
    int n = arr.length; 
    int[] temp = new int[n];

    //원래 배열의 요소를 역순으로 temp에 복사
    for(int i=0; i<n; i++) {
      temp[n-i-1] = arr[i];
    }

    //요소를 원래 배열로 다시 복사
    for(int i=0; i<n; i++) {
      arr[i] = temp[i];
    }
  }

  /**
   * 투 포인터 사용 
   * - 두개의 포인터를 유지하고 left가 배열의 시작, right가 배열의 끝
   * - 왼쪽 포인터가 오른쪽 포인터보다 작은 동안, 이 두 위치의 요소를 교환한다.
   * - 각 교환 후, 왼쪽 포인터를 증가시키고 오른쪽 포인터를 감소시켜 배열의 중앙으로 이동
   */
  public static void twoPointer(int[] arr) {
    int left = 0;
    int right = arr.length -1;

    while(left < right) {
      int temp = arr[left];
      arr[left] = arr[right];
      arr[right] = temp;
      //왼쪽 포인터 증가
      left++;
      //오른쪽 포인터 감소
      right--;
    }
  }

  /**
   * 전반부 반복
   * - 배열의 전반부를 반복하고 각 요소를 끝의 해당 요소와 교환하는 것
   * - 전반부를 반복하는 동안 인덱스 i의 모든 요소는 인덱스(n-i-1)의 요소와 교환
   */
  public static void headSwiper(int[] arr) {
    int n = arr.length;

    for(int i=0; i < n/2; i++) {
      int temp = arr[i];
      arr[i] = arr[n-i-1];
      arr[n-i-1] = temp;
    }
  }

  /**
   * 재귀 사용
   * - 첫 번째와 마지막 요소를 바꾼다
   * - 남은 하위 배열로 함수를 재귀적으로 호출한다
   */
  public static void recursive(int[] arr) {
    int n = arr.length;
    recursiveArray(arr, 0, n-1);
  }

  public static void recursiveArray(int[] arr, int l, int r) {
    if(l >= r) return;

    int temp = arr[l];
    arr[l] = arr[r];
    arr[r] = temp;

    recursiveArray(arr, l+1, r-1);
  }

  /**
   * 내장 함수를 사용한다
   */
  public static void builtInFunction(int[] arr) {
        // int[] -> Integer[]로 변환
        Integer[] arrInteger = Arrays.stream(arr)
                                      .boxed()
                                      .toArray(Integer[]::new);

        // 배열 뒤집기
        Collections.reverse(Arrays.asList(arrInteger));

        // Integer[] -> int[]로 변환
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arrInteger[i];  // 원본 배열에 뒤집힌 값을 다시 넣기
        }
  }

  public static void main(String[] args) {
    int[] arr = { 1, 4, 3, 2, 6, 5 };

    builtInFunction(arr);

    for (int i = 0; i < arr.length; i++) 
        System.out.print(arr[i] + " ");
  }
}
