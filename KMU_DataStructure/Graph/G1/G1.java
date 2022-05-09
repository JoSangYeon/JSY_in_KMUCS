// 인접 행렬로 구현하는 Graph 클래스 DFS와 BFS 알고리즘 구현

package Graph.G1;

import java.util.Arrays;
import Graph.G1.Queue;
import Graph.G1.Stack;

public class G1
{
	private int size;
	private int[][] matrix;
	private boolean[] visited;
	
	public G1(int[][] m)
	{
		this.matrix = m;
		size = m.length;
		
		visited = new boolean[size];
		Arrays.fill(visited, false);
	}
	
	public void print()
	{
		System.out.println("print Graph...");
		System.out.print("\t");
		for(int i=0; i<size; i++)
			{System.out.print("V"+i+"\t");}
		System.out.println();
		
		for(int i=0; i<size; i++)
		{
			System.out.print("V"+i+":\t");
			for(int k=0; k<size; k++)
				{System.out.print(matrix[i][k]+"\t");}
			System.out.println();
		}
	}
	
	public void BFS(int v)
	{
		Queue q = new Queue(size);
		String result;
		
		visited[v] = true;
		q.Enqueue(v);
		result = v+"-";
		
		while(!q.isEmpty())
		{
			int ver = q.Dequeue();
			for(int i=0; i<size; i++)
			{
				if(matrix[ver][i]==1 && !visited[i])
				{
					q.Enqueue(i);
					visited[i] = true;
					result += i+"-";
				}
			}
		}
		result = result.substring(0, result.length()-1);
		System.out.println("BFS 탐색 결과: "+result);
		this.setVisited();
	}
	
	public void DFS(int v)
	{
		Stack s = new Stack(size);
		String result;
		
		visited[v] = true;
		result = v+"-";
		s.push(v);
		
		while(!s.isEmpty())
		{
			int ver = s.getTop();
			boolean flag = false;
			
			for(int i=0; i<size; i++)
			{
				if(matrix[ver][i]==1 && !visited[i])
				{
					s.push(i);
					visited[i] = true;
					flag = true;
					
					result += i+"-";
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
