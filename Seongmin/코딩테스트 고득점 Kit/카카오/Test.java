public class Test {
  public String solution(String s) {
    String answer = "";
    char[] arr = s.toCharArray();
    if(arr.length % 2 == 1) { //홀수인 경우
      answer = s.substring(arr.length / 2,arr.length / 2 + 1);
    } else { //짝수인 경우
      answer = s.substring(arr.length / 2 -1,arr.length / 2 + 1);
    }
    return answer;
  }
  public static void main(String[] args) {
    Test test = new Test();
    /*
     * s	return
      "abcde"	"c"
      "qwer"	"we"
     */
    String s = "abcde";
    String answer = test.solution(s);
    System.out.println("정답은 : " + answer);
  }
}
