import java.util.HashMap;
import java.util.Map;

public class NewsClustering {
  public int solution(String str1, String str2) {
    int answer = 0;
    //각 문자열을 두글자씩 자른 다중집합을 저장할 Map -> int는 갯수
    Map<String, Integer> map1 = new HashMap<>();
    Map<String, Integer> map2 = new HashMap<>();

    //2자리씩 잘라서 저장
    putMap(str1, map1);
    putMap(str2, map2);

    //교집합 개수 계산
    int countIntersection = 0;
    for(String key : map1.keySet()) {
      if(map2.containsKey(key)) {
        countIntersection += Math.min(map1.get(key), map2.get(key)); //최솟값을 넣어 교집합 개수를 구한다
      }
    }
    System.out.println("교집합 개수 : " + countIntersection);


    //합집합 개수 계산
    int countUnion = 0;
    for(String key : map1.keySet()) {
      if(map2.containsKey(key)) {
        countUnion += Math.max(map1.get(key), map2.get(key)); 
      } else {
        countUnion+= map1.get(key); //포함되지 않은 경우 개수 1을 더한다
      }
    }
    for(String key : map2.keySet()) {
      if(!map1.containsKey(key)) {
        countUnion+= map2.get(key);
      }
    }

    if(countUnion == 0) return 65536; //합집합 개수가 0인 경우 -> 교집합도 0이되어 NaN 발생 방지
    System.out.println("합집합 개수 : " + countUnion);
    double ans =  ((float)countIntersection / countUnion) * 65536;
    System.out.println("ans : " + ans);
    return (int) (ans);
  }

  public void putMap(String str1, Map<String,Integer> map) {
    for(int i=0; i<str1.length()-1;i++) {
      String key = str1.substring(i,i+2).toLowerCase();
      // 두 문자가 모두 영문자인 경우에만 처리
      if(Character.isLetter(key.charAt(0)) && Character.isLetter(key.charAt(1))) {
        map.put(key, map.getOrDefault(key, 0)+1);
      }
    }

    System.out.println("map : " + map);
  }
  public static void main(String[] args) {
    NewsClustering newsClustering = new NewsClustering();
    /*
      str1	      str2	      answer
      FRANCE	    french	    16384
      handshake	  shake hands	65536
      aa1+aa2	    AAAA12	    43690
      E=M*C^2	    e=m*c^2	    65536
      ABABAB      BABABA      43690
      abc         abbb        16384
      aaa         aa          32768
     */
    String str1 = "abbb";
    String str2 = "abc";
    int answer = newsClustering.solution(str1, str2);

    System.out.println("정답은 : " + answer);
  }
}
