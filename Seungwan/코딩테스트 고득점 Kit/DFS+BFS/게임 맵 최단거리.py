'''

게임 맵 최단거리
https://school.programmers.co.kr/learn/courses/30/lessons/1844

'''

'''

한승완 0918 풀이

- 처음 위치에서 위 아래 양옆을 재귀로 호출 -> 이동 가능한 위치인지 파악하고 이동 횟수 증가 + 도착지점인지 확인 ==> [ 시간 초과 ]

[수정]

- BFS 너비 우선 탐색을 이용하여 풀이
- 큐를 이용하여 각 좌표를 저장하고, 4방향 탐색을 통해 이동 가능한 좌표 탐색 후 큐 삽입 ==> [ 통과 ]

'''

from collections import deque

def solution(maps):

    if len(maps) == 1 and len(maps[0]) == 1:
        return 1

    dx = [0, 1, 0, -1] # x 이동
    dy = [1, 0, -1, 0] # y 이동

    r_len = len(maps) # y 축 길이
    v_len = len(maps[0]) # x 축 길이

    # 너비 우선 탐색
    def bfs():
        queue = deque() # 큐 생성
        queue.append((0, 0)) # 시작점 큐에 삽입
        
        while queue:
            '''
            popleft = O(1)의 빠른 복잡도로 원소 삭제 가능
            '''
            row, vec = queue.popleft()
            if (row, vec) == (r_len - 1, v_len - 1): 
                return maps[row][vec]
            
            '''
            4방향 탐색 -> dx, dy를 이용하여 각각의 방향을 탐색
            '''
            for (y, x) in zip(dy, dx):
                new_row = row + y # 새로운 y 좌표
                new_vec = vec + x # 새로운 x 좌표
                '''
                길이가 벗어나거나, 벽이거나, 이미 방문한 곳이면 continue
                '''
                if new_row < 0 or new_row >= r_len or new_vec < 0 \
                    or new_vec >= v_len or maps[new_row][new_vec] == 0 or \
                    (maps[new_row][new_vec] != 1 and 
                     maps[new_row][new_vec] <= maps[row][vec] + 1): 
                    continue
                maps[new_row][new_vec] = maps[row][vec] + 1 
                '''
                아닐경우 -> 이동 횟수 증가 + 큐에 새로운 좌표 삽입
                '''
                queue.append((new_row, new_vec))
        # 도달 못함 = -1 리턴
        return -1

    return bfs()

print(solution([[1,0,1,1,1],[1,0,1,0,1],[1,0,1,1,1],[1,1,1,0,1],[0,0,0,0,1]]))