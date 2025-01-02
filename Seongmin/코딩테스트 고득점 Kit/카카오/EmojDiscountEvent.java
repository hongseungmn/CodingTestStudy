import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EmojDiscountEvent {
  int[] answer = new int[2]; //플러스 가입자 수, 판매액

  public int[] solution(int[][] users, int[] emoticons) {
    int[] discountArr = {40, 30, 20, 10};

    
    
    List<Integer> emoticonsList = new ArrayList<>(); //이모티콘 가격들을 저장할 리스트
    List<Integer> discountList = new ArrayList<>(); //이모티콘별 각 할인율을 저장할 리스트 -> 초기 모두 40%
    Arrays.stream(emoticons).forEach(e -> emoticonsList.add(e));

    Collections.sort(emoticonsList); //가격 순으로 정렬 -> 가격이 낮은 것부터 할인율 조정해 가격변동을 줄인다

    //모두 40%를 할인
    for(int i=0; i<emoticons.length; i++) {
      discountList.add(40); //할인율 저장
    }

    //가능한 모든 할인율에 대한 조합
    calculateDiscountCombinations(emoticonsList, discountList, users, discountArr, 0);
    // for(int discount : discountArr) {
    //   for(int index = 0; index<discountList.size(); index++) {
    //     //할인율 조정
    //     discountList.set(index, discount);
    //     System.out.println("---------- 현재 할인율 ----------");
    //     System.out.println(discountList);
    //     getResult(emoticonsList, discountList, users);
    //   }
    // }
    
    //일단 해당 과정을 모든 경우의 수를 구해 도출한다
    

    
    return answer;
  }

  public void getResult(List<Integer> emoticonsList, List<Integer> discountList, int[][] users) {
    int plusCnt = 0; //플러스 가입자 수
    int saleAmount = 0; // 판매액
    //현재 적용된 할인율을 토대로 user를 순회하면서 값을 도출
    for(int userIndex=0; userIndex<users.length; userIndex++) {
      int userDiscount = users[userIndex][0]; //사용자별 생각하고 온 할인율
      int userAccount = users[userIndex][1]; //사용자별 구매비용
      int currentTotal = 0; //현재 비용
      for(int index=0; index<emoticonsList.size(); index++) {
        if(userDiscount <= discountList.get(index)) { //할인율이 사용자가 생각한 할인율보다 크다면 -> 구매
          currentTotal += emoticonsList.get(index) * ((100 - discountList.get(index)) * 0.01);
        }
      }


      if(userAccount <= currentTotal) { //현재 구매 비용이 예산을 초과할 경우 플러스 가입
        //System.out.printf("플러스에 가입했습니다. \t\t현재 비용 : %d , \t예산 : %d \n", currentTotal, userAccount);
        plusCnt++; //플러스 가입 수 업데이트
        //플러스에 가입하면 현재 비용은 0이 된다
        currentTotal = 0;
      } else {
        //System.out.printf("플러스에 가입하지 않았습니다. \t현재 비용 : %d ,\t 예산 : %d \n", currentTotal, userAccount);
      }
      //판매액 업데이트
      saleAmount += currentTotal;
    }

    //초기 조건 순으로 변동 있을 경우 변경 -> 플러스 가입자 수, 판매액
    if(answer[0] < plusCnt) {
      answer[0] = plusCnt;
      answer[1] = saleAmount;
      System.out.printf("결과 : (%d, %d) \n", answer[0], answer[1]);
    }
    if(answer[0] == plusCnt) {
      //플러스 가입자 수가 같다면 판매액을 이용해 비교
      answer[1] = Math.max(answer[1], saleAmount);
      System.out.printf("결과 : (%d, %d) \n", answer[0], answer[1]);
    }
  }

  public void calculateDiscountCombinations(List<Integer> emoticonsList, List<Integer> discountList, int[][] users,int[] discountArr, int index) {
    // 종료 조건: 모든 할인율 조합을 생성한 경우
    if (index == discountList.size()) {
      System.out.println("---------- 현재 할인율 조합 ----------");
      System.out.println(discountList);
      getResult(emoticonsList, discountList, users);
      return;
    }

    // 각 할인율을 현재 인덱스에 적용
    for (int discount : discountArr) {
        discountList.set(index, discount);
        calculateDiscountCombinations(emoticonsList, discountList, users, discountArr, index + 1);
    }
  }
  public static void main(String[] args) {
    EmojDiscountEvent emojDiscountEvent = new EmojDiscountEvent();
    /*
     * users	emoticons	result
      [[40, 10000], [25, 10000]]	[7000, 9000]	[1, 5400]
      [[40, 2900], [23, 10000], [11, 5200], [5, 5900], [40, 3100], [27, 9200], [32, 6900]]	[1300, 1500, 1600, 4900]	[4, 13860]
     */
    //int[][] users = {{40, 10000}, {25, 10000}};
    //int[] emoticons = {7000, 9000};
    
    int[][] users = {{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}};
    int[] emoticons = {1300, 1500, 1600, 4900};
    int[] answer = emojDiscountEvent.solution(users, emoticons);
    System.out.printf("정답은 : (플러스 가입자 수, 판매액) - (%d, %d)\n", answer[0], answer[1]);
  }
}
