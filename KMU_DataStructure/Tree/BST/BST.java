package BST;


public class BST
{
    private Node root; //최상위 Node

    public BST()
    {
        this.root = null;
    }
    public boolean isEmpty() {return root == null;}

    public void insert(int data) {root = insertBST(root, data);}
    private Node insertBST(Node temp, int data)
    {
        // 삽입할 노드를 탐색한다(재귀) \\
        if(temp == null) {temp = new Node(data);}
        else if(data < temp.data) {temp.left = insertBST(temp.left, data);}
        else if(data > temp.data) {temp.right = insertBST(temp.right, data);}

        return temp;
    }

    public void delete(int data) {root = deleteBST(root, data);}
    private Node deleteBST(Node temp, int data)
    {
        if(temp != null)
        {
            Node p;

            // 삭제할 노드까지 이동하는 과정(재귀) \\
            if(temp.data > data) {temp.left = deleteBST(temp.left, data);}
            else if(temp.data < data) {temp.right = deleteBST(temp.right, data);}

            // case 1: 삭제할 노드가 리프노드일 경우 \\
            else if(temp.left == null && temp.right == null)
                {temp = null;}

            // case 2-1: 삭제할 노드의 자식 노드가 1개(left)일 경우 \\
            else if(temp.right == null)
            {
                p = temp;
                temp = temp.left;
                p = null;
            }

            // case 2-2: 삭제할 노드의 자식 노드가 1개(right)일 경우 \\
            else if(temp.left == null)
            {
                p = temp;
                temp = temp.right;
                p = null;
            }
            // case 3: 삭제할 노드의 자식노드가 2개일 경우 \\
            else
            {
                //최소한의 균형을 위해 삭제하고자 하는 노드의 좌우 높이, 노드갯수를
                //비교해서 삭제를 유동적으로 실시함
                boolean flag; // left => true, right => false

                //case 3-1: 삭제할 노드의 왼쪽 subTree가 더 높을때\\
                if(getHeight(temp.left) > getHeight(temp.right))
                {
                    p = findMaxNode(temp.left);
                    flag = true;
                }

                //case 3-2: 삭제할 노드의 오른른쪽 subTree가 더 높을때\\
                else if(getHeight(temp.left) < getHeight(temp.right))
                {
                    p = findMinNode(temp.right);
                    flag = false;
                }

                //case 3-3: 양쪽의 subTree의 높이가 같을때\\
                else
                {
                    //case 3-3-1: 왼쪽 subTree의 node 개수가 더 많을때\\
                    if(getNumofNode(temp.left) >= getNumofNode(temp.right))
                    {
                        p = findMaxNode(temp.left);
                        flag = true;
                    }

                    //case 3-3-2: 오른쪽 subTree의 node 개수가 더 많을때\\
                    else
                    {
                        p = findMinNode(temp.right);
                        flag = false;
                    }
                }

                temp.data = p.data;

                if(flag) {temp.left = deleteBST(temp.left, p.data);}
                else {temp.right = deleteBST(temp.right, p.data);}
            }
        }
        return temp;
    }

    private int getHeight(Node temp)
    {
        if(temp == null) {return -1;}

        int leftSubTreeHeight = getHeight(temp.left) + 1;
        int rightSubTreeHegiht = getHeight(temp.right) + 1;

        return leftSubTreeHeight > rightSubTreeHegiht ? leftSubTreeHeight : rightSubTreeHegiht;
    }

    private int getNumofNode(Node temp)
    {
        if(temp == null) {return -1;}

        int leftSubTreeHeight = getHeight(temp.left) + 1;
        int rightSubTreeHegiht = getHeight(temp.right) + 1;

        return leftSubTreeHeight + rightSubTreeHegiht + 1;
    }

    private Node findMaxNode(Node temp)
    {
        if(temp.right == null) {return temp;}
        else {return findMaxNode(temp.right);}
    }

    private Node findMinNode(Node temp)
    {
        if(temp.left == null) {return temp;}
        else {return findMinNode(temp.left);}
    }

    public void print()
    {
        if(this.isEmpty()) {System.out.println("Tree is empty");}
        else {inorder(this.root); System.out.println();}
    }

    private void inorder(Node temp)
    {
        if (temp != null)
        {
            inorder(temp.left);
            System.out.print(temp.data+" ");
            inorder(temp.right);
        }
    }

    public static void main(String[] args)
    {

        int[] data = { 25, 500, 33, 49, 17, 403, 29, 105, 39, 66,
                305, 44, 19, 441, 390, 12, 81, 50, 100, 999 };

        BST bst = new BST();

        System.out.println("데이터 삽입");
        for (int i = 0; i < data.length; i++)
        {
            bst.insert(data[i]);
            bst.print();
        }

        System.out.println("데이터 삭제");
        for (int i = 0; i < data.length; i++)
        {
            bst.delete(data[i]);
            bst.print();
        }
    }
}
