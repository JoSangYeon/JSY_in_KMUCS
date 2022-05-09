package MST;

/*
 * �ּҽ���Ʈ��(Minimum Cost Spanning Trees == MST)����
 * 	- cost�� ���� ���� ����Ʈ��
 * 
 * Method 1: ��Į�� �˰���
 * 	- �ѹ��� �� ������ �ּ� ��� ���� Ʈ���� ����
 * 	- MST T�� ���Ե� ������ ����� ũ�� ������ ����(sort ���� �ʿ�)
 * 	- T�� n-1���� ������ ���Ե� ������ ���� �߰�(n�� ������ ����)
 * 
 * Method 2: ������ �˰���
 * 	- �ѹ��� �� ������ �ּ� ��� ���� Ʈ���� ����
 *  - �ϳ��� �������� �� Ʈ�� T���� ����
 * 	- �� �ܰ迡�� ���õ� ������ ������ Ʈ��
 * 	- T�� n-1���� ������ ���Եɶ����� ���� �߰�(n�� ������ ����)
 */

public class Main
{
	public static void main(String[] args)
	{
		int[][] costMatrix1 =
			{
					{100,6,1,5,100,100}, //v1
					{6,100,4,100,3,100}, //v2
					{1, 4, 100, 5, 6,5}, //v3
					{5,100,5,100,100,2}, //v4
					{100,3,6,100,100,6}, //v5
					{100,100,5,2,6,100}  //v6
			};
		int[][] costMatrix2 =
			{
				{0,28,100,100,100,10,100},  //1
				{28,0,16,100,100,100,14},   //2
				{100,16,0,12,100,100,100},  //3
				{100,100,12,0,22,100,18},    //4
				{100,100,100,22,0,25,24},   //5
				{10,100,100,100,25,0,100},  //6
				{100,14,100,18,24,100,0}    //7
			};
		
		MST m1 = new MST(costMatrix1);
		MST m2 = new MST(costMatrix2);
		m1.print(0);
		System.out.println();
		m1.print(1);
		System.out.println();
		System.out.println(m1.PrimMethod(0));
		System.out.println(m1.KruskalMethod());
		System.out.println();
		
		m2.print(0);
		System.out.println();
		m2.print(1);
		System.out.println();
		System.out.println(m2.PrimMethod(0));
		System.out.println(m2.KruskalMethod());
		System.out.println();
		
		
		/*
	��°��: 
VertexA: A:6:B A:1:C A:5:D 
VertexB: B:6:A B:4:C B:3:E 
VertexC: C:1:A C:4:B C:5:D C:6:E C:5:F 
VertexD: D:5:A D:5:C D:2:F 
VertexE: E:3:B E:6:C E:6:F 
VertexF: F:5:C F:2:D F:6:E 

A:1:C D:2:F B:3:E B:4:C A:5:D 
C:5:D C:5:F C:6:E A:6:B E:6:F 

MST Path: A-->C-->B-->E-->F-->D
MST Cost:  1  4  3  6  2  
Total Weight: 16

Edge1: A:1:C
Edge2: D:2:F
Edge3: B:3:E
Edge4: B:4:C
Edge5: A:5:D
Total Cost: 15

VertexA: A:28:B A:10:F 
VertexB: B:28:A B:16:C B:14:G 
VertexC: C:16:B C:12:D 
VertexD: D:12:C D:22:E D:18:G 
VertexE: E:22:D E:25:F E:24:G 
VertexF: F:10:A F:25:E 
VertexG: G:14:B G:18:D G:24:E 

A:10:F C:12:D B:14:G B:16:C D:18:G 
D:22:E E:24:G E:25:F A:28:B 

MST Path: A-->F-->E-->D-->C-->B-->G
MST Cost:  10  25  22  12  16  14  
Total Weight: 99

Edge1: A:10:F
Edge2: C:12:D
Edge3: B:14:G
Edge4: B:16:C
Edge5: D:22:E
Edge6: E:25:F
Total Cost: 99
		 */
	}
}
