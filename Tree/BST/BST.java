package BST;

public class BST
{
	private Node root;
	
	public BST() {root = null;}
	
	public boolean isEmpty() {return root == null;}
	
	public void insertData(int data) {root = this.insert(root, data);}
	
	public void deleteData(int data)
	{
		if(this.isEmpty()) {System.out.println("Tree is empty"); return;}
		else {this.delete(root, data);}
	}
	
	public void searchData(int data)
	{
		if(this.isEmpty()) {System.out.println("Tree is empty");}
		else
		{
			Node temp = search(root, data);
			
			if(temp != null)
			{System.out.println(data+" Found!!");}
		}
	}
	
	public void drawTree()
	{
		if(this.isEmpty()) {System.out.println("Tree is empty");}
		else {this.draw(root, 0);}
	}
	
	private Node insert(Node temp, int data)
	{
		if(temp == null) {temp = new Node(data);}
    	else if(data > temp.data) {temp.right = insert(temp.right, data);}
		else if(data < temp.data) {temp.left = insert(temp.left, data);}
		
		return temp;
	}
	
	private Node delete(Node temp, int data)
	{
		if(temp != null)
		{
			Node p;
			
			if(data < temp.data)
				{temp.left = delete(temp.left, data);}
			else if(data > temp.data)
				{temp.right = delete(temp.right, data);}
			else if(temp.left == null && temp.right == null)
				{temp = null;}
			else if(temp.left == null)
			{
				p = temp;
				temp = temp.right;
				p = null;
			}
			else if(temp.right == null)
			{
				p = temp;
				temp = temp.left;
				p = null;
			}
			else
			{
				p = findMax(temp.left);
				temp.data = p.data;
				temp.left = delete(temp.left, temp.data);
				
			}
		}
		return temp;
	}
	
	private Node search(Node temp, int data)
	{
		if(temp == null) {System.out.println("Not found"); return null;}
		else
		{
			if(data == temp.data) {return temp;}
			else if(data > temp.data) {return search(temp.right, data);}
			else if(data < temp.data) {return search(temp.left, data);}
		}
		return temp;
	}
	
	private void draw(Node p, int level)
	{
		if(p!=null && level <= 20)
		{
			draw(p.right, level+4);
			
			for(int i=0; i<=level-1; i++) {System.out.print(" ");}
			System.out.print(p.data);
			
			if(p.left != null && p.right != null) {System.out.println("<");}
			else if(p.right != null) {System.out.println("/");}
			else if(p.left != null) {System.out.println("\\");}
			else {System.out.println();}
			
			draw(p.left, level+4);
		}
	}
	
	private Node findMax(Node p)
	{
		if(p.right == null) {return p;}
		else {return findMax(p.right);}
	}
}
