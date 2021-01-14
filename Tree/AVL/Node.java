package AVL;

public class Node
{
	int data;
	int bf;
	Node left, right;
	
	public Node(int data)
	{
		this.data = data;
		this.bf = 0;
		this.left = null;
		this.right = null;
	}
}
