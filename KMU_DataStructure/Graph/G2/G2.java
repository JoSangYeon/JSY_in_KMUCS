// ���� ����Ʈ�� �����ϴ� Graph Ŭ���� DFS�� BFS �˰��� ����

package Graph.G2;

import Graph.*;
import Graph.G2.Node;
import Graph.G2.Linked;
import java.util.Arrays;


public class G2
{
	private Linked[] nodes;
	private int size;
	private boolean[] visited;
	
	public G2(int[][] matrix) //���� ����� ��������Ʈ�� �ٲٴ� ������
	{
		size = matrix.length;
		nodes = new Linked[size];
		visited = new boolean[size];
		Arrays.fill(visited, false);
		
		for(int i=0; i<size; i++)
		{
			Linked temp = new Linked();
			for(int k=0; k<size; k++)
			{
				if(matrix[i][k] == 1) {temp.insert(k);}
			}
			nodes[i] = temp;
		}
	}
	
	public G2(String str) //String ������ ��������Ʈ�� ������ ���ڷ� ���� ����� ������
	{
		String[] data = str.split("\n");
		size = data.length;
		
		nodes = new Linked[size];
		visited = new boolean[size];
		Arrays.fill(visited, false);
		
		for(int i=0; i<size; i++)
		{
			String[] Vdata = data[i].split(" ");
			Linked temp = new Linked();
			for(int k=1; k<Vdata.length; k++) // k=1�� ����: 0��°�� �ش��ϴ� ������ �����͸� �������� ������.
				{temp.insert(Integer.parseInt(Vdata[k]));}
			
			nodes[Integer.parseInt(Vdata[0])] = temp; // ���ĵ� �����Ͷ�� nodes[i] = temp;
		}
	}
	
	public void print()
	{
		System.out.println("print Graph...");
		for(int i=0; i<size; i++)
			{System.out.println("V"+i+": "+nodes[i]);} // Linked Ŭ������ toString �������̵�
	}
	
	public void BFS(int v)
	{
		Node p;
		String result;
		Queue q = new Queue(size);
		
		q.Enqueue(v);
		visited[v] = true;
		result = v+"-";
		
		while(!q.isEmpty())
		{
			int ver = q.Dequeue();
			for(p=nodes[ver].head; p!=null; p=p.next)
			{
				if(!visited[p.vertex])
				{
					q.Enqueue(p.vertex);
					visited[p.vertex] = true;
					result += p.vertex+"-";
				}
			}
		}
		
		result = result.substring(0, result.length()-1);
		System.out.println("BFS Ž�� ���: "+result);
		this.setVisited();
	}
	
	public void DFS(int v)
	{
		Node p;
		String result;
		Stack s = new Stack(size);
		
		s.push(v);
		visited[v] = true;
		result = v+"-";
		
		while(!s.isEmpty())
		{
			int ver = s.getTop();
			boolean flag = false;
			
			for(p = nodes[ver].head; p != null; p=p.next)
			{
				if(!visited[p.vertex])
				{
					s.push(p.vertex);
					visited[p.vertex] = true;
					result += p.vertex+"-";
					
					flag = true;
					break;
				}
			}
			if(!flag) {s.pop();}
		}
		
		result = result.substring(0, result.length()-1);
		System.out.println("DFS Ž�� ���: "+result);
		this.setVisited();
	}
	
	private void setVisited() {Arrays.fill(visited, false);}
}
