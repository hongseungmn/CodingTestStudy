import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonalInfoTerm {
  public int[] solution(String today, String[] terms, String[] privacies) {
    List<Integer> answer = new ArrayList<>();
    //모든 달은 28일까지 있다고 가정합니다.
    Map<String, Integer> termsMap = new HashMap<>();
    //문서 타입별 보관 월 수 저장
    for(String term : terms) {
      String key = term.split(" ")[0]; //문서 종류
      int value = Integer.parseInt(term.split(" ")[1]); //문서 보관기간 월 수
      termsMap.put(key, value);
    }

    for(int index=0; index<privacies.length; index++) {
      //개인정보 수집 일자
      String privacyDate = privacies[index].split(" ")[0];
      //문서 타입
      String type = privacies[index].split(" ")[1];

      //문서 타입별 보관기간 월 수를 가지고 만료 일자 계산
      if(getExpire(today, privacyDate, termsMap.get(type))) {
        answer.add(index + 1);
      }
    }

    return answer.stream().mapToInt(i -> i).toArray();
  }

  public boolean getExpire(String today,String privacyDate, int date) {
    int year = Integer.parseInt(privacyDate.split("\\.")[0]);
    int month = Integer.parseInt(privacyDate.split("\\.")[1]);
    int day = Integer.parseInt(privacyDate.split("\\.")[2]);

    month += date;
    
    // 값 업데이트
    year += (month - 1) / 12;  // 연도 갱신
    month = (month - 1) % 12 + 1;  // 월 갱신 (1~12로 조정)
    
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("%04d", year)).append(".");
    sb.append(String.format("%02d", month)).append(".");
    sb.append(String.format("%02d", day));
    System.out.println(sb.toString());
    return today.compareTo(sb.toString()) < 0 ? false : true;
  }
  public static void main(String[] args) {
    
    PersonalInfoTerm personalInfoTerm = new PersonalInfoTerm();
    /*
     * today	terms	privacies	result
      "2022.05.19"	["A 6", "B 12", "C 3"]	["2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"]	[1, 3]
      "2020.01.01"	["Z 3", "D 5"]	["2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"]	[1, 4, 5]
     */
    // String today = "2022.05.19";
    // String[] terms = {"A 6", "B 12", "C 3"};
    // String[] privacies = {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"};

    String today = "2020.01.01";
    String[] terms = {"Z 3", "D 5"};
    String[] privacies = {"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"};


    int[] answer = personalInfoTerm.solution(today, terms, privacies);
    System.out.println("정답은 : ");
    Arrays.stream(answer).forEach(e -> System.out.print(e +" "));
    System.out.println();
    
    System.out.println();
  }
}
