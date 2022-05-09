def quicksort(data, p, r):
    if p < r:
        q = partition(data, p, r)
        quicksort(data, p, q-1) # left
        quicksort(data,q+1,r)   # right

def partition(data, p, r):
    x = data[r] # 배열의 마지막 원소
    i = p-1
    for j in range(p, r):
        if data[j] <= x:
            i += 1
            data[i], data[j] = data[j], data[i]
    data[i+1], data[r] = data[r], data[i+1]
    return i+1

if __name__ == "__main__":
    arr = [2,8,7,1,3,5,6,4]
    quicksort(arr, 0, len(arr)-1)
    print(arr)