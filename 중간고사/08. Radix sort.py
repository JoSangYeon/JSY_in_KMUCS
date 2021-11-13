def get_point(num, key):
    return int(str(num%10**(key+1))[0])

def counting_sort(data, key):
    k = 10
    p = [0 for _ in range(k)]
    b = [0 for _ in range(len(data))]

    for d in data:
        p[get_point(d,key)] += 1
    for i in range(1,len(p)):
        p[i] += p[i-1]

    for i in range(len(data)-1,-1,-1):
        p[get_point(data[i],key)] -= 1
        b[p[get_point(data[i],key)]] = data[i]

    return b

def radix_sort(data):
    m = max(data)
    for i in range(len(str(m))):
        data = counting_sort(data, i)
    return data

if __name__ == "__main__":
    a = [329,457,657,839,436,720,355]
    r = radix_sort(a)
    print(r)