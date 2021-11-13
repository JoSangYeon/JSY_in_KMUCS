"""
Divide-and-conquer-and-combine 정렬 알고리즘의 한 예
입력 list를 반으로 쪼개서 정렬한 후 그 걸 다시 한 원소씩 비교하면서 정렬
"""
def merge_sort(data):
    if len(data) < 2:
        return data
    else:
        mid = len(data)//2
        l = data[:mid]
        r = data[mid:]

        left = merge_sort(l)
        right = merge_sort(r)
        s_data = merge(left,right)
        return s_data

def merge(l,r):
    s_data = []
    i = j = 0

    while i<len(l) and j < len(r):
        if l[i] <= r[j]:
            s_data.append(l[i])
            i += 1
        else:
            s_data.append(r[j])
            j += 1

    while i < len(l):
        s_data.append(l[i])
        i+=1
    while j < len(r):
        s_data.append((r[j]))
        j+=1
    return s_data

if __name__ == "__main__":
    a = [4,2,6,3,1,5]
    a = merge_sort(a)
    print(a)