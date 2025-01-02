/*
 * n + 1 카드 게임
 * https://school.programmers.co.kr/learn/courses/30/lessons/258707
 */

import java.util.HashSet;
import java.util.Set;

public class NplusOneCardGame {
  
  public int solution(int coin, int[] cards) {
    Set<Integer> myDeck = new HashSet<>(); // 내 초기 덱
    //라운드마다 초기화 시키지 않고 이후 필요할 경우 해당 라운드에서 구매한 것이라 가정하면 된다
    Set<Integer> roundDeck = new HashSet<>(); // 라운드마다 받는 카드 2개 
    int round = 0; //라운드 
    int targetNumber = cards.length + 1; //다음라운드로 가기 위한 목표 숫자
    int cardIndex = cards.length / 3; //카드덱의 인덱스

    //초기값 세팅
    for(int i=0; i< cards.length / 3; i++) {
      myDeck.add(cards[i]);
    }

    //단순화를 놓침 -> 그리디하게 해결해야하지만 처음 완전탐색으로 구현하려 했음(구현 실패)
    //현재에서 해결이 불가능한 경우 카드 덱을 뽑는다.
    while(true) { //계속 반복 수행
      round++; //라운드 더하기
      if(cardIndex >= cards.length){
        break;
      }

      boolean flag = false; //다음라운드 가능 여부 확인
      //라운드 덱 추가
      roundDeck.add(cards[cardIndex]); 
      roundDeck.add(cards[cardIndex + 1]); 

      cardIndex += 2; //cardIndex 업데이트 


      for(int i : myDeck) { //라운드카드를 사용하지 않고 넘길 수 있다면 
        if(myDeck.contains(targetNumber - i)) { // 내 덱에서 반대 값을 찾아 타겟넘버를 만들 수 있다면
          myDeck.remove(i);
          myDeck.remove(targetNumber - i);
          flag = true;
          break;
        }
      }

      if(!flag) {
        if(coin > 0) { //카드를 살 수 있을 때 (1개)
          for(int i : myDeck) {
            if(roundDeck.contains(targetNumber - i)) {
              myDeck.remove(i);
              roundDeck.remove(targetNumber - i);
              coin--; //코인 감소
              flag = true;
              break;
            }
          }
        }
      }

      if(!flag) {
        if(coin > 1) { //카드를 살 수 있을 때 (2개)
          for(int i : roundDeck) {
            if(roundDeck.contains(targetNumber - i)) {
              roundDeck.remove(i);
              roundDeck.remove(targetNumber - i);
              coin -= 2; //코인 감소
              flag = true;
              break;
            }
          }
        }
      }

      if(!flag) break; //라운드 진행 불가
    }

    

    return round;
  }

  public static void main(String[] args) {
    NplusOneCardGame solution = new NplusOneCardGame();
    /*
     * coin	cards	result
        4	[3, 6, 7, 2, 1, 10, 5, 9, 8, 12, 11, 4]	5
        3	[1, 2, 3, 4, 5, 8, 6, 7, 9, 10, 11, 12]	2
        2	[5, 8, 1, 2, 9, 4, 12, 11, 3, 10, 6, 7]	4
        10	[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18]	1
     */

    int[] cards = {3, 6, 7, 2, 1, 10, 5, 9, 8, 12, 11, 4};
    int coin = 4;

    // int[] cards = {1, 2, 3, 4, 5, 8, 6, 7, 9, 10, 11, 12};
    // int coin = 3;

    // int[] cards = {5, 8, 1, 2, 9, 4, 12, 11, 3, 10, 6, 7};
    // int coin = 2;

    // int[] cards = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18};
    // int coin = 10;

    int answer = solution.solution(coin, cards);
    System.out.println("정답은 : " + answer);
  }
}