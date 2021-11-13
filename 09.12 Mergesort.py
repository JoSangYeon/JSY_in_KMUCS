def m_sort(data):
    if len(data) < 2:
        return data

    mid = len(data) // 2
    left = data[:mid]
    right = data[mid:]

    left1 = m_sort(left)
    right1 = m_sort(right)
    return merge(left1, right1)


def merge(left, right):
    i = 0
    j = 0
    s_data = []

    while (i < len(left)) and (j < len(right)):
        if left[i][0] >= right[j][0]:
            s_data.append(left[i])
            i += 1
        else:
            s_data.append(right[j])
            j += 1

    while (i < len(left)):
        s_data.append(left[i])
        i += 1
    while (j < len(right)):
        s_data.append(right[j])
        j += 1
    return s_data


n = int(input())
data = []
for i in range(n):
    d = input().split(" ")
    data.append((int(d[0]), d[1]))

# d = sorted(data, key=lambda x:x[0], reverse=True)
d = m_sort(data)

for item in d:
    print(item[0], item[1])