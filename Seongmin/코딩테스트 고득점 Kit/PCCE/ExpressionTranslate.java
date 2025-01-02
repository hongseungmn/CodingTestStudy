import java.util.ArrayList;
import java.util.List;

public class ExpressionTranslate {
  public String[] solution(String[] expressions) {
    //모든 수식을 탐색해 가능한 진법 수를 구한다(만약 수식 중 가장 큰 값이 5라면 5,4,3,2진법은 불가능 -> 6~9진법만 가능해진다)
    boolean[] baseList = new boolean[8]; //2 ~ 9진법까지 가능
    List<String> expressionList = new ArrayList<>();
    String[] answer = {};
    for(int i=0; i< expressions.length; i++) { //수식을 순회하며 가능한 진법 도출
      for(int k=0; k<baseList.length; k++) {
        int base = k+2; //사용할 진법
        if(calculate(expressions[i], base)) {
          System.out.println("진법 가능 : " + base);
          baseList[k] = true;
        } else {
          System.out.println("진법 불가능 : " + base);
          baseList[k] = false;
        }
      }
    }

    //가능한 진법을 순회하면서 결과 도출
    for(String expression : expressionList) {
      int expectedAns = -1;
      for(int i=0; i< baseList.length; i++) {
        if(baseList[i]) {
          int a = changeBase(expression.split(" ")[0].toCharArray(), i+2);
          String oper = expression.split(" ")[1]; //연산자 도출
          int b = changeBase(expression.split(" ")[2].toCharArray(), i+2);
          int ans;
          String updatedExpression;
          if(oper.equals("+")) {
            ans = a + b;
          } else {
            ans = a - b;
          }
          if(ans != expectedAns) { //값이 다를 경우 -> 예상 불가이므로 ? 로 바꿈
            updatedExpression = expression.replace("X", "?");
          } else {
            updatedExpression = expression.replace("X", Integer.toString(ans));
          }
          //예상값 업데이트
          expectedAns = ans;
        }
      }
    }


    return answer;
  }

  public boolean calculate(String expression,int base) {
    char[] a = expression.split(" ")[0].toCharArray(); // a 추출
    String oper = expression.split(" ")[1]; //연산자 도출
    char[] b = expression.split(" ")[2].toCharArray(); //b 도출
    char[] ans = expression.split(" ")[expression.split(" ").length-1].toCharArray(); // 마지막 문자 추출
    System.out.println("수식 : " + String.valueOf(a) + oper + String.valueOf(b) + " = " + String.valueOf(ans));

    if(changeBase(a, base) != -1 && changeBase(b, base) != -1 && changeBase(ans, base) != -1) { //모두 진법 변환에 성공했을 때
      //사칙연산 계산
      if(oper.equals("+")) {
        int baseA = changeBase(a, base);
        int baseB = changeBase(b, base);
        int baseAns = baseA + baseB;
        if(expression.split(" ")[expression.split(" ").length-1].equals(Integer.toString(baseAns, base))) { //계산 결과가 일치한다면 true 리턴
          return true;
        }
      } else {
        int baseA = changeBase(a, base);
        int baseB = changeBase(b, base);
        int baseAns = baseA - baseB;
        if(expression.split(" ")[expression.split(" ").length-1].equals(Integer.toString(baseAns, base))) { //계산 결과가 일치한다면 true 리턴
          return true;
        }
      }
    }
    return false;
  }

  //base진법의 수를 10진법으로 변환
  public int changeBase(char[] num , int base) {
    int baseA = 0;
    for (int i = 0; i < num.length; i++) {
        int digit = Character.getNumericValue(num[i]); // 숫자로 변환
        if (digit >= base) {
            //System.out.println("잘못된 숫자: " + digit + "는 " + base + "진법에 포함될 수 없습니다."); //해당 진법으로 변경 불가시 -1 리턴
            return -1;
        }
        baseA += digit * Math.pow(base, num.length - (i + 1)); //10진법으로 변환
    }
    return baseA;
  }
  
  public static void main(String[] args) {
    /*
     * expressions	result
      ["14 + 3 = 17", "13 - 6 = X", "51 - 5 = 44"]	["13 - 6 = 5"]
      ["1 + 1 = 2", "1 + 3 = 4", "1 + 5 = X", "1 + 2 = X"]	["1 + 5 = ?", "1 + 2 = 3"]
      ["10 - 2 = X", "30 + 31 = 101", "3 + 3 = X", "33 + 33 = X"]	["10 - 2 = 4", "3 + 3 = 10", "33 + 33 = 110"]
      ["2 - 1 = 1", "2 + 2 = X", "7 + 4 = X", "5 - 5 = X"]	["2 + 2 = 4", "7 + 4 = ?", "5 - 5 = 0"]
      ["2 - 1 = 1", "2 + 2 = X", "7 + 4 = X", "8 + 4 = X"]	["2 + 2 = 4", "7 + 4 = 12", "8 + 4 = 13"]
     */
    String[] expression = {"14 + 3 = 17", "13 - 6 = X", "51 - 5 = 44"};
    ExpressionTranslate expressionTranslate = new ExpressionTranslate();
    expressionTranslate.solution(expression);
  }
}


// import java.util.ArrayList;
// import java.util.List;

