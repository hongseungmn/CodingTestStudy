/*
 * 모의고사
 * https://school.programmers.co.kr/learn/courses/30/lessons/42840
 */
//홍성민

import java.util.ArrayList;
import java.util.List;

public class PracticeTest {
  public int[] solution(int[] answers) {
    //해당 문자열 반복처리 여유분을 위해 +1처리
    String stringStudent_1 = "12345".repeat(answers.length / 5 + 1); 
    String stringStudent_2 = "21232425".repeat(answers.length / 8 + 1);
    String stringStudent_3 = "3311224455".repeat(answers.length / 10 + 1);
    //해당 문자열을 int[] 배열로 변경
    int[] intStudent_1 = stringStudent_1.chars().map(Character::getNumericValue).toArray();
    int[] intStudent_2 = stringStudent_2.chars().map(Character::getNumericValue).toArray();
    int[] intStudent_3 = stringStudent_3.chars().map(Character::getNumericValue).toArray();
    //학생별 점수를 저장할 변수 생성
    int scoreStudent_1 = 0;
    int scoreStudent_2 = 0;
    int scoreStudent_3 = 0;
    //답안지와 비교를 하며 점수 산출
    for(int i=0; i<answers.length;i++) {
      if(answers[i] == intStudent_1[i]) scoreStudent_1++;
      if(answers[i] == intStudent_2[i]) scoreStudent_2++;
      if(answers[i] == intStudent_3[i]) scoreStudent_3++;
    }
    System.out.println("학생 1의 점수 : "+ scoreStudent_1);
    System.out.println("학생 2의 점수 : "+ scoreStudent_2);
    System.out.println("학생 3의 점수 : "+ scoreStudent_3);
    List<Integer> answerList = new ArrayList<>();
    //비교를 한 후 가장 점수가 높은 인원 추가, 같을 경우 또한 처리
    int maxScore = Math.max(scoreStudent_1, Math.max(scoreStudent_2, scoreStudent_3));
    if (scoreStudent_1 == maxScore) {
      answerList.add(1);
    }
    if (scoreStudent_2 == maxScore) {
      answerList.add(2);
    }
    if (scoreStudent_3 == maxScore) {
      answerList.add(3);
    }
    //결과값은 list가 아닌 int[] 로 변환해 리턴
    return answerList.stream().mapToInt(Integer::intValue).toArray();
  }
  public static void main(String[] args) {
    PracticeTest practiceTest = new PracticeTest();

    /*
      answers	          return
      [1,2,3,4,5]	      [1]
      [1,3,2,4,2]	      [1,2,3]
      [3, 2, 2, 3]      [2]
      [3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, 4, 4, 5, 5]


      1번 수포자가 찍는 방식: 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, ...
      2번 수포자가 찍는 방식: 2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5, ...
      3번 수포자가 찍는 방식: 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, ...
    */

    int[] answers = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
    int [] result = practiceTest.solution(answers);
    for(int i : result) {
      System.out.println(i);
    }
    //System.out.println("정답은 : "+midtermExam.solution(answers).toString());
  }
}
