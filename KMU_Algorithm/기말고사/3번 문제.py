"""
[
[1,0,1,1,1],
[1,0,1,0,1],
[1,0,1,1,1],
[1,1,1,0,1],
[0,0,0,0,1]]
미로 찾기 알고리즘 인거 같음.
"""
from collections import deque

def can_go(x, y, row, col):
    return (0 <= x < row) and (0 <= y < col)

def calc(maps):
    row = len(maps)
    col = len(maps[0])
    visited = [[False for _ in range(col)] for _ in range(row)]
    moves = [(-1,0), (1,0), (0,1), (0,-1)] # 북 남 동 서

    count = 1
    x = 0
    y = 0
    q = deque([[x,y,count]])
    while q:
        now_x,now_y,count = q.popleft()
        if now_x == row-1 and now_y == col-1:
            return count
        for nx,ny in moves:
            next_x, next_y = now_x+nx, now_y+ny
            # 범위내의 좌표이고, 방문하지 않았었고, 갈수있는 좌표이면,,,
            if (can_go(next_x, next_y, row, col)
                and not visited[next_x][next_y]
                and maps[next_x][next_y] == 1):
                q.append([next_x, next_y, count+1])
                visited[next_x][next_y] = True
    return -1

def solution(maps):
    answer = calc(maps)
    return answer