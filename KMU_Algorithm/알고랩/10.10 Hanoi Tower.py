"""
64 18446744073709551615
n(원반의 개수),  k(k번째의 이동(하는 소스 기둥과 목적 기둥을 return))
n개의 원반은 크게보면 ①n-1개만큼의 원반을 a기둥에서 b기둥으로 옮기고,
②이후 a기둥에 있던 마지막 원반을 c 기둥으로 옮기고,
③마지막으로 ①에서 옮겼던 b기둥에 있던 나머지 n-1개의 원반을 c 기둥으로 옮기는 것이다.
근데, 여기서 자세히 보면, n-1개만큼의 원반들은 또다시①~③과정을 반복한다.
따라서 위 문제를 해결하기 위해서는,
n-1개의 원반을 옮기는 과정을 생략할 필요가 있다.
n개의 원반을 옮기는 최대 회수는 (2**n)-1임을 이용하여 횟수를 줄여서 빠르게 계산해 나간다.
생각 할 수 있는 경우의 수는 3가지이다.
    1. k가 2**(n-1)보다 작다면(k < 2**(n-1)):
        k가 2**(n-1)보다 작다면, k번째 옮겨지는 경우는 n-1개의 원반을 a->b로 옮기는 과정 중에서 나타난다는 의미이다.
        따라서 이 경우에는 n-1개를 a->b로 옮기는 재귀함수 내부로 들어가야한다. 따라서 코드는
        ```
        if k < 2 ** (n - 1):
            hanoi_tower(n - 1, a, c, b)
        ```
        즉, n-1개의 원반을 a->b기둥으로 옮기는 과정중에 k번째가 실행되는 것
    2. k가 2**(n-1)보다 크다면(k > 2**(n-1)):
        k가 2**(n-1)보다 크다면, k번째 옮겨지는 경우가 n-1개의 원반을 b->c로 옮기는 과정 중에서 나타난다는 의미이다.
        (즉, k번째 원반 옮기기의 행위가 n-1개의 원반을 한번 a->b로 옮기는 과정을 거친 후에 발생한다는 의미)
        따라서 이 경우에는 n-1개의 원반을 a->b로 옮긴 회수, 즉 2**(n-1)을 k에서 빼주어야 한다.
        위에서 언급했던 ③의 과정에서 k번째 옮기는 경우가 발생함으로 n-1개의 원반을 b->c로 옮기는 재귀 함수 내부로 들어가야한다.
        따라서 코드는
        ```
        elif k > 2 ** (n - 1):
            k -= 2 ** (n - 1)
            hanoi_tower(n - 1, b, a, c)
        ```
    3. k가 2**(n-1)와 같다면(k == 2**(n-1)):
        k가 2**(n-1)와 같다면, 이 경우는 ②의 과정에서 k번째에 해당되는 원반 옮기기가 발생한 것이다.
        따라서 이 경우에는 단순히 a->c를 출력해주면 된다.
        코드는
        ```
        else:
            print("{} -> {}".format(a, c))
        ```
"""
def hanoi_tower(n, a, b, c):
    global k
    if n > 0:
        if k < 2 ** (n - 1):
            hanoi_tower(n - 1, a, c, b)
        elif k > 2 ** (n - 1):
            k -= 2 ** (n - 1)
            hanoi_tower(n - 1, b, a, c)
        else:
            print("{} {}".format(a, c))


n = int(input())
for i in range(n):
    num_of_disks, k = map(int, input().split())
    hanoi_tower(num_of_disks, 1, 2, 3)
