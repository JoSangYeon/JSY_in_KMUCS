def hanoi_tower(n, a, b, c):
    if n > 0:
        hanoi_tower(n - 1, a, c, b)
        print("{} -> {}".format(a, c))
        hanoi_tower(n - 1, b, a, c)


n = int(input())
for i in range(n):
    num_of_disks, k = map(int, input().split())
    m = 0
    hanoi_tower(num_of_disks, 1, 2, 3)
