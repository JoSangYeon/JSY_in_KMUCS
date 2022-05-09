def insertion_sort(data):
    for i in range(len(data)):
        key = data[i]
        idx = i
        for j in range(i,-1,-1):
            if key < data[j]:
                data[j], data[idx] = data[idx], data[j]
                idx = j

def bucket_sort(data):
    n = len(data)
    b = [[] for _ in range(n)]

    for i in range(n):
        b[int(n*data[i])].append(data[i])

    for i in range(n):
        insertion_sort(b[i])
    return sum(b, [])

if __name__ == "__main__":
    a = [0.78,0.17,0.39,0.26,0.72,0.94,0.21,0.12,0.23,0.68]
    r = bucket_sort(a)
    print(r)