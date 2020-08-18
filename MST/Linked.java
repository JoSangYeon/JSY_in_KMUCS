package MST;

public class Linked
{
	Node head;
	int len;
	
	public Linked()
	{
		head = null;
		len = 0;
	}
	
	public boolean isEmpty() {return head==null && len==0;}
	public int length() {return len;}
	
	public void insert(int v1, int v2, int c)
	{
		if(this.isEmpty()) {head = new Node(v1, v2, c);}
		else
		{
			Node temp = head;
			
			while(temp.link != null) {temp = temp.link;}
			
			temp.link = new Node(v1, v2, c);
		}
		len++;
	}
	
	public String toString()
	{
		String str = "";
		Node p = head;
		while(p != null)
		{
			str += p + " ";
			p = p.link;
		}
		return str;
	}
}
