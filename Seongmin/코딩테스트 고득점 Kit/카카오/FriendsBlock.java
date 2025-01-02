import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class FriendsBlock {
  
  public int solution(int m, int n, String[] board) {
    int answer = 0;
    List<List<Block>> blockBoard = new ArrayList<>();
    //board를 list로 변경한다
    for(int i = n-1; i>=0; i--) {
      List<Block> list = new ArrayList<>();
      for(int j = m-1; j>=0; j--) { 
        list.add(new Block(i, j, board[j].charAt(i)));
      }
      blockBoard.add(list);
    }

    //블록 출력
    printBoard(blockBoard);

    //블록을 순회하며 2*2 형태로 4개인 경우를 찾는다.
    //삭제할 Map 생성 -> 중복 방지를 위해 Set으로 설정
    //이 과정을 2*2형태의 블록을 찾지 못할 때까지 반복
    boolean flag = true;
    while(flag) {
      Set<List<Integer>> removeSet = new HashSet<>();
      for(int i=0; i<blockBoard.size(); i++) {
        for(int j=0; j<blockBoard.get(i).size(); j++) {
          if(checkFriend(i, j, blockBoard, removeSet)) {
            System.out.println("2*2 형태의 블록을 찾았습니다 : " + "[" +i +"," + j +"]");
          }
        }
      }
      //만약 removeSet의 크기가 0이라면 탈출
      if(removeSet.size() == 0) flag = false;
      //removeMap에 있는 값으로 모두 제거
      //가장 바깥의 원소부터 삭제해야 한다 -> list로 변경후 order
      List<List<Integer>> sortedList = new ArrayList<>(removeSet);
      sortedList.sort(new Comparator<List<Integer>>() {
        @Override
        public int compare(List<Integer> o1, List<Integer> o2) {
          return o2.get(1) - o1.get(1);
        }
        
      });
      System.out.println("removeSet : " + sortedList);
      for(List<Integer> list : sortedList) {
        blockBoard.get(list.get(0)).remove((int)list.get(1));
      }
  
      printBoard(blockBoard);
      answer+= sortedList.size();
    }

    return answer;
  }
    
  class Block {
    int i;
    int j;
    Character blockType;

    public Block(int i, int j, Character blockType) {
      this.i = i;
      this.j = j;
      this.blockType = blockType;
    }
  }

  public void printBoard(List<List<FriendsBlock.Block>> blockBoard) {
    for(int i=0; i<blockBoard.size(); i++) {
      for(int j=0; j<blockBoard.get(i).size(); j++) {
        System.out.print(blockBoard.get(i).get(j).blockType +" ");
      }
      System.out.println();
    }
  }

  public boolean checkFriend(int i, int j, List<List<FriendsBlock.Block>> blockBoard, Set<List<Integer>> removeSet) {
    // 2*2 형태의 블록값 좌표
    int[][] block4 = {{i, j},{i+1, j}, {i, j+1}, {i+1, j+1}};
    Character blockType = blockBoard.get(i).get(j).blockType;
    for(int[] loc : block4) {
      if(blockBoard.size() <= loc[0] || blockBoard.get(loc[0]).size() <= loc[1]) return false; //index Error 방지
      if(blockType != blockBoard.get(loc[0]).get(loc[1]).blockType) return false; //블록 타입이 같지 않다면 false 리턴
    }
    //만약 2*2 인경우 removeSet에 모두 저장
    for(int[] loc : block4) {
      List<Integer> list = Arrays.asList(loc[0], loc[1]);
      removeSet.add(list);
    }
    return true;
  }

  public static void main(String[] args) {
    /*
      m	n	board	answer
      4	5	["CCBDE", "AAADE", "AAABF", "CCBBF"]	14
      6	6	["TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"]	15
      */

    int m = 6;
    int n = 6;

    String[] board = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};
    //"CCBDE", 
    //"AAADE", 
    //"AAABF", 
    //"CCBBF"
    

    FriendsBlock friendsBlock = new FriendsBlock();

    int answer = friendsBlock.solution(m, n, board);
    System.out.println("정답은 : " + answer);
  }

  
}