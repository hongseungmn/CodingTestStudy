'''
가장 큰 수
https://school.programmers.co.kr/learn/courses/30/lessons/42746

0 또는 양의 정수가 주어졌을 때, 정수를 이어 붙여 만들 수 있는 가장 큰 수를 알아내 주세요.
'''

'''
0913 한승완 풀이 
'''
def solution(numbers):
    list1 = []

    # [ 0, 0 ] 인 경우 "0"을 반환 -> 테스트 11번 
    if(sum(numbers) == 0):
        return "0"
        
    # numbers의 원소들을 문자열로 변환하여 list1에 저장
    for i in numbers:
        list1.append(str(i))

    list1.sort(key = lambda x : x * 4, reverse=True ) # 문자열 비교에서 X0을 처리하기 위해 반복
    
    
    return ''.join(list1) # 문자열로 합쳐서 반환

'''

[테스트]
numbers = [3, 30, 34, 5, 9]
return = "9534330"

'''
print(solution([3, 30, 34, 5, 9]))