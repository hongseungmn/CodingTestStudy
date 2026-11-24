package 코딩테스트고득점Kit.동적계획법;

public class 계단오르기 {
  /*
    계단은 한 번에 한 계단씩 또는 두 계단씩 오를 수 있다. 즉, 한 계단을 밟으면서 이어서 다음 계단이나, 다음 다음 계단으로 오를 수 있다.
    연속된 세 개의 계단을 모두 밟아서는 안 된다. 단, 시작점은 계단에 포함되지 않는다.
    마지막 도착 계단은 반드시 밟아야 한다.
  */
  static int[] stairs;
  public int solution(int[] arr) {
    stairs = arr;

    int n = stairs.length;
        
    // 계단이 1개일 경우 예외 처리
    if (n == 1) return stairs[0];
    // 계단이 2개일 경우 (둘 다 밟는 게 무조건 이득)
    if (n == 2) return stairs[0] + stairs[1];

    int[] DP = new int[arr.length];

    //DP[i] = DP[i-2] + stairs[i]; //두 계단 밑에서 바로 올라올 경우
    //DP[i] = DP[i-3] + DP[i-1] + stairs[i]; //두 계단 + 한 계단으로 올라온 경우 (연속된 3개x) 
    // 1. 초기값 설정 (Base Case)
    DP[0] = stairs[0];                                    // 첫 번째 계단
    DP[1] = stairs[0] + stairs[1];                        // 두 번째 계단 (1+2번째 밟기)
    DP[2] = Math.max(stairs[0] + stairs[2], stairs[1] + stairs[2]); // 세 번째 계단
    
    for(int i=3; i<stairs.length; i++) {
      DP[i] = Math.max(DP[i-2] + stairs[i], DP[i-3] + stairs[i-1] + stairs[i]);
    }



    return DP[n-1];
  }
}
