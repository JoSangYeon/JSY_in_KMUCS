import heapq
import math

def init_single_source(g, s=1):
    distance = [math.inf for _ in range(len(g))]
    distance[s] = 0
    return distance

def calc(graph, k):
    s = 1
    distance = init_single_source(graph, 1)
    Q = []
    heapq.heappush(Q, (distance[s], s))

    while Q:
        u_distance, u_vertex = heapq.heappop(Q)
        for i in range(1, len(graph[u_vertex])):
            weight = graph[u_vertex][i]
            if weight != 0 and u_vertex != i:
                dist_tmp = u_distance+graph[u_vertex][i]
                if dist_tmp < distance[i]:
                    distance[i] = dist_tmp
                    heapq.heappush(Q, (dist_tmp, i))
    return distance[1:]


def solution(n, g, k):
    graph = [[0 for _ in range(n+1)] for __ in range(n+1)]
    for r in g:
        if graph[r[0]][r[1]] == 0:
            graph[r[0]][r[1]] = r[2]
            graph[r[1]][r[0]] = r[2]
        else:
            graph[r[0]][r[1]] = min(r[2],graph[r[0]][r[1]])
            graph[r[1]][r[0]] = graph[r[0]][r[1]]

    # for row in graph:
    #     print(row)

    result = calc(graph,k)
    answer = 0
    for data in result:
        if data <= k:
            answer += 1
    return answer

if __name__ == "__main__":
    # n = 5
    # road = [[1,2,1],[2,3,3],[5,2,2],[1,4,2],[5,3,1],[5,4,2]]
    # k = 3

    n = 6
    road = 	[[1,2,1],[1,3,2],[2,3,2],[3,4,3],[3,5,2],[3,5,3],[5,6,1]]
    k = 4

    print(solution(n, road, k))