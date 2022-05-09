"""
포도주 문제랑 비슷하다.
n-3  n-2  n-1  n
 ?    ?    ?   X
 ?    X    X   O
 ?    X    O   O    
 X    O    O   O

1. memo[i-1] : 자기자신을 더하면 연속 4개여서 자기 자신을 뺏을 경우의 수
2. memo[i-2] + cards[i] : i-1까지 수를 빼고 자기 자신과 i-2까지 더한 수
3. memo[i-3] + cards[i-1] + cards[i] : i-3까지 더한 수(최대)에서 자기와 자신의 전 카드 값을 더한 수
4. memo[i-4] + cards[i-2]+cards[i-1]+cards[i]
            : i-4까지 카드핪의 최대와 i-2 ~ i(자신 -2 부터 자신)까지의 카드 합

memo[i] = max(memo[i-1], 
             memo[i-2]+cards[i],
             memo[i-3]+cards[i-1]+cards[i],
             memo[i-4]+cards[i-2]+cards[i-1]+cards[i])
"""
def calc(cards):
    n = len(cards)-1
    if n == 1 or n == 2 or n == 3:
        return sum(cards)

    memo = [0 for _ in range(n+1)]
    memo[1] = sum(cards[:2])
    memo[2] = sum(cards[:3])
    memo[3] = sum(cards[:4])

    for i in range(4, n+1):
        memo[i] = memo[i-1]
        if memo[i] < memo[i-2]+cards[i]:
            memo[i] = memo[i-2]+cards[i]
        if memo[i] < memo[i-3]+cards[i-1]+cards[i]:
            memo[i] = memo[i-3]+cards[i-1]+cards[i]
        if memo[i] < memo[i-4]+cards[i-2]+cards[i-1]+cards[i]:
            memo[i] = memo[i-4]+cards[i-2]+cards[i-1]+cards[i]
    return memo[n]

def solution(cards):
    cards = [0] + cards
    answer = calc(cards)
    return answer