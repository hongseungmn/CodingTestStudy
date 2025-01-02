import java.util.*;

public class PuzzleGameChallenge {
  public int solution(int[] diffs, int[] times, long limit) {
    int answer = 0;
    int level = 0;

    int maxLevel = Arrays.stream(diffs).max().getAsInt();
    int minLevel = Arrays.stream(diffs).min().getAsInt();
    while (minLevel <= maxLevel) {
      level = maxLevel;
			if(getTime(diffs, times, level) <= limit) {
				maxLevel = maxLevel - 1;
			} else {
				return level + 1;
			}
      //System.out.println("level : " + level);
    }
    return level;
  }

  public long getTime(int[] diffs, int[] times, int level) {
    long time = 0;
    for(int i=0; i<diffs.length; i++) {
      if(diffs[i] <= level) time += times[i];
      else {
        time += (times[i-1] + times[i]) * (diffs[i] - level) + times[i];
      }
    }
    return time;
  }
  public static void main(String[] args) {
    PuzzleGameChallenge puzzleGameChallenge = new PuzzleGameChallenge();
    // diffs	times	limit	result
    // [1, 5, 3]	[2, 4, 7]	30	3
    // [1, 4, 4, 2]	[6, 3, 8, 2]	59	2
    // [1, 328, 467, 209, 54]	[2, 7, 1, 4, 3]	1723	294
    // [1, 99999, 100000, 99995]	[9999, 9001, 9999, 9001]	3456789012	39354
    int[] diffs = {1, 4, 4, 2};
    int[] times = {6, 3, 8, 2};
    long limit = 59;
    int answer = puzzleGameChallenge.solution(diffs, times, limit);
    System.out.println("정답은 : " + answer);

  }
}
