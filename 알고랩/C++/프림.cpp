// https://blackon29.tistory.com/44
// https://engkimbs.tistory.com/372

#include <iostream>
#include <cstring>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

vector<vector<pair<int, int>>> edge;
priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pqueue; 
bool visited[1001];
int sum = 0;

void prim(int v)
{
	visited[v] = true;

	for (auto a : edge[v])
	{
		if (!visited[a.second])
		{
			pqueue.push({ a.first, a.second });
		}
	}
	while (!pqueue.empty())
	{
		pair<int, int> W = pqueue.top();
		pqueue.pop();
		if (!visited[W.second])
		{
			sum += W.first;
			prim(W.second);
			return;
		}
	}
}

int main(void)
{
	int t;
	cin >> t;
	while (t-- > 0)
	{
		int n, k, m, v, w;
		cin >> n;
		sum = 0;
		edge.clear();
		edge.resize(n + 1);
		memset(visited, false, sizeof(visited));
		for (int i = 0; i < n; i++)
		{
			cin >> k >> m;
			for (int j = 0; j < m; j++)
			{
				cin >> v >> w;
				edge[k].push_back(make_pair(w, v));
			}
		}

		prim(1);

		cout << sum << endl;
	}
}