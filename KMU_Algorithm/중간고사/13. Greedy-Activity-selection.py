def greedy_activity_selector(s, f):
    r = [0]

    sf = f[0]
    for i in range(1, len(s)):
        if sf < s[i]:
            sf = f[i]
            r.append(i)
    return r


def print_activity(s,f,r):
    for i in range(len(r)):
        print("S : {}\t~~~\tF : {}".format(s[r[i]], f[r[i]]))

if __name__ == "__main__":
    s = [1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12]
    f = [4, 5, 6, 7, 9, 9, 10, 11, 12, 14, 16]
    # result = [0]
    # recursive_activity_selector(s,f,0,len(s))
    result = greedy_activity_selector(s,f)
    print_activity(s, f, result)