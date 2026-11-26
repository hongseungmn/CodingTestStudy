package algorithm_study.자료구조_01;

/**
 * String + 연산의 경우 컴파일 타임에 new StringBuilder.append.toString으로 변환된다. -> 그만큼 소요시간이 길어짐
 * Question_1
 * String Builder exec Time : 5
 * String exec Time : 1123
 */
public class Question_1 {
  public static void main(String[] args) {
    stringBuilderTest();
    stringTest();
  }

  public static void stringTest() {
    String result = "";
    long start = System.currentTimeMillis();

    for(int i=0; i<100000; i++) {
      result += "test";
    }
    long end = System.currentTimeMillis();

    System.out.println("String exec Time : " + (end - start));
  }

  public static void stringBuilderTest() {
    StringBuilder result = new StringBuilder();
    long start = System.currentTimeMillis();

    for(int i=0; i<100000; i++) {
      result.append("test");
    }

    long end = System.currentTimeMillis();

    System.out.println("String Builder exec Time : " + (end - start));

  }
}
