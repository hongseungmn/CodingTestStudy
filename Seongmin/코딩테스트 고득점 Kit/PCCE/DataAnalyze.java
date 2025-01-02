import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class DataAnalyze {
  public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
    int[][] answer = {};
    //data에서 ext 값이 val_ext보다 작은 데이터만 뽑은 후 sort_by에 해당하는 값을 기준으로 오름차순으로 정렬
    //data = code	date	maximum	remain

    //data를 sort_by 오름차순으로 정렬한 리스트 생성
    List<int[]> list = new ArrayList<>();
    for(int[] d : data) {
      int[] row = new int[4];
      for(int i=0; i<4; i++) {
        row[i] = d[i];
      }
      list.add(row);
    }
    list = sortDataByType(list, getType(sort_by));

    //val_ext보다 작은 데이터들만 선별
    //ext로 데이터 인덱스를 가져오기
    int[][] extractData = getFitDataByExt(getType(ext), val_ext, list);
    System.out.println("결과");
    return extractData;
  }

  public int getType(String ext) {
    if(ext.equals("code")) {
      return 0;
    } else if(ext.equals("date")) {
      return 1;
    } else if(ext.equals("maximum")) {
      return 2;
    } else return 3;
  }

  public List<int[]> sortDataByType(List<int[]> source, int sortIndex) {
    List<int[]> list = new ArrayList<>(source);
    list.sort(new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        return o1[sortIndex] - o2[sortIndex];
      }
    });
    System.out.println("오름차순으로 정렬된 리스트");
    printList(list);
    return list;
  }

  public int[][] getFitDataByExt(int index, int val_ext, List<int[]> source) {
    List<int[]> list = new ArrayList<>();
    for(int[] row : source) {
      if(row[index] < val_ext) { //만약 값이 크다면 리스트에서 삭제
        list.add(row);
      }
    }

    return list.toArray(new int[list.size()][]);
  }

  public void printList(List<int[]> list) {
    for(int[] arr : list) {
      for(int a : arr) {
        System.out.print(a +" ");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    DataAnalyze dataAnalyze = new DataAnalyze();
    /*
     * data	ext	val_ext	sort_by	result
      [[1, 20300104, 100, 80], [2, 20300804, 847, 37], [3, 20300401, 10, 8]]	"date"	20300501	"remain"	[[3,20300401,10,8],[1,20300104,100,80]]
     */
    int[][] data = {{1, 20300104, 100, 80}, {2, 20300804, 847, 37}, {3, 20300401, 10, 8}};
    String ext = "date";
    int val_ext = 20300501;
    String sort_by = "remain";

    int[][] answer = dataAnalyze.solution(data, ext, val_ext, sort_by);
    for(int[] ans : answer) {
      for(int a : ans) {
        System.out.print(a + " ");
      }
      System.out.println();
    }
  }
}
