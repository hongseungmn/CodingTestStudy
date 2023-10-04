
/*
 * 의상
 * https://school.programmers.co.kr/learn/courses/30/lessons/42578
 */
//홍성민

import java.util.HashMap;

public class Clothes {
  public int solution(String[][] clothes) {
    int answer = 0;
    HashMap<String,Integer> map = new HashMap<>();
    for(int i=0;i<clothes.length;i++) {
      map.put(clothes[i][1], map.getOrDefault(clothes[i][1], 0)+1);
    }
    int kind = 1;
    for(String key : map.keySet()){
      kind *= map.get(key) +1 ;
    }
    return kind-1;
    }
}
