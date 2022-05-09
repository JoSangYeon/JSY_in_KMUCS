package AVL;

public class AVL
{
	Node root;
	Node unbalNode; //������ ���� Node�� �����ϴ� ����
	Node parents;  //������ ���� Node�� �θ� Node�� �����ϴ� ����
	
	public AVL()
	{
		root = null;
		unbalNode = null;
		parents = null;
	}
	
	public boolean isEmpty() {return root == null;}
	
	public void print() 
	{
		if(this.isEmpty()) {System.out.print("Tree is empty");}
		else {inorder(root);}
		
		System.out.println();
	}
	
	public void insert(int data)
	{
		unbalNode = null;
		parents = null;
		
		root = insertBST(root, data);
		updateBF(data); //updateBF �޼ҵ带 �����ϸ鼭 unbalNode�� parents��带 �ʱ�ȭ ��
		
		if(unbalNode == null) {return;}
		
		if(unbalNode.bf > 1)
		{
			if(calcBF(unbalNode.left) >= 0)
			{
				rotateLL(unbalNode, parents);
				return;
			}
			else
			{
				rotateLR(unbalNode, parents);
				return;
			}
		}
		else if(unbalNode.bf < -1)
		{
			if(calcBF(unbalNode.right) <= 0)
			{
				rotateRR(unbalNode, parents);
				return;
			}
			else
			{
				rotateRL(unbalNode, parents);
				return;
			}
		}
		else {return;}
	}
	
	public void delete(int data)
	{
		unbalNode = null;
		parents = null;
		
		root = deleteBST(root, data);
		updateBF(data); //updateBF �޼ҵ带 �����ϸ鼭 unbalNode�� parents��带 �ʱ�ȭ ��
		
		if(unbalNode == null) {return;}
		
		if(unbalNode.bf > 1)
		{
			if(calcBF(unbalNode.left) >= 0)
			{
				rotateLL(unbalNode, parents);
				return;
			}
			else
			{
				rotateLR(unbalNode, parents);
				return;
			}
		}
		else if(unbalNode.bf < -1)
		{
			if(calcBF(unbalNode.right) <= 0)
			{
				rotateRR(unbalNode, parents);
				return;
			}
			else
			{
				rotateRL(unbalNode, parents);
				return;
			}
		}
		else {return;}
	}
	
	private void rotateLL(Node unbal, Node p)
	{
		boolean check = true;;
		if(p != null)
		{check = p.left == unbal ? true : false;}
		
		Node sub = unbal.left;
		unbal.left = sub.right;
		sub.right = unbal;
		
		if(p == null) {root = sub;}
		else
		{
			if(check) {p.left = sub;}
			else {p.right = sub;}
		}
	}
	
	private void rotateRR(Node unbal, Node p)
	{
		boolean check = true;;
		if(p != null)
		{check = p.left == unbal ? true : false;}
		
		Node sub = unbal.right;
		unbal.right = sub.left;
		sub.left = unbal;
		
		if(p == null) {root = sub;}
		else
		{
			if(check) {p.left = sub;}
			else {p.right = sub;}
		}
	}
	
	private void rotateLR(Node unbal, Node p)
	{
		Node sub = unbal.left;
		Node q = unbal.left.right; // unbal Node�� root�϶��� ���� temp Node
		
		rotateRR(sub, unbal);
		rotateLL(unbal, p);
		
		if(p == null)
		{root = q;}		
	}
	
	private void rotateRL(Node unbal, Node p)
	{
		Node sub = unbal.right;
		Node q = unbal.right.left; // unbal Node�� root�϶��� ���� temp Node
		
		rotateLL(sub, unbal);
		rotateRR(unbal, p);
		
		if(p == null)
		{root = q;}		
	}
	
    private Node insertBST(Node temp, int data)
    {
        // ������ ��带 Ž���Ѵ�(���) \\
        if(temp == null) {temp = new Node(data);}
        else if(data < temp.data) {temp.left = insertBST(temp.left, data);}
        else if(data > temp.data) {temp.right = insertBST(temp.right, data);}

        return temp;
    }
	
	private Node deleteBST(Node temp, int data)
    {
        if(temp != null)
        {
            Node p;

            // ������ ������ �̵��ϴ� ����(���) \\
            if(temp.data > data) {temp.left = deleteBST(temp.left, data);}
            else if(temp.data < data) {temp.right = deleteBST(temp.right, data);}

            // case 1: ������ ��尡 ��������� ��� \\
            else if(temp.left == null && temp.right == null)
                {temp = null;}

            // case 2-1: ������ ����� �ڽ� ��尡 1��(left)�� ��� \\
            else if(temp.right == null)
            {
                p = temp;
                temp = temp.left;
                p = null;
            }

            // case 2-2: ������ ����� �ڽ� ��尡 1��(right)�� ��� \\
            else if(temp.left == null)
            {
                p = temp;
                temp = temp.right;
                p = null;
            }
            // case 3: ������ ����� �ڽĳ�尡 2���� ��� \\
            else
            {
                //�ּ����� ������ ���� �����ϰ��� �ϴ� ����� �¿� ����, ��尹����
                //���ؼ� ������ ���������� �ǽ���
                boolean flag; // left => true, right => false

                //case 3-1: ������ ����� ���� subTree�� �� ������\\
                if(getHeight(temp.left) > getHeight(temp.right))
                {
                    p = findMaxNode(temp.left);
                    flag = true;
                }

                //case 3-2: ������ ����� �������� subTree�� �� ������\\
                else if(getHeight(temp.left) < getHeight(temp.right))
                {
                    p = findMinNode(temp.right);
                    flag = false;
                }

                //case 3-3: ������ subTree�� ���̰� ������\\
                else
                {
                    //case 3-3-1: ���� subTree�� node ������ �� ������\\
                    if(getNumofNode(temp.left) >= getNumofNode(temp.right))
                    {
                        p = findMaxNode(temp.left);
                        flag = true;
                    }

                    //case 3-3-2: ������ subTree�� node ������ �� ������\\
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
	
	private void updateBF(int data)
	{
		if(this.isEmpty()) {return;}
		
		Node p = root;
		Node q = null;
		
		// p�� leaf Node�϶� ���� �ݺ� \\
		while(!(p.left == null && p.right == null))
		{
			p.bf = calcBF(p); //p.setBF(calcBF(p));
			
			if(p.bf > 1 || p.bf < -1)
			{
				parents = q;
				unbalNode = p;
			}
			q = p;
			
			if(p.data > data) {p = p.left;}
			else {p = p.right;}
			
			if(p == null) {break;}
		}
	}
	
	private int calcBF(Node temp)
	{
		if(temp == null) {return 0;}
		else
		{return getHeight(temp.left) - getHeight(temp.right);}
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
    	int[] testcase = { 40, 11, 77, 33, 20, 90, 99, 70, 88, 80, 66, 10, 22, 30, 44, 55, 50, 60, 25, 49 };
    	
    	AVL avl = new AVL();
    	
    	//������ ����\\
    	System.out.println("������ ����");
    	for (int i = 0; i < 20; i++)
    	{ 
    		System.out.print(testcase[i]+"����: ");
    		avl.insert(testcase[i]); 
    		avl.print();
    	}
    	
    	//������ ����\\
    	System.out.println("������ ����");
    	for (int i = 0; i < 20; i++)
    	{ 
    		System.out.print(testcase[i]+"����: ");
    		avl.delete(testcase[i]); 
    		avl.print();
    	}
    }
}
