package DisjointSet;

/*
 * Union-Find �˰����� �ٸ����� Disjoint-Set(���μ� ����) �˰��� �̶�� �ϱ⵵ �Ѵ�.
 * �ټ��� ���� �߿� ����� ��带 ã�ų� ������ ��ĥ�� ����ϴ� �˰����̴�.
 * 
 * ���� list = [0,1,2,3,4,5,6,7,8,9]�� ������ �ִٰ� �Ҷ�
 * vertex:	0 1 2 3 4 5 6 7 8 9 -> �ε���
 * link:	0 1 2 3 4 5 6 7 8 9 -> ��
 * �ε���: ������ ��ȣ
 * ��:	  ����� ����
 * 
 * �ʱ���´� �� �������� �ڱ� �ڽ��ϰ� ���� �Ǿ��ִ�(=���� ����Ǿ� ���� ����)
 * ���⼭ ������ �����Ҷ� 0-1-2-3, 4-5-6-7, 8-9�� ���·� �����Ѵٰ� �Ҷ�
 * list = [0, 0, 1, 2, 4, 4, 5, 6, 8, 8]�� ���·� ����� ���̴�.
 * 
 * �ε��� 0~3�� ���� �ؼ���
 * '��� 3'�� '��� 2'�� ����Ǿ� �ִ�.
 * '��� 2'�� '��� 1'�� ����Ǿ� �ִ�.
 * '��� 1'�� '��� 0'�� ����Ǿ� �ִ�.
 * ���� '��� 3'�� '��� 0'�� ���� �Ǿ� �ִ�
 * �ش� ���� ��͹���� ���� �ذ��� �� �ִ�. ==> �Ʒ��� find() �޼ҵ��̴�.
 * 
 * ���� link() �޼ҵ�� ���������� �����ϴ� �޼ҵ��̴�.
 * ���ڷ� �޴� a,b ��带 �����Ͽ� �θ� ��带 ��ġ�� �޼ҵ��̴�.
 */

public class Main
{	
	static int[] list = {0,1,2,3,4,5,6,7,8,9};
	
	public static int find(int[] list, int vertex)
	{
		if(list[vertex] == vertex) {return vertex;}
		else {return find(list, list[vertex]);}
	}
	
	public static void link(int[] list, int a, int b)
	{
		int i = find(list, a);
		int j = find(list, b);
		
		if(i<j) {list[j] = i;}
		else {list[i] = j;}
	}
	
	public static void main(String[] args)
	{
		UnionFind data = new UnionFind(list);
		
		System.out.print("���� ��: ");
		data.print();
		
		//��� ����: 0-1-2-3, 4-5-6-7, 8-9
		
		data.union(0, 1);
		data.union(1, 2);
		data.union(2, 3);
		
		data.union(4, 5);
		data.union(5, 6);
		data.union(6, 7);
		
		data.union(8, 9);
		
		System.out.print("���� ��:");
		data.print();
		
		System.out.println("��� 0�� ��� 3�� ���� �Ǿ� �ִ°�?: " + data.isSameParent(0, 3));
		System.out.println("��� 5�� ��� 7�� ���� �Ǿ� �ִ°�?: " + data.isSameParent(5, 7));
		System.out.println("��� 2�� ��� 8�� ���� �Ǿ� �ִ°�?: " + data.isSameParent(2, 8));
		
		/* ���� ���
���� ��: [ 0 1 2 3 4 5 6 7 8 9 ]
���� ��:[ 0 0 0 0 4 4 4 4 8 8 ]
��� 0�� ��� 3�� ���� �Ǿ� �ִ°�?: true
��� 5�� ��� 7�� ���� �Ǿ� �ִ°�?: true
��� 2�� ��� 8�� ���� �Ǿ� �ִ°�?: false
		 */
		
		
		/*	
		for(int a: list)
			System.out.print(a+" ");
		System.out.println();
		
			 
		list[2] = find(list,2);
		list[3] = find(list,3);
		list[4] = find(list,4);
		
		
		link(list, 0, 1);
		link(list, 1, 2);
		link(list, 2, 3);
		
		link(list, 4, 5);
		link(list, 5, 6);
		link(list, 6, 7);
		
		link(list, 8, 9);
		
		for(int a: list)
			System.out.print(a+" ");
		System.out.println();
		
		//��� ���
		//0 1 2 3 4 5 6 7 8 9 
		//0 0 0 0 4 4 4 4 8 8 
		*/
	}
}
