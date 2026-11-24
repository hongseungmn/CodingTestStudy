package 코딩테스트고득점Kit.카카오;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TakingGroupPhoto {

  
  static String[] ch = {"A", "C", "F", "J", "M", "N", "R", "T"};
  static boolean[] visited = new boolean[ch.length];
  static int answer = 0;

  public int solution(int n, String[] data) {
      
      // ["N~F=0", "R~T>2"] => data
      permute(new String[ch.length], 0, data);

      return answer;
  }

  static void permute(String[] result, int depth, String[] data) {
    boolean flag = true;
    if(depth == ch.length) {
      System.out.println(String.join("", result));
      String last = String.join("", result);
      for(String str : data) {
        char[] chArr = str.toCharArray();
        int a = last.indexOf(chArr[0]);
        int b = last.indexOf(chArr[2]);


        int gap = Math.abs(a - b) - 1;
        int dist = chArr[4] - '0';

        switch (chArr[3]) {
          case '=':
            if (gap != dist) flag = false;
            break;
          case '<':
            if (gap >= dist) flag = false;
            break;
          case '>':
            if (gap <= dist) flag = false;
            break;
        }
          
        if(!flag) return;
      }
      answer++;
      return;
    }

    for(int i=0; i< ch.length; i++) {
      if(!visited[i]) {
        visited[i] = true;
        result[depth] = ch[i];
        permute(result, depth + 1, data);
        visited[i] = false; //backgracking
      }
    }
  }

  public static void main(String[] args) {
    TakingGroupPhoto takingGroupPhoto = new TakingGroupPhoto();
    takingGroupPhoto.solution(2, new String[]{"N~F=0", "R~T>2"});
  }

  //시간 줄인 버전

  static char[] people = {'A','C','F','J','M','N','R','T'};

  static void dfs(char[] arr, boolean[] visited, int depth, String[] data) {
    if(depth == 8) {
      answer++;
      return;
    }

    for (int i=0; i< 8; i++) {
      if(visited[i]) continue;

      arr[depth] = people[i];
      visited[i] = true;

      //중간 검사
      if(isValid(arr, depth, data)) {
        dfs(arr, visited, depth + 1, data);
      }

      visited[i] = false;
    }
  }

  static boolean isValid(char[] arr, int depth, String[] data) {
    for(String s: data) {
      char a = s.charAt(0);
      char b = s.charAt(2);
      char op = s.charAt(3);
      int dist = Integer.parseInt(s.substring(4));

      int posA = -1, posB = -1;

      //현재까지 배치된 범위만 확인
      for(int i=0; i<=depth; i++) {
        if(arr[i] == a) posA = i;
        if(arr[i] == b) posB = i;
      }

      //둘 다 아직 배치 안 됐으면 검사 불과 -> 통과
      if (posA == -1 || posB == -1) continue;

      int gap = Math.abs(posA - posB) - 1;

      switch (op) {
          case '=':
              if (gap != dist) return false;
              break;
          case '<':
              if (gap >= dist) return false;
              break;
          case '>':
              if (gap <= dist) return false;
              break;
      }
    }
    return true;
  }
}
