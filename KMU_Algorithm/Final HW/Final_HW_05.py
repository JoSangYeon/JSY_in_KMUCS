"""
https://www.acmicpc.net/problem/2156
포도주 시식
점화식 :
    P(n) =
        0 (if n == 0)
        wine[1] (if n == 1)
        wine[1] + wine[2] (if n == 2)
        max(memo[n-1], memo[n-2] + wine[n], memo[n-3] + wine[n-1] + wine[n]) (if n >= 3)

"""
import sys

def calc(n, wine):
    if n == 1:
        return wine[1]
    if n == 2:
        return wine[1]+wine[2]

    memo = [0 for _ in range(n + 1)]
    memo[1] = wine[1]; memo[2] = wine[1] + wine[2]

    for i in range(3, n+1):
        memo[i] = memo[i-1]
        if memo[i] < memo[i-2] + wine[i]:
            memo[i] = memo[i-2] + wine[i]
        if memo[i] < memo[i-3] + wine[i-1] + wine[i]:
            memo[i] = memo[i-3] + wine[i-1] + wine[i]
    return memo[n]


if __name__ == "__main__":
    n = int(sys.stdin.readline())
    wine = [0]
    for i in range(n):
        wine.append(int(sys.stdin.readline()))

    print(calc(n, wine))
