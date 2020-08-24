/*
 * Hashing 정의 및 구현
 */

package Hashing;

public class Main
{
	public static void main(String[] args)
	{
		Hash h = new Hash(5);
		
		h.insert(1);
		h.insert(2);
		h.insert(3);
		h.insert(4);
		h.insert(5);
		h.insert(6);
		h.insert(7);
		h.insert(8);
		h.insert(9);
		h.insert(10);
		
		
		System.out.println("find(5): "+h.find(5));
		h.print();		
		
		h.delete(5);
		h.delete(10);
		System.out.println();
		System.out.println("find(5): "+h.find(5));
		h.print();
	}
}
/*
idx 0: [ 5 10 ]
idx 1: [ 1 6 ]
idx 2: [ 2 7 ]
idx 3: [ 3 8 ]
idx 4: [ 4 9 ]

idx 0: [ ]
idx 1: [ 1 6 ]
idx 2: [ 2 7 ]
idx 3: [ 3 8 ]
idx 4: [ 4 9 ]
*/