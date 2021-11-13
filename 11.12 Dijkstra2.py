"""
DIJKSTRA의 알고리즘을 이용하여 특정 vertex에서 다른 vertex의 간의 최소 경로를 찾아낸다.
(문제에서는 시작 vertex를 1로 설정하였다.)

n = 5
road = [[1,2,1],[2,3,3],[5,2,2],[1,4,2],[5,3,1],[5,4,2]] # 1 -> 2 : cost = 1
k = 3

다이제스트라 알고리즘의 핵심은 현재의 Node(vertex)에서 모든 edge정보를 확인해서 이웃의 Node의 거리를 최신화해 나가는 방법이다.
가령, 시작 Node가 1이라하면 Node1에서 Node1로 가는 비용은 0이다.(자기자신이기 때문)

우선 각 노드의 거리의 최소비용을 저장하는 distance 배열을 생성한다.
시작 Node를 제외한 모든 값은 math.inf로 설정한다.
    ex) distance = [_, 0, inf, inf, inf, inf]
가령, distance[2] = 10 이라는 것은 시작노드(여기선 1)에서 2번 노드까지 가는 최소비용이 10이라는 것이다.
    이 최소비용은 문제에서 주어진 Edge정보들에 의해 계속해서 값이 변하며 최소에 수렴하게 된다.

그리고 최소힙(우선 순위 Q)에 시작노드의 거리비용과 시작노드의 번호를 대입한다.
    ex) Q.insert((distance[start]=0, start=1))

이제 모든 Edge정보에 대해서 최소 경로를 찾는 작업이 들어간다.
Q가 Empty일때까지 반복하며, 매 반복마다. Q에서 거리정보와 현재 vertex(Node)의 정보를 pop한다.
현재 초기상태에선 시작노드의 거리값(0)과 시작노드(1)이 들어있을테니 그 값을 Q에서 pop한다.
    ex) now_distance, now_node = Q.pop()

이제 문제에서 주어진 Edge 정보(road)를 모두 비교하며 거리정보를 갱신한다.
현재 now_node와 관련된 Edge정보는 아래와 같다. [v1,v2,cost]
    v1,v2중 하나가 now_node와 같아야한다.
road[0] = [1,2,1] : Node1 -> Node2 : cost=1
road[3] = [1,4,2] : Node1 -> Node4 : cost=2
인데, 현재 road[0]를 보면,
    v1 = now_node = 1
    v2 = target_node = 2
    cost = 1
    즉, 시작노드에서 2번 노드로 가는 Edge의 weight는 1이라는 것이다.
    여기서 해야할 것은 현재 자신이 알고있는 거리정보(=distance)에 저장되어 있는 target_node의 거리값과
    해당 Edge정보를 통해서 알게된 now_node로 부터 target_node로 가는 값을 비교하는 것이다.
    정리해보면, distance[target_node]와 (now_distance+cost)를 비교하는것이다.

    원래 자신이 알던 거리값(distance[target_node)보다 새로이 알게된 거리정보값((now_distance+cost))이 더 작다면,
    그 값을 갱신한다. distance[target_node] = (now_distance+cost)
    거리값을 갱신했으니(일단 target_node로 가는 최소거리비용을 알았으니) 그 target_node와 연결되어 있는 다른 node들에
    대해서도 그 값을 구해야한다. 따라서 Q에 해당 node의 거리값과 해당 node를 삽입한다.
        ex) Q.insert((distance[target_node], target_node))

위 과정을 반복하면(Q가 empty가 될때까지) 최종적으로는 시작 vertex부터 다른 vertex까지의 최소경로를 구할 수 있게된다.
가령, distance = [_, 0, 1, 3, 5, 3]이라면,
    distance[0] = "무의미값":인덱스를 맞춰주기위해 채워둔 값
    distance[1] = 자기자신으로 가는 최소비용(당연히 0)
    distance[2] = v1 -> v2로 가는 최소비용은 1
    distance[3] = v2 -> v3로 가는 최소비용은 3
    ...
    ..
    .
    이 되겠다.
"""
import heapq
import math

def init_single_source(n, s=1):
    distance = [math.inf for _ in range(n+1)]
    distance[s] = 0
    return distance

def relax(distance, there, there_cost, Q):
    if distance[there] > there_cost:
        distance[there] = there_cost
        heapq.heappush(Q, (there_cost, there))

def calc(n, graph):
    distance = init_single_source(n, 1)
    Q = []
    heapq.heappush(Q, (distance[1], 1))

    while Q:
        here_cost, here = heapq.heappop(Q)

        for i in range(len(graph)):
            if here == graph[i][0]:
                there_cost, there = graph[i][2]+here_cost, graph[i][1]
                relax(distance, there, there_cost, Q)
            elif here == graph[i][1]:
                there_cost, there = graph[i][2]+here_cost, graph[i][0]
                relax(distance, there, there_cost, Q)
    return distance[1:]


def solution(n, g, k):
    result = calc(n,g)
    answer = 0
    for data in result:
        if data <= k:
            answer += 1

    # print(result)
    return answer

if __name__ == "__main__":
    n = 5
    road = [[1,2,1],[2,3,3],[5,2,2],[1,4,2],[5,3,1],[5,4,2]]
    k = 3

    # n = 6
    # road = 	[[1,2,1],[1,3,2],[2,3,2],[3,4,3],[3,5,2],[3,5,3],[5,6,1]]
    # k = 4

    print(solution(n, road, k))