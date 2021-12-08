import sys
def get_max_area(arr):
    res = 0
    l,r = 0, len(arr)-1

    while r>l :
        area = (r-l) * min(arr[l], arr[r])
        res = max(res, area)

        if arr[l] <= arr[r]:
            l += 1
        else:
            r -= 1
    return res


if __name__ == "__main__":
    # height = list(map(int, sys.stdin.readline().split(" ")))
    height = [1,8,6,2,5,4,8,3,7]
    print(get_max_area(height))
