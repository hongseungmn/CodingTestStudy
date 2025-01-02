public class Test2 {
  public int solution(String[] babbling) {
    int answer = 0;
    String[] can = {"aya", "ye", "woo", "ma"};
    
    for(String b : babbling) {
      String checkString = "*".repeat(b.length());
      String temp = b;
      for(String a : can) {
        if(b.contains(a)) {
          b = b.replaceFirst(a, "*".repeat(a.length()));
        }
      }
      if(b.equals(checkString)) {
        System.out.println(temp);
        System.out.println(checkString);
        answer++;
      }
    }
    return answer;
  }
  public static void main(String[] args) {
    Test2 test2 = new Test2();
    /*
     * babbling	result
      ["aya", "yee", "u", "maa"]	1
      ["ayaye", "uuu", "yeye", "yemawoo", "ayaayaa"]	2
     */

    String[] babbling = {"ayaye", "yeayaye", "yeye", "yemawoo","yemawoowoo", "ayaayaa"};
    int answer = test2.solution(babbling);
    System.out.println("정답은 : " + answer);
  }
}
