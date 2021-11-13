def insertion_sort(data):
    for i in range(len(data)):
        key = data[i]
        idx = i
        for j in range(i,-1,-1):
            if key < data[j]:
                data[j], data[idx] = data[idx], data[j]
                idx = j

if __name__ == "__main__":
    a = [4,2,6,3,1]
    insertion_sort(a)
    print(a)