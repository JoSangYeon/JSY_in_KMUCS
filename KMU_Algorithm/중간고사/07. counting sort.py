def counting_sort(data):
    m = max(data)
    p = [0 for _ in range(m+1)]
    b = [0 for _ in range(len(data))]

    for d in data:
        p[d] += 1
    for i in range(1,len(p)):
        p[i] += p[i-1]

    for i in range(len(data)-1,-1,-1):
        p[data[i]] -= 1
        b[p[data[i]]] = data[i]

    return b

if __name__ == "__main__":
    a = [2,8,7,1,3,5,6,4,5,9,1,4,2,6,7,2,0,3,1]
    r = counting_sort(a)
    print(r)