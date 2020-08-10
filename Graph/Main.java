// Graph 구현 및 DFS, BFS 알고리즘 구현
// GRaph는 인접행렬 방법과(G1) 인접리스트 방법(G2)로 구분
package Graph;
import Graph.G1.*;
import Graph.G2.*;

public class Main
{
	public static void main(String[] args)
	{
		int[][] matrix1 = {
				{0, 1, 1, 0, 0, 0},
				{1, 0, 1, 1, 0, 0},
				{1, 1, 0, 0, 0, 1},
				{0, 1, 0, 0, 1, 1},
				{0, 0, 0, 1, 0, 1},
				{0, 0, 1, 1, 1, 0}
				};
		
		int[][] matrix2 = {
				{0, 1, 1, 0, 0, 0, 0, 0},
				{1, 0, 0, 1, 1, 0, 0, 0},
				{1, 0, 0, 0, 0, 1, 1, 0},
				{0, 1, 0, 0, 0, 0, 0, 1},
				{0, 1, 0, 0, 0, 0, 0, 1},
				{0, 0, 1, 0, 0, 0, 0, 1},
				{0, 0, 1, 0, 0, 0, 0, 1},
				{0, 0, 0, 1, 1, 1, 1, 0}
				};
		
		String data = 
				"0 1 2\n" + 
				"1 0 3 4\n" + 
				"2 0 5 6\n" + 
				"3 1 7\n" + 
				"4 1 7\n" + 
				"5 2 7\n" + 
				"6 2 7\n" + 
				"7 3 4 5 6\n";
		
		G1 g1 = new G1(matrix1);
		G1 g2 = new G1(matrix2);
		
		g1.print();
		g1.BFS(0);
		g1.DFS(0);
		System.out.println();
		g2.print();
		g2.BFS(0);
		g2.DFS(0);
		System.out.println();
		
		G2 g3 = new G2(matrix1);
		G2 g4 = new G2(data);
		
		g3.print();
		g3.BFS(0);
		g3.DFS(0);
		System.out.println();
		g4.print();
		g4.BFS(0);
		g4.DFS(0);
	}
}
