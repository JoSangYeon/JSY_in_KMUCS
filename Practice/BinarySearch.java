/*
 * 본격적인 공부에 앞서 이진탐색 알고리즘으로 뇌풀기
 */

public class BinarySearch
{
	public static void Method1(int[] arr, int key) //while문 사용
	{
		int n = arr.length;
		int low=0; int high= n-1;
		int mid;
		
		boolean found = false;
		
		while(!found && low < high)
		{
			mid = (low+high)/2;
			
			if(key == arr[mid]) {found = true;}
			else if(key < arr[mid]) {high = mid-1;}
			else //(key > arr[mid])
				{low = mid+1;}
		}
		
		if(found) {System.out.println("Found");}
		else {System.out.println("Not found");}
	}
	
	public static boolean Method2(int[] arr, int key, int low, int high) //재귀함수 사용
	{
		if(low < high)
		{
			int mid = (low+high)/2;
			
			if(key == arr[mid])
				{System.out.println("Found"); return true;}
			else if(key < arr[mid])
				{
					high = mid-1;
					return Method2(arr, key, low, high);
				}
			else //(key > arr[mid])
				{
					low = mid+1;
					return Method2(arr, key, low, high);
				}
		}
		else
			{System.out.println("Not found"); return false;}
		
	}
	
	public static void main(String[] args)
	{
		int[] array = {1,2,3,4,5,6,7,8,9,10};
		
		Method1(array,11); //Not found
		Method1(array, 8); //Found
		
		Method2(array,11, 0, 9); //Not found
		Method2(array, 8, 0, 9); //Found
	}
}
