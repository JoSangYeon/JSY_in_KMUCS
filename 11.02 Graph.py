"""
0. 예제로 사용할 Graph(Directed and Undirected Graph) 생성
    1. 인접 리스트로?
    2. 인접 행렬로 ?
    3. 둘다?
1. BFS 구현
    0. BFS는 구현할때, Undirected Graph로..?
2. DFS 구현
    0. DFS는 대부분 Directed Graph임
    1. Topological sort 구현(예제 그래프 필요)
    2. Strongly connected Graph(예제 그래프 필요)
3. Euler Tour 공부?->이건 뭐임??ㅋㅋㅋ
"""
"""BFS 예제 Graph의 인접행렬"""
node_name = ['r', 's', 't', 'u', 'v', 'w', 'x', 'y']
matrix1 = [[0, 1, 0, 0, 1, 0, 0, 0], #r
           [1, 0, 0, 0, 0, 1, 0, 0], #s
           [0, 0, 0, 1, 0, 1, 1, 0], #t
           [0, 0, 1, 0, 0, 0, 1, 1], #u
           [1, 0, 0, 0, 0, 0, 0, 0], #v
           [0, 1, 1, 0, 0, 0, 1, 0], #w
           [0, 0, 1, 1, 0, 1, 0, 1], #x
           [0, 0, 0, 1, 0, 0, 1, 0]] #y
list1 = {
    0:set([1,4]),
    1:set([0,5]),
    2:set([3,5,6]),
    3:set([2,6,7]),
    4:set([0]),
    5:set([1,2,6]),
    6:set([2,3,5]),
    7:set([3,6])
}

"""DFS 예제 Graph의 인접행렬1"""
matrix2 = [[0, 1, 0, 1, 0, 0], #u
           [0, 0, 0, 0, 1, 0], #v
           [0, 0, 0, 0, 1, 1], #w
           [0, 1, 0, 0, 0, 0], #x
           [0, 0, 0, 1, 0, 0], #y
           [0, 0, 0, 0, 0, 1]] #z
list2 = {
    0:set([1,3]),
    1:set([4]),
    2:set([4,5]),
    3:set([1]),
    4:set([3]),
    5:set([5]),
}

"""DFS 예제 Graph의 인접행렬2"""
matrix3 = [[0, 0, 0, 0, 1, 0, 0, 1], #s
           [0, 0, 1, 1, 0, 0, 0, 0], #t
           [0, 1, 0, 1, 0, 0, 0, 0], #u
           [1, 0, 0, 0, 1, 0, 0, 0], #v
           [0, 0, 0, 0, 0, 1, 0, 0], #w
           [0, 0, 0, 0, 0, 0, 0, 1], #x
           [0, 0, 0, 0, 0, 1, 0, 0], #y
           [0, 0, 0, 0, 1, 0, 1, 0]] #z
list3 = {
    0:set([4,7]),
    1:set([2,3]),
    2:set([1,3]),
    3:set([0,4]),
    4:set([5]),
    5:set([7]),
    6:set([5]),
    7:set([4,6])
}

from collections import deque

def BFS(graph, root):
    visited = []
    queue = deque([root])

    while queue:
        u = queue.popleft()
        if u not in visited:
            visited.append(u)
            queue += graph[u] - set(visited) #방문했던 node를 제외하고 인접한 node를 queue 대입
    return visited

def DFS(graph, root):
    visited = []
    stack = [root]

    while stack:
        u = stack.pop()
        if u not in visited:
            visited.append(u)
            stack += graph[u] - set(visited) #방문했던 node를 제외하고 인접한 node를 queue 대입
    return visited

if __name__ == "__main__":
    # result = BFS(list1, 1)
    result = DFS(list3, 0)

    for node in result:
        print(node_name[node], end=" ")
    print()