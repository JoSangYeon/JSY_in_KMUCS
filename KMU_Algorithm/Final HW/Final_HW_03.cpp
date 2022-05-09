#include <iostream>
#include <vector>
#include <climits>
#include <cmath>
#include <algorithm>
using namespace std;

int main()
{
	int n;
	cin >> n;
	vector<int> dp(n + 1);
	dp[2] = 1;
	if (n >= 3) dp[3] = 1;

	for (int i = 4; i < n + 1; i++)
	{
		int temp = INT_MAX;
		if (i % 3 == 0) temp = dp[i / 3];
		if (i % 2 == 0) temp = min(temp, dp[i / 2]);
		dp[i] = min(temp, dp[i - 1]) + 1;
	}

	cout << dp[n] << endl;
	return 0;
}