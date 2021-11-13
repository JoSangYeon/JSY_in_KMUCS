"""
X와 Y라는 시퀀스가 주어졌을때, 이 둘의 subsequence를 구할때 가장긴 Subsquence를 구한다.
조건 크게 3가지이다.
    1. X와 Y의 끝이 같다면, Z의 마지막 원소는 X와 Y의 마지막 원소이며, Zk-1는 Xm-1과 Yn-1의 LCS이다.
    2. X와 Y의 끝이 다르고, Z의 마지막 원소와 X의 마지막 원소가 다르면, Z는 Xm-1과 Yn의 LCS이다.
    3. X와 Y의 끝이 다르고, Z의 마지막 원소와 Y의 마지막 원소가 다르면, Z는 X와 Yn-1의 LCS이다.
위 조건을 재귀식으로 나타내면 다음과 같다.
    c[i, j] = 0                             if i=0 or j=0
              c[i-1][j-1] + 1               if i,j > 0 and Xi == Yj    => case 1
              max(c[i-1][j], c[i][j-1])     if i,j > 0 and Xi != Yj    => case 2,3

재귀식 자체로만 코드를 구현하면, 이미 구한 답을 또 구하기 때문에 시간이 오래걸린다.(brute-force의 경우 m*2^n)
따라서 DP를 통해 구하면 시간을 줄일 수 있다.
"""
def LCS_length(X,Y):
    m = len(X)
    n = len(Y)
    c = [[0 for _ in range(n+1)] for _ in range(m+1)]
    b = [[(0,0) for _ in range(n+1)] for _ in range(m+1)]

    for i in range(1, m+1):
        for j in range(1, n+1):
            if X[i-1] == Y[j-1]:
                c[i][j] = c[i-1][j-1] + 1
                b[i][j] = (-1, -1)
            elif c[i-1][j] >= c[i][j-1]:
                c[i][j] = c[i-1][j]
                b[i][j] = (-1, 0)
            else:
                c[i][j] = c[i][j-1]
                b[i][j] = (0, -1)

    return c, b

def print_LCS(b, X, i, j):
    if i==0 or j==0:
        return
    if b[i][j] == (-1,-1):
        print_LCS(b, X, i-1, j-1)
        print(X[i-1], end=" ")
    elif b[i][j] == (-1, 0):
        print_LCS(b, X, i-1, j)
    else:
        print_LCS(b, X, i, j-1)

if __name__ == "__main__":
    x = ["A", "B", "C", "B", "D", "A", "B"]
    y = ["B", "D", "C", "A", "B", "A"]
    c, b = LCS_length(x, y)

    for row in c:
        print(row)
    print()
    # for row in b:
    #     print(row)
    print_LCS(b, x, len(x), len(y))
