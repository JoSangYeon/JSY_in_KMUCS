#include <vector>
#include <climits>
#include <iostream>

using namespace std;

int main(void) {
	int n;
	vector<int> v;

	cin >> n;
	for (int i = 0; i < n + 1; i++) {
		int d;
		cin >> d;
		v.push_back(d);
	}

	n = v.size() - 1;
	vector<vector<long long>> m(n+1, vector <long long>(n+1, 0));

	for (int l = 2; l < n + 1; l++)
	{
		for (int i = 1; i < n - l + 2; i++)
		{
			int j = i + l - 1;
			m[i][j] = numeric_limits<long long>::max();
			for (int k = i; k < j; k++)
			{
				long long q = m[i][k] + m[k + 1][j] + (v[i - 1] * v[k] * v[j]);
				if (q < m[i][j])
				{
					m[i][j] = q;
				}
			}
		}
	}
	cout << m[1][n];
	
	return 0;
}