package MST;

public class Node
{
	int vertex1;
	int vertex2;
	int cost;
	Node link;
	
	public Node(int v1, int v2, int c)
	{
		vertex1 = v1;
		vertex2 = v2;
		cost = c;
		link = null;		
	}
	
	public String toString()
	{
		return (char)(vertex1+65) + ":" + cost + ":" + (char)(vertex2+65);
	}
}