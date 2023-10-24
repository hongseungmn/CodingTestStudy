
/*
 * 소수찾기
 * https://school.programmers.co.kr/learn/courses/30/lessons/42839
 * 홍성민
 */
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.IntStream;

public class FindPrimeNumber {
  public Set<Integer> primeSet;
  public int solution(String numbers) {
    primeSet = new HashSet<>();
    int[] numbersIntArr = numbers.chars().map(Character::getNumericValue).toArray();
    //IntStream.of(numbersIntArr).forEach(System.out::println);
    boolean[] visited = new boolean[numbersIntArr.length];
    int n = numbersIntArr.length;
    int[] output = new int[n];
    for(int i=1; i<=numbersIntArr.length;i++) {
      //System.out.println(i+"개로 숫자 조합");
      perm(numbersIntArr, output, visited, 0, n, i);
    }

    return primeSet.size();
  }
  // 순서를 지키면서 n 개중에서 r 개를 뽑는 경우
  // 사용 예시: perm(arr, output, visited, 0, n, 3);
  public void perm(int[] arr, int[] output, boolean[] visited, int depth, int n, int r) {
    if (depth == r) {
      int number = print(output, r);
      if(isPrime(number)) {
        System.out.println("소수입니다 :"+number);
        primeSet.add(number);
      }
      return;
    }

    for (int i=0; i<n; i++) {
        if (visited[i] != true) {
            visited[i] = true;
            output[depth] = arr[i];
            perm(arr, output, visited, depth + 1, n, r);       
            output[depth] = 0; // 이 줄은 없어도 됨
            visited[i] = false;;
        }
    }
  }
  public void swap(int[] arr, int depth, int i) {
    int temp = arr[depth];
    arr[depth] = arr[i];
    arr[i] = temp;
  }

  // 배열 출력
  public int print(int[] arr, int r) {
    String strNumber = "";
    for (int i = 0; i < r; i++) {
      System.out.print(arr[i] + " ");
      strNumber += arr[i];
    }
    System.out.println(strNumber);
    int number = Integer.parseInt(strNumber);
    return number;
  }

  public boolean isPrime(int number) {
    int i=2;
    if(number == 1 || number == 0) return false;
    else if(number == 2) return true;
    while(i<=number-1) {
      if(number %i == 0) return false;
      else i++;
    }
    return true;
  }
  public static void main(String[] args) {
    /*
      numbers	    return
      "17"	      3
      "011"	      2
      "123"       5
      "1231"      
     */
    FindPrimeNumber findPrimeNumber = new FindPrimeNumber();
    String numbers = "1231";
    System.out.println("정답은 : "+findPrimeNumber.solution(numbers));

  }
}