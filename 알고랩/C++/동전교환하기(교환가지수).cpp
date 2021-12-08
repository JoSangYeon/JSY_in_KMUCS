#include <iostream>
#include <algorithm>
#include <string>
#include <vector>
using namespace std;

int calc(int n, int m, vector<int> v)
{
	vector<vector<int>> dp(m + 1, vector<int> (n + 1, 0));
	dp[0][0] = 1;

	for (int i = 1; i < m+1; i++)
	{
		int c = v[i - 1];
		for (int j = 0; j < n + 1; j++)
		{
			if (j < c)
			{
				dp[i][j] = dp[i - 1][j];
			}
			else
			{
				dp[i][j] = dp[i][j - c] + dp[i - 1][j];
			}
		}
	}
	return dp[m][n];
}

int main(void) {
	int t, n, m;
	cin >> t;
	for (int i = 0; i < t; i++) {
		cin >> n;
		cin >> m;
		vector<int> v;
		for (int j = 0; j < m; j++) {
			int d;
			cin >> d;
			v.push_back(d);
		}
		cout << calc(n, m, v) << endl;
	}
}