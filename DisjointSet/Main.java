package DisjointSet;

/*
 * Union-Find 알고리즘은 다른말로 Disjoint-Set(서로소 집합) 알고리즘 이라고 하기도 한다.
 * 다수의 노드들 중에 연결된 노드를 찾거나 노드들을 합칠때 사용하는 알고리즘이다.
 * 
 * 가령 list = [0,1,2,3,4,5,6,7,8,9]의 노드들이 있다고 할때
 * vertex:	0 1 2 3 4 5 6 7 8 9 -> 인덱스
 * link:	0 1 2 3 4 5 6 7 8 9 -> 값
 * 인덱스: 정점의 번호
 * 값:	  연결된 정점
 * 
 * 초기상태는 각 정점들이 자기 자신하고만 연결 되어있다(=각각 연결되어 있지 않음)
 * 여기서 정점을 연결할때 0-1-2-3, 4-5-6-7, 8-9의 형태로 연결한다고 할때
 * list = [0, 0, 1, 2, 4, 4, 5, 6, 8, 8]의 형태로 연결되 것이다.
 * 
 * 인덱스 0~3을 볼때 해석은
 * '노드 3'은 '노드 2'와 연결되어 있다.
 * '노드 2'는 '노드 1'과 연결되어 있다.
 * '노드 1'은 '노드 0'과 연결되어 있다.
 * 따라서 '노드 3'은 '노드 0'과 연결 되어 있다
 * 해당 논리는 재귀방법을 통해 해결할 수 있다. ==> 아래의 find() 메소드이다.
 * 
 * 다음 link() 메소드는 연결정보를 갱신하는 메소드이다.
 * 인자로 받는 a,b 노드를 연결하여 부모 노드를 합치는 메소드이다.
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
		for(int a: list)
			System.out.print(a+" ");
		System.out.println();
		
		/*		 
		list[2] = find(list,2);
		list[3] = find(list,3);
		list[4] = find(list,4);
		*/
		
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
	}
}
