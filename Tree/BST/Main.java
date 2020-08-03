package BST;

public class Main
{
	public static void main(String[] args)
	{
		BST t1 = new BST();
		
		t1.insertData(10);
		t1.insertData(30);
		t1.insertData(5);
		t1.insertData(15);
		t1.insertData(2);
		
		t1.searchData(30);
		t1.searchData(3330);
		
		t1.drawTree();
		System.out.println();
				
		t1.deleteData(2);
		t1.drawTree();
		System.out.println();
		t1.deleteData(10);
		t1.drawTree();
		System.out.println();
		t1.deleteData(30);
		t1.drawTree();
		System.out.println();
		
		/* 
30 Found!!
Not found

    30\
        15
10<
    5\
        2
-----------------
    30\
        15
10<
    5
-----------------
    30\
        15
5/
-----------------
    15
5/
-----------------
		 */
	}
}
