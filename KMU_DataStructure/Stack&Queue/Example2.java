// 프로그래머스 Stack & Queue 연습 문제 2번
// https://programmers.co.kr/learn/courses/30/lessons/42588
// Stack으로 문제 해결(근데 굳이 스택으로 풀어야 되나 싶기도 함)

class Stack
{
    int[] list;
    int top;
    
    public Stack(int size)
    {
        list = new int[size];
        top = -1;
    }
    
    public void push(int data)
      {list[++top] = data;}
    public int pop()
        {return list[top--];}
    public void reset()
        {top = -1;}
}

class Solution
{
    public static int[] solution(int[] heights)
    {
        int size = heights.length;
        int[] answer = new int[size];
        Stack s = new Stack(size);
        
        for(int i=0; i<size; i++)
            {s.push(heights[i]);}
        
        int temp=0;
        for(int i =size-1; i>=0; i--)
        {
            temp = s.pop();
            for(int k=s.top; k>=0 ; k--)
            {
                if(temp < s.list[k])
                    {answer[i] = k+1; break;}
            }
        }
        return answer;
    }
}

public class Example2
{
	public static void print(int[] arr)
	{
		System.out.print("[ ");
		for(int i: arr)
			System.out.print(i+" ");
		System.out.println("]");
	}
	
	public static void main(String[] args)
	{
		int[] arr1 = {6,9,5,7,4};
		int[] arr2 = {3,9,9,3,5,7,2};
		int[] arr3 = {1,5,3,6,7,6,5};
		
		int[] answer = Solution.solution(arr1);
		print(answer);
		answer = Solution.solution(arr2);
		print(answer);
		answer = Solution.solution(arr3);
		print(answer);
		
		/*
		[ 0 0 2 2 4 ]
		[ 0 0 0 3 3 3 6 ]
		[ 0 0 2 0 0 5 6 ]
		*/
	}
}
