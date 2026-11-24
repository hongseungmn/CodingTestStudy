package 코딩테스트고득점Kit.동적계획법;

public class MaximumSubarray {
  
  //연속된 부분배열의 최대 합
  static int[] DP;
  public int solution(int[] nums) {
    int n = nums.length;
    DP = new int[n];

    DP[0] = nums[0];
    int max = DP[0];

    for(int i=1; i<n; i++) {
      DP[i] = Math.max(DP[i-1] + nums[i], nums[i]);
      
      max = Math.max(max, DP[i]);
    }

    return max;
  }
}
