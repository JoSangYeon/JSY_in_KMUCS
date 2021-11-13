class Node:
    def __init__(self, data, frequency):
        self.d = data
        self.f = frequency
        self.left = None
        self.right = None
    def is_leaf(self):
        return self.left is None and self.right is None
    def __str__(self):
        return "{}:{}".format(self.d, self.f)
    def __lt__(self, other):
        return self.f < other.f
    def __le__(self, other):
        return self.f <= other.f
    def __eq__(self, other):
        return self.f == other.f
    def __ne__(self, other):
        return self.f != other.f
    def __gt__(self, other):
        return self.f > other.f
    def __ge__(self, other):
        return self.f >= other.f

"""
class min_heap:
    def __init__(self, data, frequency):
        self.arr = [Node(d,f) for d, f in zip(data, frequency)]
        self.build_min_heap()

    def min_heapify(self, size, i):
        l = 2*i + 1
        r = 2*i + 2

        smallest = i

        if l < size and self.arr[l].f < self.arr[smallest].f:
            smallest = l
        if r < size and self.arr[r].f < self.arr[smallest].f:
            smallest = r

        if smallest != i:
            self.arr[i], self.arr[smallest] = self.arr[smallest], self.arr[i]
            if l < size and r < size and self.arr[l].f > self.arr[r].f:
                self.arr[l], self.arr[r] = self.arr[r], self.arr[l]
                self.min_heapify(size, l)
                self.min_heapify(size, r)
            else:
                self.min_heapify(size, smallest)

    def build_min_heap(self):
        n = (len(self.arr)//2)
        for i in range(n, -1, -1):
            self.min_heapify(len(self.arr), i)

    def extract_min(self):
        temp = self.arr.pop(0)
        self.min_heapify(len(self), 0)
        return temp

    def insert(self, node):
        self.arr.append(node)
        self.build_min_heap()

    def __str__(self):
        result = "[ "
        for node in self.arr:
            result += str(node)+"  "
        result = result[:-1] + "]"
        return result

    def __len__(self):
        return len(self.arr)
"""

class Node_Q:
    def __init__(self, data, frequency):
        self.arr = [Node(d,f) for d, f in zip(data, frequency)]
        self.sort()

    def sort(self):
        self.arr = sorted(self.arr)

    def extract_min(self):
        return self.arr.pop(0)

    def insert(self, node):
        self.arr.append(node)
        self.sort()

    def __str__(self):
        result = "[ "
        for node in self.arr:
            result += str(node)+"  "
        result = result[:-1] + "]"
        return result

    def __len__(self):
        return len(self.arr)

def huffman(Q):
    n = len(Q)
    for i in range(1,n):
        x = Q.extract_min()
        y = Q.extract_min()
        z = Node(x.d+y.d, x.f+y.f)
        z.left = x
        z.right = y
        Q.insert(z)
    return Q.extract_min()

def print_huffman(node, code):
    if node.is_leaf():
        print("{} \t=>\t {}".format(node, code))
    else:
        print_huffman(node.left, code+"0")
        print_huffman(node.right, code+"1")

if __name__ == "__main__":
    data = ['a','b','c','d','e','f']
    frequency = [45,13,12,16,9,5]

    Q = Node_Q(data, frequency)
    print(Q)
    BT = huffman(Q)
    print_huffman(BT, "")