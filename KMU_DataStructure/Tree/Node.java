package Tree;

public class Node
{
	int prio;
	char data;
	Node left;
	Node right;
	
	public Node(char data)
	{
		this.data = data;
		prio = setPriority(data);
		left = right = null;
	}
	
	private int setPriority(char data)
	{
		switch(data)
		{
		case '*': case '/':
			return 2;
		case '+': case '-':
			return 1;
		default:
			return 4;
		}
	}
}
