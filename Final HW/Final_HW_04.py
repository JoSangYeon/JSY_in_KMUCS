import sys
import math

def calc(gems):
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
    return answer

if __name__ == "__main__":
    # gems = sys.stdin.readline().split(" ")
    gems = ["DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA" ]
    print(calc(gems))
