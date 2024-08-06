/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/258712
 * 가장 많이 받은 선물
 */

import java.util.*;

public class MostReceivedGift {

  Map<String, Friend> friendMap = new HashMap<>();
  int answer = 0;

  class Friend {

    //이름
    String name;

    //이번달 준 총 선물 숫자
    int giveCount;
    //이번달 준 선물 로그
    Map<String, Integer> giveLog;
    //이번달 받은 총 선물 숫자
    int receivedCount;
    //이번달 받은 선물 로그
    Map<String, Integer> receivedLog;

    //선물 지수
    int giftScoville;

    //다음달 받을 선물
    int willReceivedCount;

    public Friend(String name) {
      this.name = name;
      this.giveCount = 0;
      this.receivedCount = 0;
      this.giftScoville = 0;
      this.willReceivedCount = 0;
      this.giveLog = new HashMap<>();
      this.receivedLog = new HashMap<>();
    }

    public void giveGift(String toName) {
      Friend to = findFriend(toName);
      // 준 선물 개수로그 찍기
      this.giveLog.put(to.name , this.giveLog.containsKey(toName) ? this.giveLog.get(toName) + 1 : 1);
      // 준 선물 숫자 업데이트
      this.giveCount += 1;
    }

    public void receiveGift(String fromName) {
      Friend from = findFriend(fromName);
      // 받은 선물 개수 로그 찍기
      this.receivedLog.put(from.name, this.receivedLog.containsKey(fromName) ? this.receivedLog.get(fromName) + 1 : 1);
      // 받은 선물 숫자 업데이트
      this.receivedCount += 1;
    }

    public int getGiftScoville() {
      return giveCount - receivedCount;
    }

    public int getWillReceivedCount() {
      for(String name : friendMap.keySet()) {
        if(name.equals(this.name)) continue; // 자기 자신과 같으면 패스
        Friend friend = friendMap.get(name);
        if(this.giveLog.containsKey(friend.name) || this.receivedLog.containsKey(friend.name)) { // 해당 친구에게 주거나 받은 적이 있는 경우

          // 해당 친구에게 준 개수와 해당 친구에게서 받은 개수 비교
          int giveCount = this.giveLog.containsKey(friend.name) ? this.giveLog.get(friend.name) : 0;
          int receivedCount = friend.giveLog.containsKey(this.name) ? friend.giveLog.get(this.name) : 0;

          System.out.println(this.name + "이 " + friend.name +"에게 선물 "+giveCount+"개를 줌");
          System.out.println(this.name + "이 " + friend.name +"에게 선물 "+receivedCount+"개를 받음");

          if(giveCount > receivedCount) this.willReceivedCount++;
          else if(giveCount == receivedCount) { // 개수가 동일한 경우
            //선물 지수 비교
            if(this.getGiftScoville() > friend.getGiftScoville()) this.willReceivedCount++;
            //개수도 같고 선물지수도 같은 경우 패스
          }
        }
        else { //해당 친구와 관계가 없는 경우
          if(this.getGiftScoville() > friend.getGiftScoville()) this.willReceivedCount++;
        }

        System.out.println(this.name + " 선물지수 변화값 ("+friend.name+"에 의한): " + this.willReceivedCount);
        
      }

      System.out.println("다음달 받을 선물 수 : " + this.willReceivedCount);
      return this.willReceivedCount;
    }
  }

  public Friend findFriend(String name) {
    return friendMap.get(name);
  }

  
  public int solution(String[] friends, String[] gifts) {

    //친구 초기화
    for(String friend : friends) {
      friendMap.put(friend, new Friend(friend));
    }

    

    //선물관계 설정
    for(String gift : gifts) {
      String to = gift.split(" ")[0];
      String from = gift.split(" ")[1];
      Friend toFriend = findFriend(to);
      Friend fromFriend = findFriend(from);
      fromFriend.receiveGift(to);
      toFriend.giveGift(from);
    }




    //다음달 받을 선물 개수 계산
    for(String name : friendMap.keySet()) {
      Friend friend = friendMap.get(name);
      System.out.println("이름 : " +friend.name);
      System.out.println("준 이력 : " + friend.giveLog.toString());
      System.out.println("받은 이력 : " + friend.receivedLog.toString());
      System.out.println("선물지수 : " + friend.getGiftScoville());
      answer = Math.max(friend.getWillReceivedCount(),answer);
      System.out.println("======================================");
    }



    return answer;
  }


  public static void main(String[] args) {
    /*
      friends	                                      gifts	                                                      result
      ["muzi", "ryan", "frodo", "neo"]	           ["muzi frodo", "muzi frodo",                                 2
                                                    "ryan muzi", "ryan muzi", 
                                                    "ryan muzi", "frodo muzi", 
                                                    "frodo ryan", "neo muzi"]	

      ["joy", "brad", "alessandro", "conan", "david"]	["alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david"]	4
      ["a", "b", "c"]	["a b", "b a", "c a", "a c", "a c", "c a"]	0
     */

    // String[] friends = {"muzi", "ryan", "frodo", "neo"};
    // String[] gifts = {"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"};

    // String[] friends = {"joy", "brad", "alessandro", "conan", "david"};
    // String[] gifts = {"alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david"};

    String[] friends = {"a", "b", "c"};
    String[] gifts = {"a b", "b a", "c a", "a c", "a c", "c a"};


    MostReceivedGift mostReceivedGift = new MostReceivedGift();
    System.out.println("정답은 : " + mostReceivedGift.solution(friends, gifts));
  }
}
