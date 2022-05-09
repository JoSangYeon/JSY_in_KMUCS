#include <iostream>
#include <string>
#include <cstring>
#include <climits>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

int DP[102][102];

int matrix_chain_order(int n, vector<int> v)
{
	for (int l = 2; l < n+1; l++)
	{
		for (int i = 1; i < n - l + 2; i++)
		{
			int j = i + l - 1;
			DP[i][j] = INT_MAX;
			for (int k = i; k < j; k++)
			{
				int q = DP[i][k] + DP[k + 1][j] + (v[i - 1] * v[k] * v[j]);
				if (q < DP[i][j])
					DP[i][j] = q;
			}
		}
	}
	return DP[1][n];
}

int main(void)
{
	int t;
	cin >> t;
	while (t-- > 0)
	{
		memset(DP, 0, sizeof(DP));

		int n, d;
		vector<int> v;
		cin >> n;
		for (int i = 0; i < n + 1; i++)
		{
			cin >> d;
			v.push_back(d);
		}
		cout << matrix_chain_order(n, v) << endl;
	}
}