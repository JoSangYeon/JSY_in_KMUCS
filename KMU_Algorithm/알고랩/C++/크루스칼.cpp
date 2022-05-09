//https://yjg-lab.tistory.com/177

#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;


int getGroup(int mst[], int x)
{
	if (mst[x] == x)
		return x;
	else
		return mst[x] = getGroup(mst, mst[x]);
}

void unionGroup(int mst[], int a, int b)
{
	a = getGroup(mst, a);
	b = getGroup(mst, b);
	if (a < b)
		mst[b] = a;
	else
		mst[a] = b;
}

int find(int mst[], int a, int b)
{
	a = getGroup(mst, a);
	b = getGroup(mst, b);
	if (a == b) 
		return 1;
	else 
		return 0;
}

class Edge
{
public:
	int vertex[2];
	int dist;
	Edge(int v1, int v2, int dista)
	{
		this->vertex[0] = v1;
		this->vertex[1] = v2;
		this->dist = dista;
	}
	bool operator <(Edge& edge)
	{
		return this->dist < edge.dist;
	}
};

int mst[1001];

int main(void)
{
	int t;
	cin >> t;
	while (t-- > 0)
	{
		vector<Edge> graph;
		int n, k, m, v, w;
		cin >> n;
		for (int i = 0; i < n; i++)
		{
			cin >> k >> m;
			for (int j = 0; j < m; j++)
			{
				cin >> v >> w;
				graph.push_back(Edge(k, v, w));
			}
		}
		sort(graph.begin(), graph.end());

		for (int i = 1; i < n + 1; i++)
		{
			mst[i] = i;
		}

		int sum = 0;
		for (int i = 0; i < graph.size(); i++)
		{
			if (!find(mst, graph[i].vertex[0], graph[i].vertex[1]))
			{
				sum += graph[i].dist;
				unionGroup(mst, graph[i].vertex[0], graph[i].vertex[1]);
			}
		}
		cout << sum << endl;
	}
}