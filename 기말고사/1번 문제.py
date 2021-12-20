# 그리디 알고리즘
# 그리디 셀렉션

def calc(arr):
    cnt = 1

    sf = arr[0][1]
    for i in range(1, len(arr)):
        if sf <= arr[i][0]:
            sf = arr[i][1]
            cnt += 1
    return cnt

def solution(arr):
    # arr을 종료시간을 기준으로 정렬
    sort_key = lambda x : (x[1], x[0])

    arr = sorted(arr, key=sort_key)
    return calc(arr)