import java.util.*;

public class HateSameNumber {
  public int[] solution(int[] arr) {
    int[] answer;
    Stack<Integer> stack = new Stack<>();
    stack.push(arr[0]);
    for (int i = 1; i < arr.length; i++) {
      int before = stack.pop();
      if (before != arr[i]) {
        stack.push(before);
        stack.push(arr[i]);
      } else
        stack.push(before);
    }
    ArrayList<Integer> list = new ArrayList<>(stack);
    answer = new int[list.size()];
    int index = 0;
    for (Integer item : stack) {
      answer[index] = item;
      index++;
    }
    return answer;
  }
}