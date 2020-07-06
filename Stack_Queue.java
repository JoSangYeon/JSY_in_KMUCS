class Stack  // 일반 스택
{
	private int[] stackList;
	private int top;
	
	public Stack(int size)
	{
		stackList = new int[size];
		top = -1;
	}
	
	public boolean isFull()
	{return top==(stackList.length-1);}
	
	public boolean isEmpty()
	{return top==-1;}
	
	public void push(int value)
	{
		if(this.isFull())
			System.out.println("Stack is full");
		else
			stackList[++top] = value;
	}
	
	public int pop()
	{
		if(this.isEmpty())
		{
			System.out.println("Stack is empty");
			return 0;
		}
		else
			return stackList[(top)--];
	}
	
	public void displayStack()
	{
		for(int i=top; i>=0; i--)
			System.out.println(stackList[i]);
	}
}

class nStack // 다중스택
{
	int[] topList;
	int[][] stackList;
	
	public nStack(int size)
	{
		stackList = new int[size][size];
		topList = new int[size];
		
		for(int i=0; i<size; i++)
			topList[i] = -1;
	}
	
	public boolean isFull(int point)
	{return topList[point]==(stackList[point].length-1);}
	
	public boolean isEmpty(int point)
	{return topList[point]==-1;}
	
	public void push(int point, int value)
	{
		if(this.isFull(point))
			System.out.println("Stack is full");
		else
			stackList[point][++topList[point]] = value;
	}
	
	public int pop(int point)
	{
		if(this.isEmpty(point))
		{
			System.out.println("Stack is empty");
			return 0;
		}
		else
			return stackList[point][topList[point]--];
	}
	
	public void displayStack()
	{
		for(int i=0; i<stackList.length; i++)
		{
			if(this.isEmpty(i))
			{
				System.out.println("###No."+(i+1)+" Stack###\nEmpty!\n");
				continue;
			}
			System.out.println("###No."+(i+1)+" Stack###");
			for(int j=topList[i]; j>=0; j--)
			{
				System.out.println(stackList[i][j]);
			}
			System.out.println();	
		}
	}
	public void displayStack(int point)
	{
		if(this.isEmpty(point))
		{
			System.out.println("###No."+(point+1)+" Stack###\nEmpty!\n");
			return;
		}
		System.out.println("###No."+(point+1)+" Stack###");
		for(int i=topList[point]; i>=0; i--)
			System.out.println(stackList[point][i]);
	}
}

class Queue
{
	int[] queueList;
	int front, rear;
	
	public Queue(int size)
	{
		queueList = new int[size];
		front = -1;
		rear = -1;
	}
	
	public boolean isFull()
	{return rear == (queueList.length-1);}
	
	public boolean isEmpty()
	{return front == rear;}
	
	public void enQueue(int value)
	{
		if(this.isFull())
			System.out.println("Queue is full");
		else
			queueList[++rear] = value;
	}
	public int deQueue()
	{
		if(this.isEmpty())
		{
			System.out.println("Queue is empty");
			return 0;
		}
		else
			return queueList[++front];
	}
	
	public void displayQueue()
	{
		if(this.isEmpty())
		{
			System.out.println("Queue is empty");
			return;
		}
		else
		{
			for(int i=front+1; i<=rear; i++)
				System.out.println(queueList[i]);
		}
	}
}

class CircularQueue
{
	int[] queueList;
	int front, rear, flag;
	
	public CircularQueue(int size)
	{
		queueList = new int[size];
		front = 0; rear = 0;
		flag = 0;
	}
	
	public void enQueue(int value)
	{
		if(front==rear && flag==1)
			return;
		else
		{
			queueList[rear] = value;
			rear = (rear+1)%queueList.length;
			flag = 1;
		}
	}
	public int deQueue()
	{
		int item;
		if(flag==0 && front==rear)
			return -1;
		else
		{
			item = queueList[front];
			front = (front+1)%queueList.length;
			flag = 0;
			return item;
		}
	}
	
	public void displayQueue()
	{
		for(int i=0; i<queueList.length; i++)
			System.out.println(queueList[i]);
	}
}

public class Stack_Queue
{
	public static void main(String[] args)
	{
		Stack s1 = new Stack(5);
		nStack ns1 = new nStack(5);
		Queue q = new Queue(5);
		CircularQueue cq = new CircularQueue(3);
		
		cq.enQueue(3);
		cq.enQueue(4);
		cq.enQueue(5);
		cq.displayQueue();
		System.out.println();
		
		System.out.println(cq.deQueue());
		System.out.println(cq.deQueue());
		System.out.println();
		
		cq.enQueue(6);
		cq.enQueue(7);
		cq.displayQueue();
		System.out.println();

		
		q.enQueue(3);
		q.enQueue(4);
		q.enQueue(5);
		System.out.println(q.deQueue());
		System.out.println();
		System.out.println(q.deQueue());
		System.out.println();
		q.enQueue(6);
		q.enQueue(7);
		System.out.println(q.deQueue());
		System.out.println();
		q.displayQueue();
		
		ns1.push(0, 3);
		ns1.push(0, 2);
		ns1.push(0, 1);
		ns1.pop(0);
		
		ns1.push(1,5);
		ns1.push(1,2);
		ns1.push(3,4);
		ns1.push(0,5);
		ns1.displayStack();
		
		s1.push(3);
		s1.push(5);
		System.out.println(s1.pop());
		s1.displayStack();
		s1.push(10);
		System.out.println();
		s1.displayStack();
	}
}
