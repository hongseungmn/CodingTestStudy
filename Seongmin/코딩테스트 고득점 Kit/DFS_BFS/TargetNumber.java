/*
 * 타겟 넘버
 * https://school.programmers.co.kr/learn/courses/30/lessons/43165
 */

//홍성민



import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

  
public class TargetNumber {
  //재귀함수
  int answer;
  public int solution(int[] numbers, int target) {
    answer = 0;
    dfs(0, 0, numbers, target);
    return answer;
  }
  void dfs(int n, int sum, int[] numbers, int target) {
    if(n == numbers.length) {
      if(sum == target) 
        answer++;
      return;
    }
    dfs(n + 1, sum + numbers[n], numbers, target);
    dfs(n + 1, sum - numbers[n], numbers, target);
  }
}