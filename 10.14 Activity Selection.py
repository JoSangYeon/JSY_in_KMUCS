def recursive_activity_selector(s,f,k,n):
    if k < n-1:
        m = k+1
        while m < n and s[m] < f[k]:
            m += 1
        if m < n:
            result.append(m)
            recursive_activity_selector(s,f,m,n)
        else:
            return

def greedy_activity_selector(s,f):
    n = len(s)
    r = [0]
    k = 0
    for m in range(1, n):
        if s[m] >= f[k]:
            r.append(m)
            k = m
    return r

def print_activity(s, f, r):
    for idx in r:
        print("{} ~ {}".format(s[idx], f[idx]))

if __name__ == "__main__":
    s = [1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12]
    f = [4, 5, 6, 7, 9, 9, 10, 11, 12, 14, 16]
    # result = [0]
    # recursive_activity_selector(s,f,0,len(s))
    result = greedy_activity_selector(s,f)
    print_activity(s, f, result)