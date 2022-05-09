package PrimeNumber;

public class Eratos
{
	int[] table;
	int size;
	
	public Eratos(int size)
	{
		this.size = size;
		table = new int[size+1];
		
		// 2���� �ش� ����(size)���� �ʱ�ȭ
		for(int i=2; i<=size; i++)
			{table[i] = i;}
		
		// 2���� �����Ͽ� �ش� ������ ����� �ش��ϴ� ���� ��� ����
		// �̶� �ڱ��ڽ��� ������ �ʰ�(j=2*i), �̹� ������ ���� �ǳʶڴ�.
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
