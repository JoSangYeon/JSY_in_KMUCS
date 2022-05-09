def left(i):
    return 2*i+1


def right(i):
    return 2*i+2


def max_heapify(data, size, i):
    l = left(i)
    r = right(i)

    largest = i

    if l < size and data[l] > data[largest]:
        largest = l

    if r < size and data[r] > data[largest]:
        largest = r

    if largest != i:
        data[largest], data[i] = data[i], data[largest]
        max_heapify(data, size, largest)


def build_max_heap(data):
    n = len(data)//2-1
    for i in range(n, -1, -1):
        max_heapify(data, len(data), i)


def heapsort(data, k):
    build_max_heap(data)
    size = len(data)
    for i in range(size-1, k-1, -1):
        data[i], data[0] = data[0], data[i]
        size -= 1
        max_heapify(data, size, 0)


def solution(a, k):
    heapsort(a, k)
    return a


if __name__ == "__main__":
    r = solution([4, 1, 3, 2, 16, 9, 10, 14, 8, 7], 1)
    print(r)