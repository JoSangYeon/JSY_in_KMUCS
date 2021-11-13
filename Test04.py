"""
Rod cutting n인치 막대를 잘라서 판매하여 얻을 수 있는 최대수익 Rn을 찾는다.
막대를 자르는 비용은 0
    예를들어) 4인치 막대를 자르는 방법은 8가지(=2^3)가 있다.

Rn은 Rn-1, Rn-2,,, R1으로부터 구할 수 있다.
"""
p = [1, 5, 8, 9, 10, 17, 17, 20, 25, 30] # price_array

# Recursive Top-Down 방식
# T(n) 2^n
# 결국 Brute force와 동일
def cut_rod(n):
    if n == 0:
        return 0
    else:
        q = -1
        for i in range(1,n+1):
            q = max(q, p[i-1]+cut_rod(n-i))
    return q


# DP 요소 적용 : Top-Down 방식
# if r[n] > -1의 의미가 이전에 Optimal한 결과 있을경우 그 결과를 이용한다는 의미이다.
# 따라서 Dp를 적용하기 때문에 불필요한 계산이 적고 시간이 적게 걸리는 것
# T(n) = n ^ 2
def memoized_cut_rod(n):
    r = [-1 for _ in range(n+1)]
    return memoized_cut_rod_aux(n, r)

def memoized_cut_rod_aux(n,r):
    if r[n] != -1:
        return r[n]
    if n==0:
        return 0
    else:
        q = -1
        for i in range(1, n+1):
            q = max(q, p[i-1]+memoized_cut_rod_aux(n-i, r))
        r[n] = q
        return q


# Bottom-Up방식
# T(n) = n^2
# 같은 시간 복잡도지만, 함수 호출 시간의 over-head를 고려한다면 top-down 방식보다 빠르다.
def bottom_up_cut_rod(n):
    r = [-1 for _ in range(n+1)]; r[0]=0

    for j in range(1,n+1):
        q = -1
        for i in range(1, j+1):
            q = max(q, p[i-1]+r[j-i])
        r[j] = q
    return r[n]


# Bottom-Up 방식
# + 어떻게 잘라야 하나?(optimal한 경우를 저장 -> s)
def extended_bottom_up_cut_rod(n):
    r = [-1 for _ in range(n+1)]; r[0]=0
    s = [0 for i in range(n+1)]

    for j in range(1, n+1):
        q = -1
        for i in range(1, j+1):
            if q < p[i-1]+r[j-i]:
                q = p[i-1]+r[j-i]
                s[j] = i
        r[j] = q
    return r,s

def print_rod_cut(n,r,s):
    print(n, end="= ")
    while n > 0:
        print(s[n], end=", ")
        n -= s[n]

if __name__ == "__main__":
    # print(cut_rod(4))
    # print(memoized_cut_rod(4))
    # print(bottom_up_cut_rod(4))
    r, s = extended_bottom_up_cut_rod(10)
    print_rod_cut(8,r,s)