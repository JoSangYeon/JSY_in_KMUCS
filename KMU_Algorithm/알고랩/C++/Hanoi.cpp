#include <iostream>
#include <algorithm>
#include <string>
using namespace std;

unsigned long long k;

void hanoi(int n, int a, int b, int c)
{
	if (n > 0)
	{
		if (k < pow(2, n - 1))
		{
			hanoi(n - 1, a, c, b);// a > b
		}
		else if (k > pow(2, n - 1))
		{
			k -= (unsigned long long int)pow(2, n - 1);
			hanoi(n - 1, b, a, c); // b > c
		}
		else 
		{
			cout << a << " " << c << endl;
		}
	}
}

int main(void)
{
	int n, num_of_disks;
	cin >> n;
	for (int i = 0; i < n; i++)
	{
		cin >> num_of_disks;
		cin >> k;
		//cout << (unsigned long long int)k - (unsigned long long int)pow(2, num_of_disks-1) << endl;
		hanoi(num_of_disks, 1, 2, 3);
	}
}