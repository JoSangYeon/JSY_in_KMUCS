# def solution(n, stations, w):
#     answer = 0
#     idx = 0 # station 에 대한 것
#     i = 1
#     while i <= n:
#         if idx < len(stations) and stations[idx] - w <= i <= stations[idx] + w:
#             i = stations[idx] + w + 1
#             idx += 1
#         else:
#             i += 2 * w + 1
#             answer += 1
#     return answer
"""
answer 구하는 방법:
        올림(전파가 닿지 않는 거리 / (기지국 위치 + 전파거리*2))
        ex) 전파가 닿지 않는거리 = 6, 전파거리 = 2, 기지국 위치 = 1
                기지국 하나 설치시 커버하는 범위는 2*2(전파거리) + 기지국 설치위치(예에선 1) = 2*2 + 1 = 5
                따라서, 6의 거리를 커버하려면 기지국 2개가 필요하며 이는 6/5의 값을 올림 한 값이다.
"""

import math
def solution(n, stations, w):
    result = 0
    distance = []

    for i in range(1, len(stations)):
        distance.append((stations[i] - w - 1) - (stations[i - 1] + w))

    distance.append(stations[0] - w - 1)
    distance.append(n - (stations[-1] + w))

    for dist in distance:
        if dist <= 0: continue
        result += math.ceil(dist / ((w * 2) + 1))

    return result


if __name__ == "__main__":
    n = 11
    stations = [4, 11]
    w = 1
    print(solution(n,stations,w))