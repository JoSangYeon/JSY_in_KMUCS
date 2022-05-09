#include <iostream>
#include <string>
#include <cstring>
#include <climits>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

int MAX = 2000000000;

int get_there(int n, vector<int> &dist, vector<bool> &visited)
{
	int min_ = MAX + 1;
	int index = 1;
	for (int i = 1; i < n + 1; i++)
	{
		if (dist[i] < min_ && !visited[i])
		{
			min_ = dist[i];
			index = i;
		}
	}
	return index;
}

int main(void)
{
	int t;
	cin >> t;
	while (t-- > 0)
	{
		int n, k, m, v, w;
		cin >> n;
		vector < vector<int>> graph(n + 1, vector<int>(n + 1));
		vector<int> dist(n + 1);
		vector<int> sum_arr(n + 1);
		vector<bool> visited(n + 1);

		// graph 초기화 //
		// 자기자신에 대한 weight = 0, 그외는 일단 INF //
		for (int i = 1; i < n + 1; i++)
		{
			for (int j = 1; j < n + 1; j++)
			{
				graph[i][j] = i == j ? 0 : MAX;
			}
		}

		// edge에 대한 정보를 받아옴 //
		for (int i = 0; i < n; i++)
		{
			cin >> k >> m;
			for (int j = 0; j < m; j++)
			{
				cin >> v >> w;
				graph[k][v] = w;
			}
		}

		// 모든 Node에 대한 가중치 값 초기화 //
		for (int i = 1; i < n + 1; i++)
		{
			dist[i] = graph[1][i];
			if (dist[i] < MAX)
				sum_arr[i] = dist[i];
		}

		visited[1] = true;
		for (int i = 0; i < n - 2; i++)
		{
			int there = get_there(n, dist, visited);
			visited[there] = true;
			for (int p = 1; p < n + 1; p++)
			{
				if (!visited[p])
				{
					if (dist[p] > dist[there] + graph[there][p])
					{
						dist[p] = dist[there] + graph[there][p];
						sum_arr[p] = graph[there][p];
					}
				}
			}
		}
		int sum = 0;
		for (int i = 1; i < n + 1; i++)
			sum += sum_arr[i];

		cout << sum << endl;
	}
}