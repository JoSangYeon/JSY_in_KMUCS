from sys import stdin
from heapq import *

t = int(stdin.readline())
for _ in range(t):
    n = int(stdin.readline())
    edges = {v: {} for v in range(1, n + 1)}
    for i in range(1, n + 1):
        arr = list(map(int, stdin.readline().split()))
        for v, d in zip(arr[2::2], arr[3::2]):
            edges[i][v] = d

    dis = [0, 0] + [float('inf')] * (n - 1)
    res = dis[:]
    q = [[0, 1]]
    while q:
        d0, v0 = heappop(q)
        for v, d in edges[v0].items():
            if d0 + d < dis[v]:
                dis[v] = d0 + d
                res[v] = d
                heappush(q, [d0 + d, v])
    print(sum(res))