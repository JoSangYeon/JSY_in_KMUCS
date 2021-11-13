"""
길이가 N인 통나무를 2개의 통나무로 한 번 자르는데 N원을 받는다

(힌트)
1. Dynamic Programming(DP) 방법으로 문제를 해결한다.
2. DP 로 문제를 해결할 때 필요한 recursive equation 을 먼저 구해본다.

10 3
2 6 7
통나무 길이 : L = 10
자를 곳의 개수 : K = 3
자르게 될 위치 Lk = [2, 6, 7]

예를들어, 0~2 2~10이라고하자(point 2를 잘랐다고 가정)
우선 [0~2, 2~10]로 자르는 가격은 10-0=10이고
0~2사이에 자를 point(위치)가 있다면 0~2의 자르는 가격을 구하면된다(재귀로 들어가는 부분)
또, 2~10도 사이에 자를 point가 있다면 2~10의 자를는 가격을 구하면 된다.(재귀로 들어가는 부분)

따라서 식을 정리해보면
calc(0,10) = calc(0~2) + calc(2~10) + (10-0)이다.
여기서 calc()는 주어진 범위내에서 가장 저렴한 가격으로 통나무를 자르는 가격을 리턴하는 함수이다.

그럼 재귀식을 일반화해보면,
calc(s, f, N) = 0                                               if(N < s, N > f)
                                                                s,f 사이에 자를 point가 없을 경우
               = min(calc(s, Ni, N) + calc(Ni, f, N) + (f-s)    else
                                                                s,f 사이에 i지점을 잘랐을때, 나무토막의 비용

그렇다면 코드를 어떤식으로 설계해야 할까
우선 이전의 결과를 저장하는 shape=(L+1, L+1)배열을 생성한다.
가령, m[s][f] = 10이라고 하자. 이 의미는 크기 l인 나무에서 s위치부터 f위치까지 자를경우의 최소비용이다.
    ex) m[6][10] = 12 : 길이가 10인 통나무에서 6~10인 부분 통나무를 자르는 최소비용은 12이다.

해당 m 배열을 이용해서 이전의 결과를 바로 사용하여 연산의 효율을 높인다.
calc()에서 우선, 현재 자신이 구하고자하는 s~f의 결과가 있는지 배열 m에서 찾는다. (있는경우 그 값(m[s][f]을 return)

없는 경우, 잘라야하는 point들의 배열 lk의 원소들을 하나씩 살피면서 s와 f 사이에 있는 값이 하나라도 있는지 탐색한다.
하나도 없는 경우에는 0을 리턴한다.
    왜냐하면, 0~2인 부분 통나무를 자른다 하자, 0과 2지점 사이에는 더 이상 자를 point(N = [2, 6, 7])가 없다.
    자를필요가 없기때문에 자르는 비용은 0이되는 것이다.

잘라야하는 point가 1개라도 있다면 그 지점에 대해 위에서 구한 점화식을 대입한다.(그 점화식에서 호출한 calc()도 위 코드 구성을 따른다.)

점화식을 통해서 s~f인 (부분)통나무를 자르는 최소비용을 구했다면 m[s][f]에 그 값을 갱신하고
그 값을 return 한다.
"""
import math

def calc(s,f, lk, m):
    if m[s][f] != math.inf:
        return m[s][f] # 이전에 구했던 값이면, 그 값을 리턴

    cnt = 0
    for p in lk:
        if s < p < f: # 자르는 위치가 현재 s,f 사이에 하나라도 있는지 확인
            cnt += 1
    if cnt == 0:      # 자르는 위치가 s,f 사이에 없을 경우 0를 반환
        return 0
    else:
        q = math.inf
        for i in range(len(lk)): 
            if s < lk[i] < f: # 자르려는 위치가 현재 나무의 범위내에 있을 경우에만 수행됨
                q = min(q, calc(s, lk[i], lk, m) + calc(lk[i], f, lk, m) + (f-s))
        m[s][f] = q
        return q

def solution(l, lk):
    m = [[math.inf for _ in range(l+1)] for __ in range(l+1)]
    answer = calc(0, l, lk, m)
    return answer


'''
l = 10
k = 3
lk = [2,6,7]
'''
t = int(input())
for i in range(t):
    l, k = list(map(int,input().split(' ')))
    lk = list(map(int, input().split(' ')))
    print(solution(l, lk))