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
			}//정점 v에서 가장 낮은 가중치를 갖는 값을 찾음 가중치를 cost에 저장하고 그에 해당하는 정점은 u에 저장함(u=i)
			
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
		
		int[] cycleTable = new int[numOfVertex]; // 각각의 정점이 이어졌는지 확인 하는 사이클 테이블 선언
		// 배열의 인덱스는 정점을 뜻하고, 해당 인덱스의 값은 연결되어 있는 간선의 root가 되는 정점을 뜻한다.
		// 가령, cycleTable[2] = 3 이라면, 정점 2는 정점3과 열결되어 있다. 라는 뜻이다.
		//{0,1,2,3,4,5,6}으로 초기화 최초의 MST에서 각각의 정점은 서로 연결되어 있지 않음을 뜻함
		for(int k=0; k<cycleTable.length; k++) {cycleTable[k] = k;}
		
		while(n != numOfVertex-1) // MST의 간선은 정점의 갯수-1
		{
			int v1 = datas[i].vertex1; //인덱스 접근용 변수 v1 선언및 datas에 저장된 정점1로 초기화
			int v2 = datas[i].vertex2; //인덱스 접근용 변수 v2 선언및 datas에 저장된 정점2로 초기화
			
			if(cycleTable[v1] != cycleTable[v2])
				// cycleTable[v1] != cycleTable[v2] ==> v1과 v2의 연결된 정점이 서로 다르다면 if문 실행(v1,v2을 연결)
				{
					
					int v1root = cycleTable[v1];
					int v2root = cycleTable[v2];
					for(int k=0; k<cycleTable.length; k++) //cycleTable 각 요소에 접근하여
						// cycleTable[k]==cycleTable[v2] => 정점 k와 v2가 연결되어 있다면, cycleTable[k] = cycleTable[v1]로 연결함
					{
						if(cycleTable[k] == v2root) 
							{cycleTable[k] = v1root;}
					}
					
					// result와 totalCost에 간선에대한 연결 정보를 추가한다.
					result += "Edge"+(n+1)+": "+datas[i]+"\n";
					totalCost += datas[i].cost;
					n++; //연결된 간선 갯수 갱신
				}
			i++; //접근 인덱스 갱신
		}
		// 최종결과값 리턴
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
				temp = temp.link; //다음 정점에 대해 접근
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
