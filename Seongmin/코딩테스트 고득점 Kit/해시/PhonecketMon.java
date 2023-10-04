/*
 * 폰캣몬
 * https//school.programmers.co.kr/learn/courses/30/lessons/1845
 * 
 */
//홍성민

import java.util.HashSet;

class PhonecketMon {

    public int solution(int[] nums) {
    HashSet<Integer> set = new HashSet<Integer>();
    for(int i=0;i<nums.length;i++) {
      set.add(nums[i]);
    }
    if(set.size() >= (nums.length/2)) {
      return nums.length/2;
    }

    return set.size();
    }
}
