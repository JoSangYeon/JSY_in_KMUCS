# def calc(data):
#     if data[0] >= data[1]:
#         a = data[0]
#         b = data[1]
#     else:
#         a = data[1]
#         b = data[0]
#
#     if b == 0:
#         return a
#     else:
#         return calc((b, a%b))

def gcd(a, b):
    while b > 0:
        a, b = b, a % b
    return a

def calc(data):
    if (data[0] > data[1]):
        data[0], data[1] = data[1], data[0]

    while (data[1] != 0):
        data[0] = data[0] % data[1]
        data[0], data[1] = data[1], data[0]
    return data[0]


if __name__ == "__main__":
    n = int(input())
    for i in range(n):
        d = list(map(int, input().split(" ")))
        print(calc(d))