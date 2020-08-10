package Graph.G1;

public class Stack
{
	private int top;
	private int[] list;
	private int size;
	
	public Stack(int size)
	{
		this.size = size;
		list = new int[size];
		top = -1;
	}
	
	public boolean isEmpty() {return top == -1;}
	public boolean isFull() {return top == size;}
	
	public void push(int data)
	{
		if(this.isFull()) {return;}
		
		list[++top] = data;
	}
	
	public int pop()
	{
		if(this.isEmpty()) {return -1;}
		
		int temp = list[top--];
		return temp;
	}
	
	public int getTop()
	{
		return list[top];
	}
}
