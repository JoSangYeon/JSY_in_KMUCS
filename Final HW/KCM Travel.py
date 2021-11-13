"""
문제
각고의 노력 끝에 찬민이는 2014 Google Code Jam World Finals에 진출하게 되었다.
구글에서 온 초대장을 받고 기뻐했던 것도 잠시, 찬찬히 읽어보던 찬민이는 중요한 사실을 알아차렸다.
최근의 대세에 힘입어 구글 역시 대기업답게 비용 감축에 열을 내고 있었던 것이다.

초대장 내용에 의하면 구글은 찬민에게 최대 M원까지의 비용만을 여행비로써 부담해주겠다고 한다.
인천에서 LA행 직항 한 번 끊어주는게 그렇게 힘드냐고 따지고도 싶었지만,
다가올 결승 대회를 생각하면 대회 외적인 곳에 마음을 쓰고 싶지 않은 것 또한 사실이었다.
그래서 찬민은 인천에서 LA까지 M원 이하로 사용하면서 도착할 수 있는 가장 빠른 길을 차선책으로 택하기로 하였다.

각 공항들간 티켓가격과 이동시간이 주어질 때,
찬민이가 인천에서 LA로 갈 수 있는 가장 빠른 길을 찾아 찬민의 대회 참가를 도와주자.

입력
입력 파일의 첫 번째 줄에 테스트 케이스의 수를 의미하는 자연수 T가 주어진다.
그 다음에는 T개의 테스트 케이스가 주어진다.

각 테스트 케이스의 첫 줄에는 공항의 수 N (2 ≤ N ≤ 100),
총 지원비용 M (0 ≤ M ≤ 10,000), 티켓정보의 수 K (0 ≤ K ≤ 10,000)가 공백으로 구분되어 주어진다.
이어서 K개의 줄에 걸쳐
    각 티켓의 출발공항 u,
    도착공항 v (1 ≤ u, v ≤ N, u ≠ v),
    비용 c (1 ≤ c ≤ M, 정수),
    그리고 소요시간 d (1 ≤ d ≤ 1,000)
가 공백으로 구분되어 주어진다.
인천은 언제나 1번 도시이고, LA는 언제나 N번 도시이다


출력
각 테스트 케이스당 한 줄에 찬민이 LA에 도착하는 데 걸리는 가장 짧은 소요시간을 출력한다.
만약, LA에 도착할 수 없는 경우 "Poor KCM"을 출력한다.

예제 입력 1
2
3 100 3
1 2 1 1
2 3 1 1
1 3 3 30
4 10 4
1 2 5 3
2 3 5 4
3 4 1 5
1 3 10 6

예제 출력 1
2
Poor KCM
"""
"""
JSY : 일단 Cost최소화 말고 Time최소화만 하는 일반적인 다익스트라 알고리즘을 풀어보자
    단, 여기서 Cost는 비행기에 탑승하기위해 지불하는 금전적인 비용이고
        Time은 비행기를 타고가는 동안 지불되는 시간비용이다.
    즉, 문제에서 주어진 c(=cost)는 무시하고 풀고, 기존의 다익스트라 알고리즘에서 weight의 개념과 같은 time을 최소화 하는
        알고리즘을 구현해보자
JSY : 예제로 준 샘플은 맞게 나오지만, 결과는 아마 틀릴것이다. 왜냐하면, 소요 시간(time)은 최소가 되었지만, 비용(cost)은
    매우 비싼 경우가 있을 것이다.
        ex) 3 100 3
            1 2 51 1
            2 3 51 1
            1 3 3 30
            해당 경우에는 1->2 and 2->3은 최소시간(2)지만, 요금은(102)로 가지고 있는 비용을 넘어선다.
            이럴 경우에는 답이 1->3로 가는 경로가 될 것이다.
    즉 지금 코드는 
"""
import sys
import math
import heapq

def init_source(n):
    distance = [math.inf for _ in range(n+1)]
    distance[1] = 0
    visited = [False for _ in range(n+1)]
    return distance, visited

def calc(graph, n, m):
    time, visited = init_source(n)
    Q = []
    heapq.heappush(Q, (time[1], 1))

    while Q:
        here_weight, here = heapq.heappop(Q)
        if visited[here]: continue

        for there,cost, weight in graph[here]:
            there_weight = here_weight + weight
            if time[there] > there_weight:
                time[there] = there_weight
                heapq.heappush(Q, (time[there], there))
    return time[1:]


if __name__ == "__main__":
    t = int(input())
    for _ in range(t):
        n,m,k = map(int, sys.stdin.readline().split(' '))
        graph = [[] for _ in range(n+1)]
        for __ in range(k):
            s, d, cost, time = map(int, sys.stdin.readline().split(' '))
            graph[s].append((d, cost, time))

        result = calc(graph, n, m)
        if result[-1] <= m:
            print(result[-1])
        else:
            print("Poor KCM")