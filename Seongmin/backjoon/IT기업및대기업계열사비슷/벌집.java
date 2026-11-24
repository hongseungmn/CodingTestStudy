package backjoon.IT기업및대기업계열사비슷;

import java.util.Scanner;

public class 벌집 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int a = sc.nextInt();

    //지나는 방 수
    int chase = 1; 
    int start = 2;
    while(a > start - 1) {
      start += chase * 6;
      chase++;
    }
    System.out.println(chase);
  }
}
