package PrimeNumber;

public class Eratos
{
	int[] table;
	int size;
	
	public Eratos(int size)
	{
		this.size = size;
		table = new int[size+1];
		
		// 2부터 해당 숫자(size)까지 초기화
		for(int i=2; i<=size; i++)
			{table[i] = i;}
		
		// 2부터 시작하여 해당 숫자의 배수에 해당하는 수는 모두 지움
		// 이때 자기자신은 지우지 않고(j=2*i), 이미 지워진 수는 건너뛴다.
		for(int i=2; i<=size; i++)
		{
			if(table[i]==0) {continue;}
			for(int j=2*i; j<=size; j+=i)
				{table[j] = 0;}
		}
	}
	
	public void show()
	{
		System.out.print("[ ");
		for(int i=0; i<size; i++)
		{
			if(table[i]!=0) {System.out.print(table[i]+" ");}
		}
		System.out.println("]");
	}
}
