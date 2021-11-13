def digit16(v,d):
    v = v >> d*4
    return v & 0xf

def countingSort16(data, d):
    k = 16
    cnt_arr = [0 for _ in range(k)]
    copy_data = list(data)

    for i in range(len(data)):
        cnt_arr[digit16(data[i][0],d)] += 1

    for i in range(1, k):
        cnt_arr[i] += cnt_arr[i-1]

    n = len(data)-1
    for i in range(n,-1,-1):
        copy_data[cnt_arr[digit16(data[i][0],d)]-1] = data[i]
        cnt_arr[digit16(data[i][0],d)] -= 1
    return copy_data

if __name__ == "__main__":
    # print(digit16(78320238,0))
    max = -1
    data = []
    n = int(input())
    for i in range(n):
        d = input().split(" ")
        data.append((int(d[0]), d[1]))
        if len(d[0]) > max:
            max = len(d[0])

    for d in range(max):
        data = countingSort16(data,d)

    for item in data:
        print(item[0], item[1])