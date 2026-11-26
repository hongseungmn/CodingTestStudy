package algorithm_study.자료구조_01;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * [문제 상황]
 * 크기 N = 200,000인 데이터 배열에서
 * 특정 값이 존재하는지 M = 100,000번 확인해야 할 때의 시간 비교
 *
 * 1. 단순 선형 탐색 (Linear Search) -> O(N * M) -> 약 200억 번 연산 (측정을 위해 축소 실행)
 * 2. 정렬 후 이진 탐색 (Binary Search) -> O((N + M) log N) -> 약 540만 번 연산
 * 3. 해시 셋 (HashSet) -> O(N + M) -> 약 30만 번 연산
 */
public class Question_2 {

  public static void main(String[] args) {
    int N = 200_000;
    int M = 100_000;

    System.out.println("=== 데이터 준비 (N = " + N + ", M = " + M + ") ===");
    Random random = new Random(42);

    int[] data = new int[N];
    for (int i = 0; i < N; i++) {
      data[i] = random.nextInt(1_000_000);
    }

    int[] queries = new int[M];
    for (int i = 0; i < M; i++) {
      queries[i] = random.nextInt(1_000_000);
    }

    // 1. 단순 선형 탐색 (O(N * M)은 200초 이상 걸리므로 M을 1,000으로 줄여서 추정)
    linearSearchTest(data, queries);

    // 2. 정렬 + 이진 탐색 (O((N + M) log N))
    binarySearchTest(data.clone(), queries);

    // 3. 해시 셋 (O(N + M))
    hashSetTest(data, queries);
  }

  /**
   * 1. 단순 선형 탐색
   * M개의 질문 전체를 다 돌리면 컴퓨터가 멈춘 것처럼 보일 수 있어서,
   * M = 1,000번만 돌리고 전체 100,000번일 때의 예상 시간을 계산합니다.
   */
  public static void linearSearchTest(int[] data, int[] queries) {
    int sampleM = 1_000;
    int foundCount = 0;

    long start = System.currentTimeMillis();
    for (int q = 0; q < sampleM; q++) {
      int target = queries[q];
      for (int num : data) {
        if (num == target) {
          foundCount++;
          break;
        }
      }
    }
    long end = System.currentTimeMillis();
    long elapsed = end - start;

    System.out.println("\n[1. 단순 선형 탐색 (Linear Search)]");
    System.out.println(" - 1,000번 조회 소요 시간: " + elapsed + " ms");
    System.out.println(" - 100,000번 전체 조회 시 예상 시간: 약 " + (elapsed * 100 / 1000.0) + " 초 (시간 초과 TLE 확정!)");
  }

  /**
   * 2. 정렬 후 이진 탐색
   * Arrays.sort(): O(N log N) Dual-Pivot Quicksort
   * Arrays.binarySearch(): 1회당 O(log N)
   */
  public static void binarySearchTest(int[] data, int[] queries) {
    long start = System.currentTimeMillis();

    // 1) 데이터 정렬 (O(N log N))
    Arrays.sort(data);

    // 2) M번 이진 탐색 (O(M log N))
    int foundCount = 0;
    for (int target : queries) {
      if (Arrays.binarySearch(data, target) >= 0) {
        foundCount++;
      }
    }

    long end = System.currentTimeMillis();
    System.out.println("\n[2. 정렬 후 이진 탐색 (Binary Search)]");
    System.out.println(" - 정렬 + 100,000번 전체 이진탐색 소요 시간: " + (end - start) + " ms");
    System.out.println(" - 찾은 개수: " + foundCount);
  }

  /**
   * 3. 해시 셋 (HashSet)
   * Set 빌드: O(N)
   * M번 조회: 1회당 O(1) -> O(M)
   */
  public static void hashSetTest(int[] data, int[] queries) {
    long start = System.currentTimeMillis();

    // 1) HashSet에 데이터 삽입 (O(N))
    Set<Integer> set = new HashSet<>(data.length);
    for (int num : data) {
      set.add(num);
    }

    // 2) M번 조회 (O(M))
    int foundCount = 0;
    for (int target : queries) {
      if (set.contains(target)) {
        foundCount++;
      }
    }

    long end = System.currentTimeMillis();
    System.out.println("\n[3. 해시 셋 (HashSet)]");
    System.out.println(" - Set 빌드 + 100,000번 전체 조회 소요 시간: " + (end - start) + " ms");
    System.out.println(" - 찾은 개수: " + foundCount);
  }
}
