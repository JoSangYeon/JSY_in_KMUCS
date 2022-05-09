# 보석쇼핑

import sys
import math

"""def calc(gems):
    start = end = 0
    kinds_of_gems = len(set(gems))
    container = {}
    shortest = float("inf")
    answer = [0, len(gems)-1]

    while end < len(gems):
        if gems[end] not in container:
            container[gems[end]] = 1
        else:
            container[gems[end]] += 1
        end += 1

        if len(container) == kinds_of_gems:
            while start < end:
                if container[gems[start]] <= 1:
                    break
                container[gems[start]] -= 1
                start += 1
            if end-start < shortest:
                shortest = end-start
                answer = [start+1, end]
    return answer"""

# def calc(gems):
#     answer = [0, len(gems) - 1]
#     n = len(set(gems))
#     temp = [-1, 0]
#     cart = dict()
#     while temp[1] < len(gems):
#         gem = gems[temp[1]]
#         if gem in cart:
#             cart[gem] += 1
#         else:
#             cart[gem] = 1
#         if len(cart.keys()) == n:
#             while temp[0] < temp[1]:
#                 temp[0] += 1
#                 gem = gems[temp[0]]
#                 if cart[gem] == 1:
#                     del cart[gem]
#                     break
#                 else:
#                     cart[gem] -= 1
#             if (temp[1] - temp[0]) < (answer[1] - answer[0]):
#                 answer = temp[::]
#         temp[1] += 1
#     answer[0] += 1
#     answer[1] += 1
#     return answer


def solution(gems):
    size = len(set(gems))
    dic = {gems[0]:1}
    temp = [0, len(gems) - 1] #답이 될 수 있는 후보
    start , end = 0, 0

    while(start < len(gems) and end < len(gems)):
        if len(dic) == size:
            if end - start < temp[1] - temp[0]:
                temp = [start, end]
            if dic[gems[start]] == 1:
                del dic[gems[start]]
            else:
                dic[gems[start]] -= 1
            start += 1
        else:
            end += 1
            if end == len(gems):
                break
            if gems[end] in dic.keys():
                dic[gems[end]] += 1
            else:
                dic[gems[end]] = 1
    return [temp[0]+1, temp[1]+1]

if __name__ == "__main__":
    # gems = sys.stdin.readline().split(" ")
    gems = ["DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA" ]
    print(calc(gems))
