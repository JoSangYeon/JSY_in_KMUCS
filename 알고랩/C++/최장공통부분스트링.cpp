#include <iostream>
#include <string>
#include <cstring>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

int DP[102][102];

int main(void)
{
	int t;
	string x, y;
	cin >> t;
	while (t-- > 0)
	{
		memset(DP, 0, sizeof(DP));
		cin >> x >> y;
		int n, m;
		m = x.size();
		n = y.size();

		for (int i = 1; i < m + 1; i++)
		{
			for (int j = 1; j < n + 1; j++)
			{
				if (x[i - 1] == y[j - 1])
				{
					DP[i][j] = DP[i - 1][j - 1] + 1;
				}
				else
				{
					DP[i][j] = max(DP[i - 1][j], DP[i][j - 1]);
				}
			}
		}
		cout << DP[m][n] << endl;
	}
}