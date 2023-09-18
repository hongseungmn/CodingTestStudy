'''
K번째 수
https://school.programmers.co.kr/learn/courses/30/lessons/42748?language=python3

배열 array의 i번째 숫자부터 j번째 숫자까지 자르고 정렬했을 때, k번째에 있는 수를 구하려 합니다.
'''

'''
0913 한승완 풀이 
'''
def solution(array, commands):
    result = []
    x = []
    for i in commands:
        x = array[i[0]-1:i[1]]
        x.sort()
        result.append(x[i[2]-1])
    return result

'''

[ 테스트 ]
array = [1, 5, 2, 6, 3, 7, 4]
commands = [[2, 5, 3], [4, 4, 1], [1, 7, 3]]
return = [5, 6, 3] 

'''
print(solution([1, 5, 2, 6, 3, 7, 4], [[2, 5, 3], [4, 4, 1], [1, 7, 3]]))
