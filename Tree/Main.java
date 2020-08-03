//Tree 구현 & 순회 알고리즘 구현

package Tree;

public class Main
{
	public static void main(String[] args)
	{
		Tree t1 = new Tree("3+5+4");
		t1.print(0);
		t1.print(-1);
		t1.print(1);
		/*
		 * Inoder: 3 + 5 + 4 
		 * Postoder: 3 5 + 4 + 
		 * Preoder: + + 3 5 4 
		 */
		
		t1.draw();
		/*
		     4 
		+ <
        		5 
   			+ <
        		3 
		 */
	}
}
