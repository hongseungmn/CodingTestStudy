# 📅 2026 Week 01: 선형 자료구조 (배열 & 연결 리스트)

> **핵심 목표**: 
> 1. Big-O 표기법과 제약 조건에 따른 알고리즘 선택 기준 습득 ($1초 \approx 10^8$번 연산)
> 2. `Array`와 `ArrayList`(Dynamic Array)의 내부 동작 원리 및 시간 복잡도 이해
> 3. `LinkedList`의 노드 기반 구조, 양방향 연결 리스트와 이중 스택 기법 비교

---

## 📋 이번 주 문제 목록

| 번호 | 플랫폼 & 문제 번호 | 문제 이름 | 난이도 | 권장 시간 복잡도 | 핵심 자료구조 / 알고리즘 | 상태 |
| :---: | :--- | :--- | :---: | :---: | :--- | :---: |
| 1 | 백준 10818 | [최소, 최대](https://www.acmicpc.net/problem/10818) | Bronze 3 | $O(N)$ | 1차원 배열 순회, 빠른 입출력 | ⏳ 진행중 |
| 2 | 프로그래머스 Lv.1 | [같은 숫자는 싫어](https://school.programmers.co.kr/learn/courses/30/lessons/12906) | Level 1 | $O(N)$ | 동적 배열(`ArrayList`), 연속 중복 제거 | ⏳ 진행중 |
| 3 | 백준 1158 | [요세푸스 문제](https://www.acmicpc.net/problem/1158) | Silver 4 | $O(N \times K)$ | 원형 큐 / `Queue`, `ArrayDeque` | ⏳ 진행중 |
| 4 | 백준 1406 | [에디터](https://www.acmicpc.net/problem/1406) | Silver 2 | $O(M)$ | 커서 기반 편집기, `LinkedList` vs 이중 스택 | ⏳ 진행중 |

---

## 💡 파일 구성 및 실행 방법
모든 자바 소스 파일은 별도의 테스트 프레임워크(JUnit) 없이 **코드 내부에 자체 `main()` 테스트 러너**가 포함되어 있습니다.

```bash
# 파일 단독 실행 및 테스트
java boj_10818_최소최대.java
java prog_12906_같은숫자는싫어.java
java boj_1158_요세푸스문제.java
java boj_1406_에디터.java
```
