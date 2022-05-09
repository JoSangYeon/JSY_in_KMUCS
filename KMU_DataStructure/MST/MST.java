package MST;

import java.util.Arrays;

public class MST
{
	private int[][] matrix;
	private Linked[] heads;
	private Node[] datas;
	
	public MST(int[][] m)
	{
		matrix = m;
		heads = new Linked[m.length];
		
		int count = 0;
		for(int i=0; i<m.length; i++)
		{
			Linked temp = new Linked();
			for(int k=0; k<m.length; k++)
			{
				if(0 < m[i][k] && m[i][k] < 100)
					{temp.insert(i, k, m[i][k]); count++;}
			}
			heads[i] = temp;
		}
		
		datas = new Node[count/2];
		this.setDatas(count);
	}
	
	public String PrimMethod(int vertex)
	{
		int n = matrix.length;
		int edge = 0;
		int v = vertex;
		int u = 0;
		int MSTPath[] = new int[n];
		int MSTCost[] = new int[n-1];
		boolean found[] = new boolean[n];
		
		Arrays.fill(found, false);
		found[v] = true;
		MSTPath[0] = v;
		
		while(edge != n-1)
		{
			int cost = 100;
			for(int i=0; i<n; i++)
			{
				if(cost > matrix[v][i] && !found[i])
					{cost = matrix[v][i]; u=i;}
			}//���� v���� ���� ���� ����ġ�� ���� ���� ã�� ����ġ�� cost�� �����ϰ� �׿� �ش��ϴ� ������ u�� ������(u=i)
			
			if(cost == 100) {break;} //Not found MST
			else
			{
				MSTPath[edge+1] = u;
				MSTCost[edge] = cost; // cost == matrix[v][u]
				found[u] = true;
				v = u; u = 0;
			}
			edge++;
		}
		
		if(edge != n-1) {System.out.println("No sapnning Tree..."); return "";}
		else
		{
			String path = "MST Path: ";
			String cost = "MST Cost:  ";
		
			for(int i: MSTPath)
				{//path += ((char)(65+i)+"--->");
				path += ((char)(65+i)+"-->");	}
			path = path.substring(0, path.length()-3);
			
			int sum=0;
			for(int i: MSTCost)
				{cost += (i+"  "); sum+=i;}
			cost += "\nTotal Weight: "+sum;
			
			//System.out.println(path.substring(0, path.length()-4));
			//System.out.println(cost);
		
			return path+"\n"+cost+"\n";
		}
	}
	
	public String KruskalMethod()
	{
		String result="";
		int totalCost = 0;
		int numOfVertex = heads.length;
		int i = 0;
		int n = 0; 
		
		int[] cycleTable = new int[numOfVertex]; // ������ ������ �̾������� Ȯ�� �ϴ� ����Ŭ ���̺� ����
		// �迭�� �ε����� ������ ���ϰ�, �ش� �ε����� ���� ����Ǿ� �ִ� ������ root�� �Ǵ� ������ ���Ѵ�.
		// ����, cycleTable[2] = 3 �̶��, ���� 2�� ����3�� ����Ǿ� �ִ�. ��� ���̴�.
		//{0,1,2,3,4,5,6}���� �ʱ�ȭ ������ MST���� ������ ������ ���� ����Ǿ� ���� ������ ����
		for(int k=0; k<cycleTable.length; k++) {cycleTable[k] = k;}
		
		while(n != numOfVertex-1) // MST�� ������ ������ ����-1
		{
			int v1 = datas[i].vertex1; //�ε��� ���ٿ� ���� v1 ����� datas�� ����� ����1�� �ʱ�ȭ
			int v2 = datas[i].vertex2; //�ε��� ���ٿ� ���� v2 ����� datas�� ����� ����2�� �ʱ�ȭ
			
			if(cycleTable[v1] != cycleTable[v2])
				// cycleTable[v1] != cycleTable[v2] ==> v1�� v2�� ����� ������ ���� �ٸ��ٸ� if�� ����(v1,v2�� ����)
				{
					
					int v1root = cycleTable[v1];
					int v2root = cycleTable[v2];
					for(int k=0; k<cycleTable.length; k++) //cycleTable �� ��ҿ� �����Ͽ�
						// cycleTable[k]==cycleTable[v2] => ���� k�� v2�� ����Ǿ� �ִٸ�, cycleTable[k] = cycleTable[v1]�� ������
					{
						if(cycleTable[k] == v2root) 
							{cycleTable[k] = v1root;}
					}
					
					// result�� totalCost�� ���������� ���� ������ �߰��Ѵ�.
					result += "Edge"+(n+1)+": "+datas[i]+"\n";
					totalCost += datas[i].cost;
					n++; //����� ���� ���� ����
				}
			i++; //���� �ε��� ����
		}
		// ��������� ����
		return result+"Total Cost: "+ totalCost;
	}
	//n = 0 -> {0,1,2,3,4,5,6}
	//n = 1 -> {0,1,2,3,4,0,6}
	//n = 2 -> {0,1,2,2,4,0,6}
	//n = 3 -> {0,1,2,2,4,0,1}
	//n = 4 -> {0,1,1,1,4,0,1}
	//n = 5 -> {0,0,0,0,0,0,6}
	
	public void print(int mode)
	{
		if(mode == 0) {printG();}
		else if(mode == 1) {printE();}
		else {System.out.println("Wrong input...");}
	}
	
	private void setDatas(int cnt)
	{
		boolean[][] found = new boolean[matrix.length][matrix.length];
		for(int j=0; j<matrix.length; j++) {Arrays.fill(found[j], false);}
		
		int i=0; int k=0; 
		while(i != cnt/2)
		{
			Node temp = heads[k].head; 
			while(temp != null)
			{
				
				if(!found[temp.vertex1][temp.vertex2] && !found[temp.vertex2][temp.vertex1])
				{
					found[temp.vertex1][temp.vertex2] = true;
					found[temp.vertex1][temp.vertex1] = true;
					datas[i] = temp;
					i++;
				}
				temp = temp.link; //���� ������ ���� ����
			}
			k++;
		}
		
		Node temp;
		int min;
		for(i=0; i<cnt/2; i++)
		{
			min = i;
			for(k=i+1; k<cnt/2; k++)
			{
				if(datas[k].cost < datas[min].cost) {min = k;}
			}
			temp = datas[min];
			datas[min] = datas[i];
			datas[i] = temp;
		}
	}
	
	private void printG()
	{
		for(int i=0; i<heads.length; i++)
		{System.out.println("Vertex"+(char)(i+65)+": "+heads[i]);}
	}
	
	private void printE()
	{
		for(int i=0; i<datas.length; i++)
		{
			if(i != 0 && i%5 == 0) {System.out.println();}
			System.out.print(datas[i]+" ");
		}
		System.out.println();
	}
	
}
