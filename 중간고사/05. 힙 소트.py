def max_heapify(data, size, i):
    l = 2*i+1
    r = 2*i+2
    largest = i

    if l < size and data[largest] < data[l]:
        largest = l
    if r < size and data[largest] < data[r]:
        largest = r

    if largest != i:
        data[i], data[largest] = data[largest], data[i]
        max_heapify(data, size, largest)

def build_heap(data):
    n = len(data)//2-1
    for i in range(n,-1,-1):
        max_heapify(data, len(data), i)

def heap_sort(data):
    build_heap(data)
    n = len(data)
    for i in range(n-1, -1, -1):
        data[0], data[i] = data[i], data[0]
        n -= 1
        max_heapify(data,n,0)

if __name__ == "__main__":
    a = [4, 1, 3, 2, 16, 9, 10, 14, 8, 7]
    heap_sort(a)
    print(a)