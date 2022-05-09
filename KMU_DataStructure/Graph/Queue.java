package Graph.G1;

public class Queue
{
	private int front;
	private int rear;
	private int[] list;
	private int size;
	private boolean flag;
	
	public Queue(int size)
	{
		this.size = size;
		front = rear = 0;
		list = new int[size];
		
		flag = false;
	}
	
	public boolean isEmpty() {return front==rear && !flag;}
	public boolean isFull() {return front==rear && flag;}
	
	public void Enqueue(int data)
	{
		if(this.isFull()) {return;}
		
		list[rear++] = data;
		rear = rear%size;
		flag = true;
	}
	
	public int Dequeue()
	{
		if(this.isEmpty()) {return -1;}
		
		int temp = list[front++];
		front = front%size;
		flag = false;
		
		return temp;
	}
}
