"""
https://www.acmicpc.net/problem/1463
1로 만들기

점화식 :
P(n) = 1 (if n = 2 or 3)
     = min(P(n/3), P(n/2), P(n-1)) + 1
"""
import sys
import math

def calc(n):
    DP = [0 for _ in range(n+1)]
    DP[2] = 1
    if n >= 3:
        DP[3] = 1

    for i in range(4, n+1):
        q = math.inf
        if i % 3 == 0:
            q = DP[i//3]
        if i % 2 == 0:
            q = min(q, DP[i//2])
        DP[i] = min(q, DP[i-1])+1

    return DP[n]

if __name__ == "__main__":
    n = int(sys.stdin.readline())
    # n = int(input())
    print(calc(n))
