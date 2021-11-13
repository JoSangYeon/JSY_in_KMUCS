"""
https://www.geeksforgeeks.org/hoares-vs-lomuto-partition-scheme-quicksort/
"""

def quicksort_H(data, p, r):
    if p < r:
        q = partition_H(data, p, r)
        quicksort_H(data, p, q)   # left
        quicksort_H(data,q+1,r)   # right
def partition_H(data, p, r):
    pivot = data[p]
    i = p - 1
    j = r + 1

    while True:
        i += 1
        while data[i] < pivot:
            i += 1
            cnt[2] += 1
        if data[i] >= pivot:
            cnt[2] += 1

        j -= 1
        while data[j] > pivot:
            j -= 1
            cnt[2] += 1
        if data[j] <= pivot:
            cnt[2] += 1

        if i >= j:
            return j
        data[i], data[j] = data[j], data[i]
        cnt[0] += 1

def quicksort_L(data, p, r):
    if p < r:
        q = partition_L(data, p, r)
        quicksort_L(data, p, q-1) # left
        quicksort_L(data,q+1,r)   # right
def partition_L(data, p, r):
    pivot = data[p]
    j = p
    for i in range(p+1, r+1):
        if data[i] < pivot:
            cnt[3] += 1
            j += 1
            data[i], data[j] = data[j], data[i]
            cnt[1] += 1
        else:
            cnt[3] += 1

    pivot_pos = j
    data[pivot_pos], data[p] = data[p], data[pivot_pos]
    cnt[1] += 1
    return pivot_pos


n = int(input())
data = []
for i in range(n):
    d = list(map(int, input().split(" ")))[1:]
    data.append(d)

for i in range(n):
    cnt = [0, 0, 0, 0]  # [cnt_H_swap, cnt_L_swap, cnt_H_comparison, cnt_L_comparison]
    arr1 = list(data[i])
    arr2 = list(data[i])
    quicksort_H(arr1, 0, len(arr1) - 1)
    quicksort_L(arr2, 0, len(arr2) - 1)
    print(cnt[0], cnt[1], cnt[2], cnt[3])
    # print("Hoare :",arr1)
    # print("Lomuto :",arr2)


"""
9 4 7 3 9 2 5 8 1 6
40 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9
30 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30
30 30 29 28 27 26 25 24 23 22 21 20 19 18 17 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1
30 19 14 30 25 13 29 5 20 2 12 18 24 9 21 6 26 16 4 22 1 28 17 10 7 23 15 3 27 11 8
-------------------
7 14 45 17
100 39 278 780
0 29 493 435
15 254 508 435
43 71 212 100
"""