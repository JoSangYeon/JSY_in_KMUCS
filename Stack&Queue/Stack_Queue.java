import java.lang.Object;

class Stack
{
	Object[] list;
	int top;
	
	public Stack(int size)
	{
		list = new Object[size];
		top = -1;
	}

	public boolean isEmpty() {return top == -1;}
	public boolean isFull() {return top == list.length;}
	
	public void push(int data) 
	{
		if(this.isFull()) {return;}
		else {list[++top] = data;}
	}
	public Object pop() 
	{	
		if(this.isEmpty()) {return 0;}
		else {return list[top--];}
	}
	
	public void printStack()
	{
		if(this.isEmpty()) {System.out.println("[ ]");}
		else
		{
			System.out.print("[ ");
			for(int i=0; i<top; i++)
				{System.out.print(list[i] + " ");}
			System.out.println(list[top]+"* ]");
		}
	}
}

class Queue
{
	Object[] list;
	int front, rear;
	
	public Queue(int size)
	{
		list = new Object[size];
		front = rear = 0;
	}
	
	public boolean isEmpty() {return front == rear;}
	public boolean isFull() {return rear == list.length;}
	
	public void enQueue(int data)
	{
		if(this.isFull()) {return;}
		else {list[rear++] = data;}
	}
	public Object deQueue()
	{
		if(this.isEmpty()) {return 0;}
		else {return list[front++];}
	}
	
	public void printQueue()
	{
		if(this.isEmpty()) {System.out.println("[ ]");}
		else
		{
			System.out.print("[ *");
			for(int i=front; i<rear; i++)
				{System.out.print(list[i]+" ");}
			System.out.println("]");
		}
	}
}

class CircularQueue
{
	Object[] list;
	int front, rear, flag;
	int size;
	
	public CircularQueue(int size)
	{
		list = new Object[size];
		front = 0; rear = 0;
		flag = 0;
		this.size = size;
	}
	
	public boolean isEmpty() {return front==rear && flag == 0;}
	public boolean isFull() {return front==rear && flag == 1;}
	
	public void enQueue(int value)
	{
		if(this.isFull()) {return;}
		else
		{
			list[rear] = value;
			rear = (rear+1)%size;
			flag = 1;
		}
	}
	public Object deQueue()
	{
		Object item;
		if(this.isEmpty()) {return -1;}
		else
		{
			item = list[front];
			list[front] = null;
			front = (front+1)%size;
			flag = 0;
			return item;
		}
	}
	
	public void printQueue()
	{
		if(this.isEmpty()) {System.out.println("[ ]"); return;}
		else
		{
			System.out.print("[ ");
			for(int i=0; i<size; i++)
			{
				if(list[i]==null) {continue;}
				if(i == front) {System.out.print("*"+list[i]+" "); continue;}
				else {System.out.print(list[i] + " ");}
			}
			System.out.println("]");
		}
	}
}
public class Stack_Queue
{
	public static void main(String[] args)
	{
		Stack s = new Stack(10);
		Queue q = new Queue(10);
		CircularQueue cq = new CircularQueue(10);
		
		s.push(3);   // stack = [3*]
		s.push(4);   // stack = [3 4*]
		s.push(5);   // stack = [3 4 5*]
		s.push(2);   // stack = [3 4 5 2*]
		s.printStack();
		
		s.pop();   // stack = [3 4 5*]
		s.pop();   // stack = [3 4*]
		s.printStack();
		
		
		q.enQueue(3);    // queue = [*3]
		q.enQueue(4);    // queue = [*3 4]
		q.enQueue(5);    // queue = [*3 4 5]
		q.enQueue(2);    // queue = [*3 4 5 2]
		q.printQueue();
		
		q.deQueue();    // queue = [*4 5 2]
		q.deQueue();    // queue = [*5 2]
		q.printQueue();
		
		cq.enQueue(3);
		cq.enQueue(4);
		cq.enQueue(5);
		cq.enQueue(2);
		cq.printQueue();
		
		cq.deQueue();
		cq.deQueue();
		cq.printQueue();
	}
}
