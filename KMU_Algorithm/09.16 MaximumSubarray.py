import math

def solution(data):
    answer = find_maximum_subarray(data, 0, len(data) - 1)
    return answer


def find_maximum_subarray(data, s, e):
    if s == e:
        return data[s]
    else:
        mid = (s + e) // 2
        left_sum = find_maximum_subarray(data, s, mid)
        right_sum = find_maximum_subarray(data, mid + 1, e)
        cross_sum = find_max_crossing_subarray(data, s, mid, e)

        if left_sum >= right_sum and left_sum >= cross_sum:
            return left_sum
        elif right_sum >= left_sum and right_sum >= cross_sum:
            return right_sum
        else:
            return cross_sum


def find_max_crossing_subarray(data, s, mid, e):
    left_sum = -math.inf
    sum = 0
    for i in range(mid, s-1, -1):
        sum += data[i]
        if sum > left_sum:
            left_sum = sum

    right_sum = -math.inf
    sum = 0
    for i in range(mid + 1, e+1):
        sum += data[i]
        if sum > right_sum:
            right_sum = sum

    return left_sum + right_sum


if __name__ == '__main__':
    print(solution([21, 3, -5, 45, -66, -75, -95]))