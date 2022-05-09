// 인접 리스트로 구현하는 Graph 클래스 DFS와 BFS 알고리즘 구현

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
	
	public G2(int[][] matrix) //인접 행렬을 인접리스트로 바꾸는 생성자
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
	
	public G2(String str) //String 형태의 인접리스트의 정보가 인자로 왔을 경우의 생성자
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
			for(int k=1; k<Vdata.length; k++) // k=1인 이유: 0번째는 해당하는 정점의 데이터를 가짐으로 버린다.
				{temp.insert(Integer.parseInt(Vdata[k]));}
			
			nodes[Integer.parseInt(Vdata[0])] = temp; // 정렬된 데이터라면 nodes[i] = temp;
		}
	}
	
	public void print()
	{
		System.out.println("print Graph...");
		for(int i=0; i<size; i++)
			{System.out.println("V"+i+": "+nodes[i]);} // Linked 클래스의 toString 오버라이딩
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
		System.out.println("BFS 탐색 결과: "+result);
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
		System.out.println("DFS 탐색 결과: "+result);
		this.setVisited();
	}
	
	private void setVisited() {Arrays.fill(visited, false);}
}
