package Tree;

public class Tree
{
	private Node root;
	
	public Tree() {root = null;}
	public Tree(String str)
	{
		this();
		
		char[] arr = str.toCharArray();
		
		for(char ch: arr) {this.insert(ch);}
	}
	
	public boolean isEmpty() {return root == null;}
	
	public void insert(char data)
	{
		Node temp = new Node(data);
		if(temp.prio == 4) {setOperand(temp);}
		else {setOperator(temp);}
	}
	
	public void print(int key)
	{
		if(key == 0)
		{
			System.out.print("Inoder: ");
			this.inoder(root);
		}
		else if(key == -1)
		{
			System.out.print("Postoder: ");
			this.postoder(root);
		}
		else if(key == 1)
		{
			System.out.print("Preoder: ");
			this.preoder(root);
		}
		else {System.out.print("Wrong input");}
		System.out.println();
	}
	
	public void draw()
	{
		if(this.isEmpty()) {System.out.println("Tree is empty");}
		else {drawTree(root, 0);}
	}
	
	private void setOperand(Node temp)
	{
		if(this.isEmpty()) {root = temp;}
		else
		{
			Node p = root;
			while(p.right != null) {p = p.right;}
			p.right = temp;
		}
	}
	
	private void setOperator(Node temp)
	{
		if(root.prio >= temp.prio)
		{
			temp.left = root;
			root = temp;
		}
		else
		{
			temp.left = root.right;
			root.right = temp;
		}
	}
	
	private void inoder(Node temp)
	{
		if(temp != null)
		{
			inoder(temp.left);
			System.out.print(temp.data+" ");
			inoder(temp.right);
		}
	}
	
	private void postoder(Node temp)
	{
		if(temp != null)
		{
			postoder(temp.left);
			postoder(temp.right);
			System.out.print(temp.data+" ");
		}
	}
	
	private void preoder(Node temp)
	{
		if(temp != null)
		{
			System.out.print(temp.data+" ");
			preoder(temp.left);
			preoder(temp.right);
		}
	}
	
	private void drawTree(Node p, int level)
	{
		if(p!=null && level <= 20)
		{
			drawTree(p.right, level+4);
			
			for(int i=0; i<=level-1; i++) {System.out.print(" ");}
			System.out.print(p.data+" ");
			
			if(p.left != null && p.right != null) {System.out.println("<");}
			else if(p.right != null) {System.out.println("/");}
			else if(p.left != null) {System.out.println("\\");}
			else {System.out.println();}
			
			drawTree(p.left, level+4);
		}
	}
}
