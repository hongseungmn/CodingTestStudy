
import java.util.HashMap;
import java.util.Map;

public class VowelDictionary {
  //사전 Map 생성
  Map<Character,Integer> vowelDict = new HashMap<>();
  public int solution(String word) {
    //사전에 글자 넣기
    vowelDict.put('A', 1);
    vowelDict.put('E',2);
    vowelDict.put('I',3);
    vowelDict.put('O',4);
    vowelDict.put('U',5);
    int answer = 0;
    char[] charArr = word.toCharArray(); //문자 배열로 전환
    answer += charArr.length; //기존 글자 수 더하기
    for(int i=0; i<charArr.length;i++) {
      int num = vowelDict.get(charArr[i]); //숫자를 가져옴
      int loop = 0;
      System.out.println("num : "+num); //현재 루프를 돌면서 가능한 개수 셀 변수 초기화
      for (int j = 5; j > i; j--) { //ex) E-> A로 가능한 모든 경우의 수를 더해준다
        loop += Math.pow(5, j - i - 1); // A,E,I,O,U 총 다섯개이므로 5의 제곱수가 된다
      }
      System.out.println("loop : "+loop);
      answer += (num-1) * loop; //ex) I -> A,E의 두가지 경우 가능하므로 (3 - 1) * 그 뒤의 모든 경우의 수
    }
    return answer;
  }
  public static void main(String[] args) {
    VowelDictionary vowelDictionary = new VowelDictionary();
    /*
    String word = "AAAAE";        result = 6;
    String word = "AAAE";         result = 10;
    String word = "I";            result = 1563;
    String word = "EIO";          result = 1189
    */
    String word = "EIO";        
    
    System.out.println("정답은 : "+ vowelDictionary.solution(word));
  }
}

