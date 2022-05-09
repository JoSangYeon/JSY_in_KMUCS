"""
포기 씨바ㅓㄹ
"""

def matrix_mul(A,B):
    n = len(A)
    c = [0 for i in range(n)]
    if n == 1:
        c[0] = A[0] * B[0]
    else:
        a = sum(A,[])
        b = sum(B,[])
        a_t = []
        b_t = []
        for x in range(0, n // 2 + 1, 2):
            for y in range(0, n // 2 + 1, 2):
                t1 = []
                t2 = []
                for i in range(n // 2):
                    for j in range(n // 2):
                        t1.append(a[(i + x) * 4 + (j + y)])
                        t2.append(b[(i + x) * 4 + (j + y)])
                a_t.append(t1); b_t.append(t2)
        c[0] = matrix_mul(a_t[0],b_t[0])+matrix_mul(a_t[1],b_t[2])
        c[1] = matrix_mul(a_t[0],b_t[1])+matrix_mul(a_t[1],b_t[3])
        c[2] = matrix_mul(a_t[2],b_t[0])+matrix_mul(a_t[3],b_t[2])
        c[3] = matrix_mul(a_t[0],b_t[1])+matrix_mul(a_t[3],b_t[3])
    return c


if __name__ == "__main__":
    a = [[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]]
    b = [[21,22,23,24],[25,26,27,28],[29,30,31,32],[33,34,35,36]]
    r = matrix_mul(a,b)
    print(r)