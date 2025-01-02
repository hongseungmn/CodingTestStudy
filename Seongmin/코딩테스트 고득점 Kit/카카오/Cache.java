import java.util.LinkedList;
import java.util.List;

public class Cache {
  public int solution(int cacheSize, String[] cities) {
    int answer = 0;
    //캐시 리스트 생성
    List<String> list = new LinkedList<String>();

    //도시를 순회하며 list에 삽입
    for(String city : cities) {
      String lowCaseCity = city.toLowerCase();
      //해당 도시가 포함되어 있지 않다면
      if(!list.contains(lowCaseCity)) {
        list.add(lowCaseCity);

        //리스트의 크기가 cacheSize보다 크다면
        if(list.size() > cacheSize) {
          //가장 앞의 요소 삭제
          list.remove(0);
        }

        //cacheMiss
        answer+= 5;

      } else { //해당 도시가 포함되어 있다면
        list.remove(lowCaseCity);
        list.add(lowCaseCity);

        //cacheHit
        answer++;
      }
      
    }

    System.out.println("LinkedList : " + list);

    return answer;
  }

  public static void main(String[] args) {
    Cache cache = new Cache();
    //String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
    //String[] cities = {"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"};
    String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
    int cacheSize = 0;
    int answer = cache.solution(cacheSize, cities);
    System.out.println("정답은 : " + answer);
  }
}