"""
거스름 돈을 동전으로 교환할 때, 동전의 개수가 가장 적게 교환하는 방법을 찾는 프로그램을 작성
dp를 이용

n = 287 // 거슬러 줄 돈
m = 6   // 동전의 개수
coin = [1, 5, 10, 25, 50, 100] // 동전의 타입

[0~n]인 배열을 dp라 가정, dp[i]는 i원을 거슬러줄때 사용된 동전의 최소 개수
주어진 동전들을 이용해서 0원일때부터 n원일때까지의 거슬러줄 수 있는 동전의 최소 개수를 구한다.
coin[0] 부터 coin[m-1]까지 계산(여기서 coin의 입력은 오름차순)
1원 동전 만으로 계산할떄, 0원부터 287원 각각 0개~287개의 동전이 필요함
0,5원 동전만으로 계산할때, 5원 이상부터는 개수를 생략할 수 있다.
    dp[5] = dp[5-5]+1 : 5 => 1(0+1)
    dp[6] = dp[6-5]+1 : 6 => 2(1+1)
    dp[7] = dp[7-5]+1 : 7 => 3(2+1)
    dp[8] = dp[8-5]+1 : 8 => 4(3+1)
    do[10] = dp[10-5]+1 : 10 =>2(1+1)
    dp[13] = dp[13-5]+1 : 13 => 5(4+1) => 5원 2개와 1원 3개
0,5,10원 동전만으로 계산할 때, 10원 이상부터는 그 개수를 생략할 수 있다.
.
.
.
계속해서 계산하면 마지막 287원일때의 최소 계수를 구할 수 ㅇㅆ다.

https://reakwon.tistory.com/41
"""
import math

def calc(n, m, coin):
    dp = [math.inf for _ in range(n+1)]
    dp[0] = 0
    for c in coin:
        for j in range(c, n+1):
            dp[j] = min(dp[j], dp[j-c]+1)
    return dp[-1]

def solution(n, m, coin):
    answer = calc(n, m, coin)
    return answer

t = int(input())
for i in range(t):
    n = int(input())
    tmp = list(map(int, input().split(' ')))
    m, coin = tmp[0], tmp[1:]
    print(solution(n, m, coin))