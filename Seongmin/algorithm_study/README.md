# 📚 성민 학생의 알고리즘 & 자료구조 로드맵 (Java)

> **목표**: 단순히 답을 구하는 코딩이 아닌, **시간·공간 복잡도 분석, 자료구조의 내부 동작 원리, 알고리즘적 사고의 틀**을 탄탄히 다져 프로그래머스 Lv.2 안정적 마스터 및 Lv.3 진입.

---

## 🗺️ 전체 커리큘럼

### [Phase 1] 선형 자료구조와 시간 복잡도 (기초 탄탄)
- [ ] **01-시간복잡도와-선형자료구조**
  - Big-O 표기법과 제약 조건에 따른 알고리즘 선택 기준 (1초 = 약 $10^8$번 연산)
  - `Array` vs `ArrayList` (Dynamic Array 원리, Amortized $O(1)$)
  - `LinkedList`의 구조와 메모리 배치 (배열 캐시 효율 vs 노드 참조)
- [ ] **02-스택-큐-덱 (Stack, Queue, Deque)**
  - Stack과 재귀/DFS의 관계, Monotonic Stack(단조 스택)
  - Queue와 Deque의 원리 (`ArrayDeque` vs `LinkedList`)
  - 관련 대표 문제 유형: 괄호 검사, 주식 가격, 기능 개발, 프로세스
- [ ] **03-해시 (Hash)**
  - Hash Table 동작 원리 (해시 충돌 해결: Chaining vs Open Addressing)
  - Java의 `HashMap`, `HashSet` 내부 구조
  - `equals()`와 `hashCode()` 규약
  - 관련 대표 문제 유형: 완주하지 못한 선수, 의상, 전화번호 목록

---

### [Phase 2] 비선형 자료구조와 탐색
- [ ] **04-우선순위-큐와-힙 (PriorityQueue & Heap)**
  - Complete Binary Tree와 Array 기반 Heap 구현
  - Max/Min Heap 삽입/삭제 $O(\log N)$ 원리
  - Java `Comparable`과 `Comparator`
  - 관련 대표 문제 유형: 더 맵게, 디스크 컨트롤러
- [ ] **05-그래프-표현과-기본-탐색 (DFS & BFS)**
  - 인접 행렬 vs 인접 리스트의 공간/시간 복잡도 트레이드오프
  - DFS: 재귀, Call Stack 오버플로우 방지
  - BFS: Queue, 최단거리 보장 원리
  - 관련 대표 문제 유형: 타겟 넘버, 게임 맵 최단거리, 네트워크

---

### [Phase 3] 효율적 탐색과 문제 해결 테크닉
- [ ] **06-정렬과-이진탐색 (Sorting & Binary Search)**
  - Dual-Pivot QuickSort, TimSort (Java `Arrays.sort` vs `Collections.sort`)
  - Binary Search의 불변식(Loop Invariant), Parametric Search(매개변수 탐색)
  - 관련 대표 문제 유형: 입국심사, 순위 검색
- [ ] **07-투포인터와-슬라이딩윈도우 (Two Pointers & Sliding Window)**
  - $O(N^2) \to O(N)$으로 줄이는 구간 탐색 기법
  - 관련 대표 문제 유형: 연속된 부분 수열의 합, 구명보트

---

### [Phase 4] 심화 문제 해결 전략
- [ ] **08-완전탐색과-백트래킹 (Brute Force & Backtracking)**
  - 상태 공간 트리와 가지치기(Pruning)
  - 순열, 조합, 부분집합 템플릿
  - 관련 대표 문제 유형: 카펫, 피로도, 소수 찾기
- [ ] **09-동적계획법과-그리디 (DP & Greedy)**
  - Optimal Substructure와 Overlapping Subproblems
  - Memoization (Top-down) vs Tabulation (Bottom-up)
  - 그리디의 정당성 증명 (Greedy Choice Property)
  - 관련 대표 문제 유형: 정수 삼각형, 등굣길, 체육복, 큰 수 만들기

---

## 📝 수업 및 기록 규칙
1. 각 단원 폴더에는 `개념노트.md`를 작성하여 이론과 Java 내부 동작 원리를 정리합니다.
2. 문제 풀이 시 파일명은 `[문제이름].java`로 하고, 코드 상단에 주석으로:
   - **문제 접근 아이디어**
   - **시간 복잡도 계산**
   - **핵심 로직 및 주의할 점**
   을 반드시 명시합니다.
