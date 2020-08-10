package Graph.G2;

import Graph.G2.Node;

public class Linked
{
	Node head;
	
	public Linked() {head = null;}
	
	public boolean isEmpty() {return head == null;}
	
	public void insert(int v)
	{
		if(this.isEmpty()) {head = new Node(v);}
		else
		{
			Node temp = head;
			
			while(temp.next != null) {temp = temp.next;}
			
			temp.next = new Node(v);
		}
	}
	
	public String toString()
	{
		if(this.isEmpty()) {return "[ ]";}
		else
		{
			String str = "[ ";
			
			Node temp = head;
			while(temp != null)
			{
				str += temp.vertex+" ";
				temp = temp.next;
			}
			str += "]";
			
			return str;
		}
	}
}
