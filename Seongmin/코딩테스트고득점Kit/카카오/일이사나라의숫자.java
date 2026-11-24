package 코딩테스트고득점Kit.카카오;

/**
 맞아요! 😄 아주 중요한 포인트입니다.

124진법은 1부터 시작이라서 n-1을 해주는 것이고,

234진법은 2부터 시작이므로, 논리적으로 n-2를 해야 맞습니다.

일반화된 규칙

변환할 숫자 집합: digits = [d1, d2, d3]

가장 작은 숫자가 minDigit = d1

자리 연산 전: n - minDigit

자리 계산: (n - minDigit) % len(digits)

다음 자리: (n - minDigit) / len(digits)

예시: 234진법 (digits = [2,3,4])

10진수 1~10을 변환하면:

가장 작은 숫자 = 2 → n-2

길이 = 3

자리 계산 = (n-2) % 3 → 0,1,2 → digits[0], digits[1], digits[2]
 */
public class 일이사나라의숫자 {
  public String solution(int n) {
      String answer = "";
      StringBuilder sb = new StringBuilder();
      while(n > 0) {
        int mod = (n - 1) % 3;
        switch (mod) {
          case 0: sb.append("1");break;
          case 1: sb.append("2");break;
          case 2: sb.append("4");break;
          default:
            break;
        }
        n = (n - 1) / 3;
      }

      return sb.reverse().toString();
  }

  static String convertTo124(int n) {
    // if
    return null;
  }
}
