import java.util.LinkedList;

public class LightCycle {

  class Node {

    char type;
    int row;
    int col;
    LinkedList adjacent;

    Node(char type, int row, int col) {
      this.type = type;
      this.row = row;
      this.col = col;
      this.adjacent = new LinkedList<Node>();
    }

    public int[] out(int[] inputPoint){
      int[] outPoint = new int[2];
      switch (this.type) {
        case 'S':
          outPoint = sOut(inputPoint);
          break;
        case 'L':
          outPoint = lOut(inputPoint);
          break;
        case 'R':
          outPoint = rOut(inputPoint);
          break;
        default:
          break;
      }
      return outPoint;
    }

    public int[] sOut(int[] inputPoint) {
      //아래에서 들어옴
      if(row - inputPoint[0] > 0) return new int[]{inputPoint[0] - 1, inputPoint[1]};
      //위에서 들어옴
      else if(row - inputPoint[0] < 0) return new int[]{inputPoint[0] + 1, inputPoint[1]};
      //오른쪽에서 들어옴
      else if(col - inputPoint[1] > 0) return new int[]{inputPoint[0], inputPoint[1] - 1};
      //왼쪽에서 들어옴
      if(col - inputPoint[1] < 0) return new int[]{inputPoint[0], inputPoint[1] + 1};

      return null;
    }

    public int[] lOut(int[] inputPoint) {
      //아래에서 들어옴
      if(row - inputPoint[0] > 0) return new int[]{inputPoint[0] - 1, inputPoint[1] - 1};
      //위에서 들어옴
      else if(row - inputPoint[0] < 0) return new int[]{inputPoint[0] + 1, inputPoint[1] + 1};
      //오른쪽에서 들어옴
      else if(col - inputPoint[1] > 0) return new int[]{inputPoint[0] + 1, inputPoint[1] - 1};
      //왼쪽에서 들어옴
      if(col - inputPoint[1] < 0) return new int[]{inputPoint[0] - 1, inputPoint[1] + 1};
      return null;
    }

    public int[] rOut(int[] inputPoint) {
      //아래에서 들어옴
      if(row - inputPoint[0] > 0) return new int[]{inputPoint[0] - 1, inputPoint[1] + 1};
      //위에서 들어옴
      else if(row - inputPoint[0] < 0) return new int[]{inputPoint[0] + 1, inputPoint[1] - 1};
      //오른쪽에서 들어옴
      else if(col - inputPoint[1] > 0) return new int[]{inputPoint[0] - 1, inputPoint[1] - 1};
      //왼쪽에서 들어옴
      if(col - inputPoint[1] < 0) return new int[]{inputPoint[0] + 1, inputPoint[1] + 1};
      return null;
    }

    public void addLink(Node toNode) {
      if(!this.adjacent.contains(toNode)) {
        this.adjacent.add(toNode);
      }
      if(!toNode.adjacent.contains(this)) {
        toNode.adjacent.add(this);
      }
    }
  }



  public int[] solution(String[] grid) {
    int[] answer = {};
    Node[][] nodeGrid = new Node[grid.length][grid[0].length()];
    //nodeGrid 생성 및 링크 연결
    for(int row = 0; row < grid.length; row++) {
      for(int col = 0; col < grid[row].length(); col++) {
        Node node = new Node(grid[row].charAt(col), row, col);
        nodeGrid[row][col] = node;

        if(col == grid[row].length()-1) {// 마지막인 경우 처음 노드와 연결

        }
      }
    }

    //링크 연결


    return answer;
  }
  public static void main(String[] args) {
    // grid	result
    // ["SL","LR"]	[16]
    // ["S"]	[1,1,1,1]
    // ["R","R"]	[4,4]
    String[] grid = {"SL", "LR"};
    LightCycle lightCycle = new LightCycle();
    System.out.println(lightCycle.solution(grid));
  }
}
