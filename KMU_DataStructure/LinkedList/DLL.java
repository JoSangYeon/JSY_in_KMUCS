/* 2020.07.21
 * Doubly Linked List(DLL) 구현
 * dNode class 선언 dNode는 2개의 dNode객체를 멤버로 갖는다.
 * 
 * ADT:
 * 		생성자
 * 		Insert
 * 		delete
 * 		print
 * 		search
 * 		.
 * 		.
 * 		.
 */

class dNode
{
	int data;
	dNode left, right;
	
	public dNode(int data)
	{
		this.data = data;
		left = right = null;
	}
}

class DoublyLinkedList
{
	private int len;
	private dNode head;
	
	public DoublyLinkedList()
	{
		len = 0;
		head = null;
	}
	
	public boolean isEmpty() {return head == null && len == 0;}
	public int length() {return this.len;}
	
	public void insert(int data)
	{
		if(this.isEmpty()) {head = new dNode(data);}
		else
		{
			dNode p = head;
			while(p.right != null) {p = p.right;}
			
			dNode temp = new dNode(data);
			
			p.right = temp;
			temp.left = p;
		}
		len++;
	}
	
	public void delete(int data)
	{
		if(this.isEmpty()) {System.out.println("List is empty");}
		else
		{
			dNode p = head;
			
			if(head.data == data)
				{head = head.right;}
			else
			{
				while(p.right != null && p.data != data) 
					{p = p.right;}
				
				if(p.right != null) // 삭제할 노드가 가운데 있을 경우
				{	
					p.left.right = p.right;
					p.right.left = p.left;
				}
				else if(p.right == null) // 삭제할 노드가 끝에 있을 경우
					{p.left.right = null;}
				else {System.out.println("Not found");}
			}
			p = null;
			len--;
		}
	}
	
	public int search(int data)
	{
		int idx = 0;
		
		if(this.isEmpty()) {System.out.println("List is empty"); return -1;}
		else
		{
			dNode p = head;
			if(head.data == data) {return 0;}
			else
			{
				while(p.right != null && p.data != data) 
					{p = p.right; idx++;}
			
				if(p.right != null) // 탐색할 노드가 가운데 있을 경우
					{return idx;}
				else if(p.right == null && p.data == data) // 탐색할 노드가 끝에 있을 경우
					{return len-1;}
				else {System.out.println("Not found"); return -1;}
			}
		}
	}
	
	public String toString()
	{
		String result = "[ ";
		
		dNode p = head;
		
		while(p != null)
		{
			result += p.data + " ";
			p = p.right;
		}
		result += "]";
		
		return result;
	}
	
	public void print()
		{System.out.println(this.toString());}
}

public class DLL
{
	public static void main(String[] args)
	{
		DoublyLinkedList dll = new DoublyLinkedList();
		
		dll.insert(10); 
		dll.insert(20);
		dll.insert(30);
		dll.insert(40);
		dll.insert(50);
		
		dll.print(); // [ 10 20 30 40 50 ]
		
		dll.delete(20);
		dll.print(); // [ 10 30 40 50 ]
		
		System.out.println(dll.search(10)); // 0
		System.out.println(dll.search(20)); // Not found
		System.out.println(dll.search(30)); // 1
		System.out.println(dll.search(50)); // 3
	}
}
