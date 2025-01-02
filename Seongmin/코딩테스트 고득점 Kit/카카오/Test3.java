import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Test3 {
  public int solution(int []A, int []B) {
      int answer = 0;
      List<Integer> arrA = new ArrayList<>();
      List<Integer> arrB = new ArrayList<>();
      Arrays.stream(A).forEach(e -> arrA.add(e));
      Arrays.stream(B).forEach(e -> arrB.add(e));

      Collections.sort(arrA);
      Collections.sort(arrB, new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
          return o2 - o1;
        }
      });

      for(int i=0; i< A.length; i++) {
        answer += arrA.get(i) * arrB.get(i);
      }

      return answer;
  }
  public static void main(String[] args) {
    Test3 test3 = new Test3();
    /*
    A	B	answer
    [1, 4, 2]	[5, 4, 4]	29
    [1,2]	[3,4]	10
     */

    // int[] arr1 = {1, 4, 2};
    // int[] arr2 = {5, 4, 4};

    int[] arr1 = {1, 2};
    int[] arr2 = {3, 4};

    int answer = test3.solution(arr1, arr2);

    System.out.println("정답은 : " + answer);
  }
}
