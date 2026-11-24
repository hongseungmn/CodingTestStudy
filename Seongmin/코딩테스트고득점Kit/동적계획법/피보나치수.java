package 코딩테스트고득점Kit.동적계획법;

public class 피보나치수 {

  /*
  DP, Dynamic Programming이란 복잡한 문제를 간단한 여러 개의 문제로 나누어 푸는 방법을 말한다
  분할정복과 비슷하나 dp에서는 작은 문제들이 반복되며, 그것을 이용해 문제를 풀어나간다는 점이다

  dp를 사용할 수 있는 가정
  1. 큰 문제를 작은 문제로 분할 가능해야 하며,
  2. 작은 문제에서 구한 정답이 그것을 포함하는 큰 문제에서도 동일해야 한다
  
  dp는 top-down 방식과 bottom-up 두 방식으로 나뉘는데 top-down 방식은 재귀함수를 사용하며 bottom-up은 반복문을 사용한다
  */

  //F(0) = 0, F(1) = 1
  //F(n) = F(n-1) + F(n-2)
  public int solution(int n) {
    return (int) fibo(n);
  }

  static long[] arr = new long[100000];

  public long fibo(int num) {
    if(num < 1) return 0;
    if(num == 1) return 1;
    if(num == 2) return 1;

    if(arr[num] != 0) return arr[num];

    return arr[num] = (fibo(num - 1) + fibo(num - 2) ) % 1234567;
  }

  /**
   * dp를 사용하지 않은 피보나치 계산법
   * @param n
   * @return
   */
  public int solution1(int n) {
    if(n == 1) {
      return 1;
    }
    if(n == 2) {
      return 1;
    }
    int result = solution1(n-1) + solution1(n-2);

    return result;
  }

  static int[] solTopdownArr = new int[100];
  public int solutionTopDown(int n) {
    //첫번째 수열인 경우
    if(n == 1) {
      return 1;
    }
    //두번째 수열인 경우
    if(n == 2) {
      return 1;
    }
    //배열에 저장된 값이 있을 시 저장된 값 반환
    if(solTopdownArr[n] != 0) {
      return solTopdownArr[n];
    }
    //저장된 값이 없는 경우, 값을 저장 후 반환
    return solTopdownArr[n] = solutionTopDown(n-1) + solutionTopDown(n-2);
  }

  static int[] solBottomupArr = new int[100];
  public int solutionBottomUp(int n) {
    //첫번째, 두번째 수열에 1 저장
    solBottomupArr[0] = 1;
    solBottomupArr[1] = 1;

    //반복문을 돌며 3번째 수열부터 차례로 값을 채워넣는다
    for(int i=2; i<n; i++) {
      solBottomupArr[i] = solBottomupArr[i-1] + solBottomupArr[i-2];
    }
    return solBottomupArr[n - 1];
  }
}
