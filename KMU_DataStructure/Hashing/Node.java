package Hashing;

public class Node
{
	int data;
	Node link;
	public Node(int d)
	{
		data = d;
		link = null;
	}
	
	public String toString()
	{return Integer.toString(data);}
}
