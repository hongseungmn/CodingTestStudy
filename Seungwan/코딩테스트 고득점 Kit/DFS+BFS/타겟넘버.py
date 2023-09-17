'''

타겟넘버
https://school.programmers.co.kr/learn/courses/30/lessons/43165

'''

'''

0917 한승완 풀이 [1] - DFS 재귀 응용

'''
def solution2(numbers, target):
    depth = 0
    answer = [0]

    def dfs(index, value, depth):
        if(depth == len(numbers)):
            if(target == value):
                answer[0] += 1
                return 
        if(depth < len(numbers)):
            dfs(index+1, value + numbers[index], depth+1)
            dfs(index+1, value - numbers[index], depth+1)

    dfs(0,0,0)

    return answer[0]

'''

0917 한승완 풀이 [2] - BFS 큐 이용

- 시간 초과 1번 2번

'''

def solution(numbers, target):
    node_q = [0]

    for node in numbers:
        q = []
        for _ in range(len(node_q)):
            v = node_q.pop(0)
            node_q.append(v + node)
            node_q.append(v + -(node))
    
    return node_q.count(target)

'''

테스트

'''
print(solution([1, 1, 1, 1, 1],3))


