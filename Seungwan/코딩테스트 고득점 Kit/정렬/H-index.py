'''

H-index
https://school.programmers.co.kr/learn/courses/30/lessons/42747#fn1

'''

'''
0914 한승완 풀이 [1] - 테스트 11 실패

아마도 시간 초과일 것으로 예상됨

'''
def solution2(citations):
    h_num = 0
    citations.sort()
    
    if(sum(citations) == 0): # 논문이 0개일 경우 0 반환
        return 0
    
    for i in range(citations[-1]): # 논문의 최대 인용 값까지 반복
        test_num = 0
        h_num += 1
        for j in range(len(citations)): # 논문 전체를 돌면서 인용 횟수를 판단
            if(citations[j] >= h_num):
                test_num += 1
        if(h_num == test_num):
            return h_num
        
'''
0914 한승완 풀이 [2]

인용 횟수의 배열 자체를 내림차순으로 정렬하여 한번의 탐색으로 Hindex 도출
'''

def solution(citations):
    citations.sort(reverse=True) # 인용 횟수를 내림차순으로 정렬
    h_num = 0
    '''
    인용 횟수와 해당 논문의 인덱스를 비교하여 Hindex 도출
    '''
    for i in range(len(citations)):
        if citations[i] >= i + 1: 
            h_num = i + 1
        else:
            break
    
    return h_num

'''

[시간 복잡도 비교 - 지피티가 해줬음 ]
solution2 = O(n^2)
solution의 = O(n)

[테스트]
citations = [3, 0, 6, 1, 5]
return = 3
'''

print(solution([3, 0, 6, 1, 5]))