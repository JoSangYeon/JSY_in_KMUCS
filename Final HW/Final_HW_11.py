"""
https://programmers.co.kr/learn/courses/30/lessons/81305
시험장 나누기
"""
import sys

sys.setrecursionlimit(1000000)

left = [0] * 10005
right = [0] * 10005

x = [0] * 10005
p = [-1] * 10005
n = 0
root = 0
now = 0


def dfs(now, lim):
    global count

    lc = 0
    if left[now] != -1:
        lc = dfs(left[now], lim)
    rc = 0
    if right[now] != -1:
        rc = dfs(right[now], lim)

    if x[now] + lc + rc <= lim:
        return x[now] + lc + rc
    if x[now] + min(lc, rc) <= lim:
        count += 1
        return x[now] + min(lc, rc)
    count += 2
    return x[now]


def solve(lim):
    global count
    count = 0

    dfs(root, lim)
    count += 1
    return count


def solution(k, num, links):
    global root
    n = len(num)

    for i in range(n):
        left[i], right[i] = links[i]
        x[i] = num[i]
        if left[i] != -1:
            p[left[i]] = i

        if right[i] != -1:
            p[right[i]] = i

    for i in range(n):
        if p[i] == -1:
            root = i
            break
    answer = max(x)
    end = 10 ** 8

    while answer < end:
        mid = (answer + end) // 2
        if solve(mid) <= k:
            end = mid
        else:
            answer = mid + 1
    return answer