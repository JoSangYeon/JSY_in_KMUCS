/* Linked Stack & Linked Queue 구현
 * 
 * Node class는 Linked.java에서 import
 */

class LinkedStack
{
	private int len;
	private Node top;
	
	public LinkedStack() {top = null; len = 0;}
	
	public boolean isEmpty() {return top == null && len == 0;}
	
	public int length() {return this.len;}
	
	public void push(int data)
	{
		if(this.isEmpty()) {top = new Node(data);}
		else
		{
			Node p = new Node(data);
			p.next = top;
			top = p;
		}
		len++;
	}
	
	public int pop()
	{
		int result;
		Node temp;
		
		if(this.isEmpty()) {System.out.println("Stack is empty"); return -1;}
		else
		{
			temp = top;
			result = temp.data;
			top = top.next;
			temp = null;
			
			len--;
			
			return result;
		}
	}
	
	public String toString()
	{
		String result = "";
		Node p = top;
		
		while(p != null)
		{
			if(p == top) {result += "*"+p.data+" "; p = p.next; continue;}
			result = p.data + " " + result;
			p = p.next;
		}
		
		result = "[ " + result;
		result += "]";
		return result;
	}
	
	
	public void print() {System.out.println(this.toString());}
}

class LinkedQueue
{
	private int len;
	private Node front, rear;
	
	public LinkedQueue()
	{
		len = 0;
		front = rear = null;
	}
	
	public boolean isEmpty() {return front == rear && len == 0;}
	
	public int length() {return this.len;}
	
	public void enQueue(int data)
	{
		if(this.isEmpty()) {rear = new Node(data); front = rear;}
		else
		{
			Node temp = new Node(data);
			rear.next = temp;
			rear = temp;
		}
		len++;
	}
	
	public int deQueue()
	{
		if(this.isEmpty()) {System.out.println("Queue is empty"); return -1;}
		else
		{
			Node p = front;
			int result = front.data;
			
			front = front.next;
			p = null;
			len--;
			
			return result; 
		}
	}
	
	public String toString()
	{
		if(this.isEmpty()) {return "[ ]";}
		else
		{
			String result = "[ >";
			Node p = front;
			
			while(p != null)
			{
				result += p.data+" ";
				p = p.next;
			}
			
			result = result.substring(0, result.length()-1);
			result += "< ]";
			
			return result;
		}
	}
	
	public void print() {System.out.println(this.toString());}
}

public class LinkedSTQ
{
	public static void main(String[] args)
	{
		LinkedStack lst = new LinkedStack();
		LinkedQueue lq = new LinkedQueue();
		
		lst.print(); // [ ]
		
		lst.push(30);
		lst.push(20);
		lst.push(10);
		
		lst.print(); // [ *10 20 30 ]
		
		int temp = lst.pop();
		System.out.println(temp); // 10
		System.out.println(lst.length()); // 2
		System.out.println(lst); // [ *20 30 ]
		
		/*******************/
		
		lq.print(); // [ ]
		
		lq.enQueue(30);
		lq.enQueue(20);
		lq.enQueue(10);
		
		lq.print(); // [ >30 20 10< ]
		
		temp = lq.deQueue();
		System.out.println(temp); // 30
		System.out.println(lq.length()); // 2
		System.out.println(lq); // [ >20 10< ]
	}
}
