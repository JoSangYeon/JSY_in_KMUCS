import math

def get_maximum_arr(data,low,high):
    if low==high:
        return data[low]
    else:
        mid = (low+high)//2
        left_sum = get_maximum_arr(data,low,mid)
        right_sum = get_maximum_arr(data,mid+1,high)
        cross_sum = get_cross_maximum_arr(data,low,mid,high)

    return max(left_sum, right_sum, cross_sum)

def get_cross_maximum_arr(data, low, mid, high):
    left_sum = -math.inf
    sum = 0
    for i in range(mid,low-1,-1):
        sum += data[i]
        if left_sum < sum:
            left_sum = sum

    right_sum = -math.inf
    sum = 0
    for i in range(mid+1, high+1):
        sum += data[i]
        if right_sum < sum:
            right_sum = sum
    return left_sum+right_sum

if __name__ == "__main__":
    a = [21, 3, -5, 45, -66, -75, -95]
    r = get_maximum_arr(a, 0, len(a)-1)
    print(r)