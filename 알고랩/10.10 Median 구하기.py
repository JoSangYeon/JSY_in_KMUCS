"""
https://pongdangstory.tistory.com/471
"""
import heapq

def median(data):
    result = []
    max_heap = []
    min_heap = []

    medi = 0
    for num in data:
        if num > medi:
            heapq.heappush(min_heap, num)
        else:
            heapq.heappush(max_heap, -num)

        if abs(len(max_heap)-len(min_heap)) > 1:
            if len(max_heap) > len(min_heap):
                heapq.heappush(min_heap, -heapq.heappop(max_heap))
            else:
                heapq.heappush(max_heap, -heapq.heappop(min_heap))

        if (len(max_heap)+len(min_heap)) % 2 == 0:
            medi = (-max_heap[0] + min_heap[0]) // 2
        else:
            if len(max_heap) > len(min_heap):
                medi = -max_heap[0]
            else:
                medi = min_heap[0]
        result.append(medi)

    return calc(result)

def calc(result):
    sum = 0
    for r in result:
        sum = (sum+r)%10
    return sum

n = int(input())
for i in range(n):
    data = list(map(int, input().split()))[1:]
    result = median(data)
    print(result)