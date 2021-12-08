#include <iostream>
#include <algorithm>
#include <string>
#include <vector>
using namespace std;

int calc(int n, int m, vector<int> v)
{
	vector<int> dp;

	for (int i = 0; i < n + 1; i++)
	{
		dp.push_back(99999999);
	}
	dp[0] = 0;

	for (int i = 0; i < m; i++)
	{
		for (int j = v[i]; j < n + 1; j++)
		{
			dp[j] = min(dp[j], dp[j - v[i]] + 1);
		}
	}
	return dp[n];
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