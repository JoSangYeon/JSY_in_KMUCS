import math

INF = math.inf
NIL = -1

def Faster_All_pairs_Shortest_Paths(w):
    n = len(w)
    ls = [[] for _ in range(n+1)]
    ls[1] = w
    m = 1
    while m < n-1:
        ls[2*m] = Extend_Shortest_Paths(ls[m], ls[m])
        m *= 2
    return ls[m]

def Extend_Shortest_Paths(L, W):
    n = len(L)
    new_L = [[INF for _ in range(n)] for _ in range(n)]
    for i in range(n):
        for j in range(n):
            for k in range(n):
                new_L[i][j] = min(new_L[i][j], L[i][k]+W[k][j])
    return new_L

def Floyd_Warshall(W):
    n = len(W)
    ds = [[] for _ in range(n+1)]; ds[0] = W

    pi = [[NIL if i==j or W[i][j] == INF else i for j in range(n)]for i in range(n)]

    for k in range(1, n+1):
        dk = list(ds[k-1])
        for i in range(n):
            for j in range(n):
                # dk[i][j] = min(ds[k-1][i][j], ds[k-1][i][k-1]+ds[k-1][k-1][j])
                if ds[k-1][i][j] <= ds[k-1][i][k-1]+ds[k-1][k-1][j]:
                    dk[i][j] = ds[k-1][i][j]
                    pi[i][j] = pi[i][j]
                else:
                    dk[i][j] = ds[k-1][i][k-1]+ds[k-1][k-1][j]
                    pi[i][j] = pi[k-1][j]
        ds[k] = dk
    return ds[n], pi

def print_All_Pairs_Shortest_Path(pi, i, j):
    if i == j:
        print(i+1 ,end = " ")
    elif pi[i][j] == NIL:
        print("no path from {} to {} exist".format(i+1, j+1))
    else:
        print_All_Pairs_Shortest_Path(pi, i, pi[i][j])
        print(j+1, end=" ")

if __name__ == "__main__":
    w = [
        [0,3,8,INF,-4],
        [INF,0,INF,1,7],
        [INF,4,0,INF,INF],
        [2,INF,-5,0,INF],
        [INF,INF,INF,6,0]
    ]

    pi5 = [
        [NIL,2,3,4,0],
        [3,NIL,3,1,0],
        [3,2,NIL,1,0],
        [3,2,3,NIL,0],
        [3,2,3,4,NIL]
    ]

    # L = Faster_All_pairs_Shortest_Paths(w)
    D, pi = Floyd_Warshall(w)
    for row in D:
        print(row)
    print("### "*10)
    for row in pi:
        print(row)
    print_All_Pairs_Shortest_Path(pi,len(w)-1, 1)