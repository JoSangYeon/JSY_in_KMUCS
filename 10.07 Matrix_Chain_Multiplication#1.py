"""
인덱스를 무조건 0부터 셌을 경우
이건 진짜 머리 꺠짐ㅋㅋㅋ
"""

import math

def matrix_chain_order(p):
    n = len(p)-1
    m = [[0 for _ in range(n)] for _ in range(n)]
    s = [[0 for _ in range(n-1)] for _ in range(n-1)]

    for l in range(2,n+1):
        for i in range(n-l+1):
            j = i+l-1
            m[i][j] = math.inf
            for k in range(i,j):
                q = m[i][k] + m[k+1][j] + (p[i]*p[k+1]*p[j+1])
                if q < m[i][j]:
                    m[i][j] = q
                    s[i][j-1] = k
    print(m)
    print(s)
    return m,s

def print_optimal_parens(s,i,j):
    if i==j+1:
        print("A{}".format(i+1), end="")
    else:
        print("(", end="")
        print_optimal_parens(s,i,s[i][j]-1)
        print_optimal_parens(s,s[i][j]+1,j)
        print(")", end="")

if __name__ == "__main__":
    p = [30,35,15,5,10,20,25]
    m,s = matrix_chain_order(p)
    print_optimal_parens(s,0,len(s)-1)