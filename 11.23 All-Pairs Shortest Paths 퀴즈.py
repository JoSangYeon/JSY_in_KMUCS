def calc(n, signs):
    result = list(signs)
    for r in range(n):
        for s in range(n):
            for e in range(n):
                if result[s][r] == 1 and result[r][e] == 1:
                    result[s][e] = 1
    return result

def solution(n,signs):
    answer = calc(n, signs)

    return answer