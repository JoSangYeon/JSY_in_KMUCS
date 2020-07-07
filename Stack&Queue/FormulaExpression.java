import java.lang.Object;

public class FormulaExpression
{
	public static int priority(char c) // 연산 우선순위 비교
	{
		if(c == ')')
			return 3;
		else if(c == '*' || c == '/')
			return 2;
		else if(c == '+' || c == '-')
			return 1;
		else if(c == ')')
			return 0;
		else
			return -1;
	}
	
	public static String InfixToPostfix(String str)
	{
		char[] splitStr = str.toCharArray();
		String result = "";
		Stack st = new Stack(splitStr.length); // Stack_Queue.java
		
		for(int i=0; i<splitStr.length; i++)
		{
			switch(splitStr[i])
			{
			case '(':
				st.push(splitStr[i]);
				break;
			case ')':
				Object temp = st.pop();
				while(!temp.equals('('))
				{
					result += temp;
					temp = st.pop();
				}
				break;
			case '*': case '/': case '+': case '-':
				if(priority(splitStr[i]) > priority((char)st.getTop()))
				{
					st.push(splitStr[i]);
				}
				else
				{
					result += st.pop();
					st.push(splitStr[i]);
				}
				break;
			default:
				result += splitStr[i];
				break;
			}
		}
		
		while(!st.isEmpty()) {result += st.pop();}
		
		return result;
	}
	
	public static void main(String[] args)
	{
		String str = "3+(4+2)+1";
		System.out.println(InfixToPostfix(str));
	}
}
