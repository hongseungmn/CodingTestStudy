package 코딩테스트고득점Kit.동적계획법;

import java.util.Arrays;

public class CoinChange {
  //amount를 맞추기 위한 coins의 최소 개수
  static int[] DP;
  public int solution(int[] coins, int amount) {
    
    DP = new int[amount + 1];
    
    //초기값 생성
    Arrays.fill(DP, amount + 1);
    DP[0] = 0;

    //점화식 f(n) = f(n-coin) + 1
    for(int i=1; i<=amount; i++) {

      for(int coin : coins) {
        if(i >= coin) {
          DP[i] = Math.min(DP[i], DP[i-coin] + 1);
        }
      }
      
    }

    return DP[amount] > amount ? -1 : DP[amount];
  }
}
