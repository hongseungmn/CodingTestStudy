import java.text.SimpleDateFormat;
import java.util.*;

public class ProgressSubject {
  public String[] solution(String[][] plans) {
    String[] answer = {};

    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

    //시간순 정렬
    
    Arrays.sort(plans, new Comparator<String[]>() {
      @Override
      public int compare(String[] o1, String[] o2) {
        try {
          Date o1Date = dateFormat.parse(o1[1]);
          Date o2Date = dateFormat.parse(o2[1]);

          return o1Date.after(o2Date) ? 1 : -1;

        } catch (Exception e) {
          System.out.println(e);
        }
        return 0;
      }
    });


    Queue<String[]> queue = new LinkedList<>(Arrays.asList(plans));
    Stack<String[]> stack = new Stack<>();
    while(!queue.isEmpty() || !stack.isEmpty()) {
      
    }

    for (String[] str : plans) {
      System.out.println(str[1]);
    }
    return answer;
  }

  public static void main(String[] args) {
    /*
     * plans
     * [["korean", "11:40", "30"], ["english", "12:10", "20"], ["math", "12:30",
     * "40"]]
     * [["science", "12:40", "50"], ["music", "12:20", "40"], ["history", "14:00",
     * "30"], ["computer", "12:30", "100"]]
     * [["aaa", "12:00", "20"], ["bbb", "12:10", "30"], ["ccc", "12:40", "10"]]
     */
    /*
     * result
     * ["korean", "english", "math"]
     * ["science", "history", "computer", "music"]
     * ["bbb", "ccc", "aaa"]
     */
    String[][] plans = {{"science", "12:40", "50"}, {"music", "12:20", "40"}, {"history", "14:00", "30"}, {"computer", "12:30", "100"}};
    //String[][] plans = { { "korean", "11:40", "30" }, { "english", "12:10", "20" }, { "math", "12:30", "40" } };
    ProgressSubject progressSubject = new ProgressSubject();
    progressSubject.solution(plans);
  }
}
