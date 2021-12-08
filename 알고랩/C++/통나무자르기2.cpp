#include <iostream>
#include <vector>
#include <climits>
using namespace std;

int calc(int s, int f, vector<int>& lk, vector< vector<int> >& m)
{
	if (m[s][f] != INT_MAX) { return m[s][f]; }
	
	int cnt = 0;
	for (int i = 0; i < lk.size(); i++)
	{
		if (lk[i] > s && lk[i] < f)
		{
			cnt++;
		}
	}
	if (cnt == 0) { return cnt; }
	else
	{
		int q = INT_MAX;
		for (int i = 0; i < lk.size(); i++)
		{
			if (lk[i] > s && lk[i] < f)
			{
				int tmp = calc(s, lk[i], lk, m) + calc(lk[i], f, lk, m) + (f - s);
				if (tmp < q) { q = tmp; }
			}
		}
		m[s][f] = q;
		return q;
	}
}

int main(void)
{
	int t;
	cin >> t;
	for(int c=0; c<t; c++)
	{
		int l, k;
		vector<int> lk;
		cin >> l >> k;
		for (int i = 0; i < k; i++) 
		{
			int tmp;
			cin >> tmp;
			lk.push_back(tmp);
		}
		vector< vector <int> > m(l + 1, vector<int>(l + 1, INT_MAX));

		int result = calc(0, l, lk, m);
		cout << result << endl;
	}
}