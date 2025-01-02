public class FoldWallet {
  public int solution(int[] wallet, int[] bill) {
    int answer = 0;
    //지갑의 긴 변과 작은 변을 변수에 저장(불변)
    int walletMax = Math.max(wallet[0], wallet[1]);
    int walletMin = Math.min(wallet[0], wallet[1]);

    //지폐의 긴 변과 작은 변을 변수에 저장(변경 가능)
    int billMax = Math.max(bill[0], bill[1]);
    int billMin = Math.min(bill[0], bill[1]);

    //지폐가 지갑에 들어가지는지 확인
    while(!(billMax <= walletMax && billMin <= walletMin)) {
      //반으로 접기
      billMax /= 2;
      
      //접고 난 후 지폐의 긴 변과 작은변을 업데이트
      if(billMax < billMin) {
        int temp = billMax;
        billMax = billMin;
        billMin = temp;
      }
      answer++;
    }
    return answer;
  }
  public static void main(String[] args) {
    /*
     * wallet	    bill	      result
      [30, 15]	  [26, 17]	  1
      [50, 50]	  [100, 241]	4
     */
    FoldWallet foldWallet = new FoldWallet();
    int[] wallet = {50, 50};
    int[] bill = {100, 241};
    int answer = foldWallet.solution(wallet, bill);
    System.out.println("정답은 : " + answer);
  }
}
