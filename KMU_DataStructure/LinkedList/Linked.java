/*
 * Singly Linked List 구현하기
 * ADT:		생성자
 * 		Insert
 * 		Delete
 * 		Search
 * 		print
 * 		.
 * 		.
 * 		.
 */

class Node
{
	int data;
	Node next;
	public Node(int data)
	{
		this.data = data;
		next = null;
	}
}

class LinkedList
{
	private int len;
	private Node head;
	
	public LinkedList()
	{
		len = 0;
		head = null;
	}
	
	public int length() {return this.len;}
	
	public void insert(int data)
	{
		if(this.isEmpty()) {head = new Node(data);}
		else
		{
			Node p = head;
			while(p.next != null) {p = p.next;}
			p.next = new Node(data);
		}
		len++;
	}
	public void delete(int data)
	{
		Node q=null, p=head;
		
		if(this.isEmpty()) {System.out.println("List is Empty");}
		else if(head.data == data) {head = head.next;}
		else
		{
			while(p.next != null && p.data != data) {q=p; p = p.next;}
			
			if(p.next != null) {q.next = p.next;}
			else {System.out.println("Not found");}
		}
		p = null;
		len--;
	}
	
	public int search(int data)
	{
		Node p=head;
		int idx = 0;
		
		if(this.isEmpty()) {return -1;}
		else if(head.data == data) {return 0;}
		else
		{
			while(p != null && p.data != data) {p = p.next; idx++;}
			
			if(p != null) {return idx;}
			else {System.out.println("Not found"); return -1;}
		}
	}
	
	public String toString()
	{
		String result= "[ ";
		
		Node p = head;
		while(p != null)
		{
			result += p.data+" "; 
			p = p.next;
		}
		result += "]";
		
		return result;
	}
	
	public void print() {System.out.println(this.toString());}
	
	private boolean isEmpty() {return head == null && len == 0;}
}

public class Linked
{
	public static void main(String[] args)
	{
		LinkedList li = new LinkedList();
		
		li.print(); // [ ]
		
		li.insert(10);
		li.insert(20);
		
		li.print(); // [ 10 20 ]
		
		li.insert(30);
		li.insert(40);
		li.insert(25);
		li.insert(35);
		
		System.out.println(li);  // [ 10 20 30 40 25 35 ]
		
		li.delete(20);
		li.delete(40);
		
		System.out.println(li); // [ 10 30 25 35 ]
		
		System.out.println(li.search(10)); // 0
		System.out.println(li.search(35)); // 3
		System.out.println(li.length());   // 4
	}
}