// public class ExpressionTranslate {
//   public String[] solution(String[] expressions) {
//     //모든 수식을 탐색해 가능한 진법 수를 구한다(만약 수식 중 가장 큰 값이 5라면 5,4,3,2진법은 불가능 -> 6~9진법만 가능해진다)
//     boolean[] baseList = new boolean[8]; //2 ~ 9진법까지 가능
//     List<String> expressionList = new ArrayList<>();
//     String[] answer = {};
//     for(int i=0; i< expressions.length; i++) { //수식을 순회하며 가능한 진법 도출
//       for(int k=0; k<baseList.length; k++) {
//         int base = k+2; //사용할 진법
//         if(calculate(expressions[i], base)) {
//           System.out.println("진법 가능 : " + base);
//           baseList[k] = true;
//         } else {
//           System.out.println("진법 불가능 : " + base);
//           baseList[k] = false;
//         }
//       }
//     }

//     //가능한 진법을 순회하면서 결과 도출
//     for(String expression : expressionList) {
//       int expectedAns = -1;
//       for(int i=0; i< baseList.length; i++) {
//         if(baseList[i]) {
//           int a = changeBase(expression.split(" ")[0].toCharArray(), i+2);
//           String oper = expression.split(" ")[1]; //연산자 도출
//           int b = changeBase(expression.split(" ")[2].toCharArray(), i+2);
//           int ans;
//           String updatedExpression;
//           if(oper.equals("+")) {
//             ans = a + b;
//           } else {
//             ans = a - b;
//           }
//           if(ans != expectedAns) { //값이 다를 경우 -> 예상 불가이므로 ? 로 바꿈
//             updatedExpression = expression.replace("X", "?");
//           } else {
//             updatedExpression = expression.replace("X", Integer.toString(ans));
//           }
//           //예상값 업데이트
//           expectedAns = ans;
//         }
//       }
//     }


//     return answer;
//   }

//   public boolean calculate(String expression,int base) {
//     char[] a = expression.split(" ")[0].toCharArray(); // a 추출
//     String oper = expression.split(" ")[1]; //연산자 도출
//     char[] b = expression.split(" ")[2].toCharArray(); //b 도출
//     char[] ans = expression.split(" ")[expression.split(" ").length-1].toCharArray(); // 마지막 문자 추출
//     System.out.println("수식 : " + String.valueOf(a) + oper + String.valueOf(b) + " = " + String.valueOf(ans));

//     if(changeBase(a, base) != -1 && changeBase(b, base) != -1 && changeBase(ans, base) != -1) { //모두 진법 변환에 성공했을 때
//       //사칙연산 계산
//       if(oper.equals("+")) {
//         int baseA = changeBase(a, base);
//         int baseB = changeBase(b, base);
//         int baseAns = baseA + baseB;
//         if(expression.split(" ")[expression.split(" ").length-1].equals(Integer.toString(baseAns, base))) { //계산 결과가 일치한다면 true 리턴
//           return true;
//         }
//       } else {
//         int baseA = changeBase(a, base);
//         int baseB = changeBase(b, base);
//         int baseAns = baseA - baseB;
//         if(expression.split(" ")[expression.split(" ").length-1].equals(Integer.toString(baseAns, base))) { //계산 결과가 일치한다면 true 리턴
//           return true;
//         }
//       }
//     }
//     return false;
//   }

//   //base진법의 수를 10진법으로 변환
//   public int changeBase(char[] num , int base) {
//     int baseA = 0;
//     for (int i = 0; i < num.length; i++) {
//         int digit = Character.getNumericValue(num[i]); // 숫자로 변환
//         if (digit >= base) {
//             //System.out.println("잘못된 숫자: " + digit + "는 " + base + "진법에 포함될 수 없습니다."); //해당 진법으로 변경 불가시 -1 리턴
//             return -1;
//         }
//         baseA += digit * Math.pow(base, num.length - (i + 1)); //10진법으로 변환
//     }
//     return baseA;
//   }
  
//   public static void main(String[] args) {
//     /*
//      * expressions	result
//       ["14 + 3 = 17", "13 - 6 = X", "51 - 5 = 44"]	["13 - 6 = 5"]
//       ["1 + 1 = 2", "1 + 3 = 4", "1 + 5 = X", "1 + 2 = X"]	["1 + 5 = ?", "1 + 2 = 3"]
//       ["10 - 2 = X", "30 + 31 = 101", "3 + 3 = X", "33 + 33 = X"]	["10 - 2 = 4", "3 + 3 = 10", "33 + 33 = 110"]
//       ["2 - 1 = 1", "2 + 2 = X", "7 + 4 = X", "5 - 5 = X"]	["2 + 2 = 4", "7 + 4 = ?", "5 - 5 = 0"]
//       ["2 - 1 = 1", "2 + 2 = X", "7 + 4 = X", "8 + 4 = X"]	["2 + 2 = 4", "7 + 4 = 12", "8 + 4 = 13"]
//      */
//     String[] expression = {"14 + 3 = 17", "13 - 6 = X", "51 - 5 = 44"};
//     ExpressionTranslate expressionTranslate = new ExpressionTranslate();
//     expressionTranslate.solution(expression);
//   }
// }
