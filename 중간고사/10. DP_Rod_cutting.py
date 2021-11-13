"""
n인치 막대를 잘라서 판매하여 얻을 수 있는 최대 수익 rn을 찾는
"""
def rod_cut(n):
    r = [-1 for _ in range(n+1)]; r[0]=0
    s = [0 for _ in range(n+1)]

    for i in range(1,n+1):
        q = -1
        for j in range(1, i+1):
            if q < p[j-1]+r[i-j]:
                q = p[j-1]+r[i-j]
                s[i] = j
        r[i] = q
    return r,s

def print_rod(n,s):
    while n > 0:
        print(s[n], end = " ")
        n -= s[n]
    print()


if __name__ == "__main__":
    p = [1, 5, 8, 9, 10, 17, 17, 20, 25, 30] # price_array
    r,s = rod_cut(5)
    print(r)
    print(s)
    print_rod(5,s)
