package backjoon.IT기업및대기업계열사비슷;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/23971
public class ZOAC4 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    //초기값 세팅
    int H = Integer.parseInt(st.nextToken());
    int W = Integer.parseInt(st.nextToken());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    double heightNum = Math.ceil(H / (N + 1.0 ));
    double widthNum = Math.ceil(W / (M + 1.0));
    // System.out.println("heightNum : " + heightNum);
    // System.out.println("widthNum : " + widthNum);
    System.out.println((int)heightNum * (int)widthNum);
    
  }
}
