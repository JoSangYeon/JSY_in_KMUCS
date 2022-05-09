package DisjointSet;

public class UnionFind
{
	private int[] list;
	private int myLen;
	
	public UnionFind(int[] list)
	{
		this.list = list;
		myLen = list.length;
	}
	
	public int getLength() {return myLen;}
	
	public void print() {System.out.println(this.toString());}
	
	public String toString()
	{
		String str = "[ ";
		
		for(int a: list) {str += a+" ";}
		
		return str+"]";
	}
	
	public int find(int vertex)
	{
		if(list[vertex] == vertex) {return vertex;}
		else {return list[vertex];}
	}
	
	public void union(int v1, int v2)
	{
		int x = this.find(v1);
		int y = this.find(v2);
		
		// 적은 번호로 부모 노드를 설정한다.
		if(x<y) {this.list[y] = x;}
		else {this.list[x] = y;}
	}
	
	public boolean isSameParent(int v1, int v2)
	{
		boolean result = true;
		
		if(this.list[v1] == this.list[v2]) {return result;}
		else {return !result;}
	}
}
