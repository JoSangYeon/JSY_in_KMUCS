def partition(data, l, h):
    pivot = data[h]
    j = l-1
    for i in range(l,h):
        if data[i] <= pivot:
            j += 1
            data[i], data[j] = data[j], data[i]
    data[j+1], data[h] = data[h], data[j+1]
    return j+1

def quick_sort(data,l,h):
    if l < h:
        q = partition(data,l,h)
        quick_sort(data,l,q-1)
        quick_sort(data,q+1,h)

if __name__ == "__main__":
    a = [2,8,7,1,3,5,6,4]
    quick_sort(a,0, len(a)-1)
    print(a)