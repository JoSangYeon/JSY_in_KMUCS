def insertion_sort(data):
    for j in range(1, len(data)):
        key = data[j]
        i = j-1
        while i>=0 and data[i] > key:
            data[i+1] = data[i]
            i = i-1
        data[i+1] = key
    return data

if __name__ == "__main__":
    arr = [5,2,4,6,1,3]
    print(insertion_sort(arr))