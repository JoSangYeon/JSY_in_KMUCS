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
		
		G1 g1 = new G1(matrix1);
		G1 g2 = new G1(matrix2);
		
		g1.print();
		g1.BFS(0);
		g1.DFS(0);
		
		g2.print();
		g2.BFS(0);
		g2.DFS(0);
	}
}
