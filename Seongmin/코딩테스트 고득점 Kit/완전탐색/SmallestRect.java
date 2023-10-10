/*
 * 최소 직사각형
 * https://school.programmers.co.kr/learn/courses/30/lessons/86491
 */
//홍성민



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
 * 사각형을 긴 변으로 눕힌 뒤 비교했다
 */
public class SmallestRect {
  class Rect { //사각형 클래스
    int w;
    int h;
    Rect(int w, int h) {
      if(w>=h) { 
        this.w = w;
        this.h = h;
      }
      else { //긴 변으로 눕힌다
        this.w = h;
        this.h = w;
      }
    }
    public int getArea() {
      return w*h;
    }
  }
  public int solution(int[][] sizes) {
    int answer = 0;
    for(int i=0;i<sizes.length;i++) {
      rectList.add(new Rect(sizes[i][0], sizes[i][1]));
    }
    rectList.sort(new Comparator<Rect>() {//눕힌 변 중 가장 긴 변을 가져옴
      @Override
      public int compare(Rect o1, Rect o2) {
        return o2.w - o1.w;
      }
      
    });
    answer = rectList.get(0).w;
    rectList.sort(new Comparator<Rect>() {//높이 중 가장 긴 변을 가져옴
      @Override
      public int compare(Rect o1, Rect o2) {
        return o2.h - o1.h;
      }
      
    });
    answer *= rectList.get(0).h;
    return answer;
  }
  List<Rect> rectList = new ArrayList<Rect>();
  public static void main(String[] args) {
      /*
      * sizes	                                                result
        [[60, 50], [30, 70], [60, 30], [80, 40]]	              4000
        [[10, 7], [12, 3], [8, 15], [14, 7], [5, 15]]	          120
        [[14, 4], [19, 6], [6, 16], [18, 7], [7, 11]]	          133
      */
    int[][] sizes = {{14,4},{19,6},{6,16},{18,7},{7,11}};
    SmallestRect smallestRect = new SmallestRect();
    System.out.println("정답은 : "+smallestRect.solution(sizes));
  }
}

