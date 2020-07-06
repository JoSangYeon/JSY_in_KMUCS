class Stack
{
	int[] list;
	int top;
	
	public Stack(int size)
	{
		list = new int[size];
		top = -1;
	}

	public boolean isEmpty() {return top == -1;}
	public boolean isFull() {return top == list.length;}
	
	public void push(int data) {list[++top] = data;}
	public int pop() {return list[top--];}
	
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
	int[] list;
	int front, rear;
	
	public Queue(int size)
	{
		list = new int[size];
		front = rear = 0;
	}
	
	public boolean isEmpty() {return front == rear;}
	public boolean isFull() {return rear == list.length;}
	
	public void enQueue(int data) {list[rear++] = data;}
	public int deQueue() {return list[front++];}
	
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

public class Stack_Queue
{
	public static void main(String[] args)
	{
		Stack s = new Stack(10);
		Queue q = new Queue(10);
		
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
	}
}
