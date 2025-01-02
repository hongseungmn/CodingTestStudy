import java.util.Arrays;
import java.util.Collections;

public class ParkNMats {
  public int solution(int[] mats, String[][] park) {
    int answer = -1;

    //매트를 큰 것부터 넣어보기 위해 역순으로 재배치
    Integer[] sortedMats = Arrays.stream(mats).boxed().toArray(Integer[]::new);
    Arrays.sort(sortedMats, Collections.reverseOrder());

    System.out.println(Arrays.toString(mats));
    for(int i=0; i<park.length; i++) {
      for(int j=0; j<park[i].length; j++) {
        if(park[i][j].equals("-1")) { //만약 해당 자리가 빈공간(-1) 이라면
          //해당 자리에 깔 수 있는 정사각형 매트의 길이 구함 초기 크기 1
          answer = Math.max(getMaxArea(i, j, park, 1), answer);
        }
      }
    }
    
    for(int mat : sortedMats) {
      if(mat <= answer) {
        answer = mat;
        return answer;
      }
    }
    return -1;
  }

  public int getMaxArea(int row, int col, String[][] park, int size) {
    for(int i=0; i < size; i++) {
      for(int j=0; j < size; j++) {
        int checkRow = row + i;
        int checkCol = col + j;
        if(park.length <= checkRow || park[checkRow].length <= checkCol) return size-1; // 길이 벗어나는 index 에러 헨들링 이전 사이즈 리턴
        if(!park[checkRow][checkCol].equals("-1")) { // -1이 아니라면 이전 사이즈 리턴
          return size-1;
        }
      }
    }
    
    return getMaxArea(row, col, park, size + 1); //모두 -1이라면 size를 한칸 늘려 재수행
  }

  public static void main(String[] args) {
    // mats	park	result
    // [5,3,2]	[["A", "A", "-1", "B", "B", "B", "B", "-1"], ["A", "A", "-1", "B", "B", "B", "B", "-1"], ["-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1"], ["D", "D", "-1", "-1", "-1", "-1", "E", "-1"], ["D", "D", "-1", "-1", "-1", "-1", "-1", "F"], ["D", "D", "-1", "-1", "-1", "-1", "E", "-1"]]	3
    // [3], [["-1", "-1", "-1"], ["-1", "-1", "-1"], ["-1", "-1", "A"]]
    int[] mats = {3};
    String[][] park = {{"-1", "-1", "-1"}, {"-1", "-1", "-1"}, {"-1", "-1", "A"}};
    ParkNMats parkNMats = new ParkNMats();
    int answer = parkNMats.solution(mats, park);
    System.out.println("정답은 : " + answer);
  }
}
