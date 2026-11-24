package 코딩테스트고득점Kit.동적계획법;

public class HouseRober {

  static int[] DP;

  public int solution(int[] nums) {
    DP = new int[nums.length];

    if(nums.length == 1) return nums[0];
    if(nums.length == 2) return Math.max(nums[0], nums[1]);

    DP[0] = nums[0];
    DP[1] = Math.max(nums[0], nums[1]);

    //인접한 두 집을 도둑질 할 수 없다

    for(int i=2; i<nums.length; i++) {
      DP[i] = Math.max(DP[i-1], DP[i-2] + nums[i]);
    }


    return DP[nums.length - 1];
  }
}
