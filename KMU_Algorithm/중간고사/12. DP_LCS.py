def LCS_length(x, y):
    m = len(x)
    n = len(y)

    b = [[0 for _ in range(n+1)] for _ in range(m+1)]
    c = [[0 for _ in range(n+1)] for _ in range(m+1)]

    for i in range(1, m+1):
        for j in range(1,n+1):
            q = -1
            if x[i-1] == y[j-1]:
                c[i][j] = c[i-1][j-1]+1
                b[i][j] = (-1,-1)
            elif c[i-1][j] >= c[i][j-1]:
                c[i][j] = c[i-1][j]
                b[i][j] = (-1,0)
            else:
                c[i][j] = c[i][j-1]
                b[i][j] = (0,-1)

    return c, b

def print_LCS(b, X, i, j):
    if i==0 or j==0:
        return
    if b[i][j] == (-1,-1):
        print_LCS(b,X,i-1,j-1)
        print(X[i-1], end="")
    elif b[i][j] == (-1, 0):
        print_LCS(b,X,i-1,j)
    else:
        print_LCS(b,X,i,j-1)


if __name__ == "__main__":
    x = ["A", "B", "C", "B", "D", "A", "B"]
    y = ["B", "D", "C", "A", "B", "A"]
    c, b = LCS_length(x, y)

    for row in c:
        print(row)
    print()
    for row in b:
        print(row)
    print_LCS(b, x, len(x), len(y)); print()
