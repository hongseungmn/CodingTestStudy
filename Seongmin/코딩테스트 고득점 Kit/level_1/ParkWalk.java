public class ParkWalk {
  private int[] curLoc = new int[2];
  private int width = 0;
  private int height = 0;
  public int[] solution(String[] park, String[] routes) {
    int[] answer = {};

    //공원정보 인덱스 초기화
    width = park[0].length()-1;
    height = park.length-1;

    //처음 위치 찾기
    for(int i=0; i<park.length; i++) {
      for(int j=0; j<park[i].length(); j++) { //시작지점 초기화
        if(park[i].charAt(j) == 'S') {
          curLoc[0] = i;
          curLoc[1] = j;
        }
      }
    }

    for(String route : routes) {
      int[] move = getMoveType(route);
      int newLocX = curLoc[0] + move[0];
      int newLocY = curLoc[1] + move[1];

      System.out.printf(String.format("현재 위치 (%d, %d)\n", curLoc[0], curLoc[1]));
      System.out.printf(String.format("이동 위치 (%d, %d)\n", newLocX, newLocY));

      System.out.println("width : " + width);
      System.out.println("height : " + height);
      
      //해당 지점이 인덱스를 벗어나는 경우 무시
      if(newLocX > height  || newLocX < 0 || newLocY > width || newLocY < 0) continue;


      //해당 이동 경로에 장애물이 있을 경우 무시
      int xRangeMin = Math.min(curLoc[0], newLocX);
      int xRangeMax = Math.max(curLoc[0], newLocX);
      int yRangeMin = Math.min(curLoc[1], newLocY);
      int yRangeMax = Math.max(curLoc[1], newLocY);
      boolean flag = false;

      for(int i=xRangeMin; i<=xRangeMax; i++) {
        for(int j=yRangeMin; j<=yRangeMax; j++) {
          
          if(park[i].charAt(j) == 'X') {
            flag = true;
          }
        }
      }
      if(flag) continue;
      else {
        curLoc[0] = newLocX;
        curLoc[1] = newLocY;
      }
    }
    
    return new int[] {curLoc[0], curLoc[1]};
  }

  public int[] getMoveType(String route) {
    String type = route.split(" ")[0];//움직이는 방향 동,서,남,북 -> 1, 2, 3, 4
    int[] direction = type.equals("E") ? new int[] {0, 1} : 
                    type.equals("W") ? new int[] {0, -1} : 
                    type.equals("S") ? new int[] {1, 0} : new int[] {-1, 0};

    int m = Integer.parseInt(route.split(" ")[1]); //움직일 거리
    return new int[]{direction[0] * m, direction[1] * m};
  }
  public static void main(String[] args) {
    /*
     * park	routes	result
      ["SOO","OOO","OOO"]	["E 2","S 2","W 1"]	[2,1]
      ["SOO","OXX","OOO"]	["E 2","S 2","W 1"]	[0,1]
      ["OSO","OOO","OXO","OOO"]	["E 2","S 3","W 1"]	[0,0]
     */
    // String[] park = {"SOO","OOO","OOO"};
    // String[] routes = {"E 2","S 2","W 1"};

    // String[] park = {"SOO","OXX","OOO"};
    // String[] routes = {"E 2","S 2","W 1"};

    String[] park = {"OSO","OOO","OXO","OOO"};
    String[] routes = {"E 2","S 3","W 1"};

    ParkWalk parkWalk = new ParkWalk();
    parkWalk.solution(park, routes);
  }
}