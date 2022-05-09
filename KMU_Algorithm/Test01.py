def f(x):
    x[0] = 100

def ff():
    arr = [1, 2, 3, 4]
    print(arr)
    f(arr)
    print(arr)

if __name__ == "__main__":
    # print(arr)
    ff()
    # print(arr)

"""
결론!
python도 배열같은 자료형은 함수에 들어갈때,
call by value가 아닌! call by "reference"이다!
"""