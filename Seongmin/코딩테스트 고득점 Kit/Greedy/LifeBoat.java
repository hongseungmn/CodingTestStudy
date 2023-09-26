/*
 * 구명보트
 */

import java.util.Arrays;
import java.util.stream.IntStream;

public class LifeBoat {
  public int solution(int[] people, int limit) {
    int answer = 0;
    Arrays.sort(people); // 오름차순
    int firstIndex = 0;
    int lastIndex = people.length-1;
    while(true) {
      if(lastIndex == firstIndex) {
        answer++;
        break;
      }
      else if(firstIndex > lastIndex) {
        break;
      }
      if(people[firstIndex] + people[lastIndex] <= limit) {
        answer++;
        lastIndex--;
        firstIndex++;
        System.out.println("두명 태운 경우");
      }
      else {
        lastIndex--;
        answer++;
      }
    }
    IntStream.of(people).forEach((x)->System.out.println(x));
    return answer;
  }

  public static void main(String[] args) {
    /*
      people	          limit	    return
      [70, 50, 80, 50]	100	      3
      [70, 80, 50]	    100	      3
     */
    int[] people = {70,50,80,50,60,80,30};
    int limit = 100;
    LifeBoat lifeBoat = new LifeBoat();
    System.out.println("정답은 :" +lifeBoat.solution(people, limit));
  }
}
