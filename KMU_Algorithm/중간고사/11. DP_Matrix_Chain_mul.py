import math

def matrix_chain_order(p):
    n = len(p)-1
    m = [[0 for _ in range(n+1)] for _ in range(n+1)]
    s = [[0 for _ in range(n+1)] for _ in range(n+1)]

    for l in range(2, n+1):
        for i in range(1, (n-l+1)+1):
            j = i+l-1
            m[i][j] = math.inf
            for k in range(i, j):
                q = m[i][k] + m[k+1][j] + (p[i-1]*p[k]*p[j])
                if q < m[i][j]:
                    m[i][j] = q
                    s[i][j] = k
    return m,s

def print_optimal_parens(s,i,j):
    if i == j :
        print("A{}".format(i), end="")
    else:
        print("(",end="")
        print_optimal_parens(s,i,s[i][j])
        print_optimal_parens(s,s[i][j]+1, j)
        print(")",end="")

def print_arr(data):
    for i in range(len(data)):
        print(data[i])
    print()

if __name__ == "__main__":
    """
    30X35, 35X15, 15X5, 5X10, 10X20, 20X25
    """
    p = [30,35,15,5,10,20,25]
    m,s = matrix_chain_order(p)
    print_arr(m)
    print_arr(s)
    print_optimal_parens(s,1,len(p)-1);print()