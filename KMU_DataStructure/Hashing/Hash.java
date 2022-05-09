package Hashing;

public class Hash
{
	Node[] Table;
	int size;
	
	public Hash(int size)
	{
		this.size = size;
		Table = new Node[size];
	}
	
	public int length() {return size;}
	
	public void insert(int key)
	{
		int idx = this.hasingFunc(key);
		
		if(Table[idx] == null) {Table[idx] = new Node(key);}
		else
		{
			Node p = Table[idx];
			while(p.link != null) {p = p.link;}
			p.link = new Node(key);
		}
	}
	
	public void delete(int key)
	{
		int idx = this.hasingFunc(key);
		
		if(Table[idx] == null) {return;}
		else
		{
			Node p = Table[idx];
			Node q = p;
			
			while(p.link != null && p.data != key)
				{p = q; p = p.link;}
			
			if(p.data == key)
			{
				if(p == Table[idx]) {Table[idx] = Table[idx].link;}
				else
				{
					q.link = p.link;
					p = null;
				}
			}
			else {return;}
		}
	}
	
	public boolean find(int key)
	{
		int idx = this.hasingFunc(key);
		boolean result = false;
		
		if(Table[idx] == null) {result = false;}
		else
		{
			Node p = Table[idx];
			
			while(p.link != null && p.data != key)
				{p = p.link;}
			
			if(p.data == key) {result = true;}
			else {result = false;}
		}
		
		return result;
	}
	
	public void print()
	{
		for(int i=0; i<size; i++)
		{
			Node p = Table[i];
			System.out.print("idx "+i+": [ ");
			while(p != null) {System.out.print(p+" "); p = p.link;}
			System.out.println("]");
		}
	}
	
	private int hasingFunc(int key) {return key%size;}
	
}
