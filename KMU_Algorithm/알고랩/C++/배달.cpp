#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int INF = 2100000000;
vector<int> dist;

void calc(int N, vector<vector<int> > road)
{
	dist.resize(N + 1, INF);
	dist[1] = 0;
	priority_queue<pair<int, int>> Q;
	Q.push(make_pair(0, 1));

	while (!Q.empty())
	{
		int thereCost, there;
		int hereCost = -Q.top().first;
		int here = Q.top().second;
		Q.pop();

		for (int i = 0; i < road.size(); i++)
		{
			if (here == road[i][0])
			{
				thereCost = road[i][2] + hereCost;
				there = road[i][1];

				if (dist[there] > thereCost)
				{
					dist[there] = thereCost;
					Q.push(make_pair(-dist[there], there));
				}
			}
			else if (here == road[i][1])
			{
				thereCost = road[i][2] + hereCost;
				there = road[i][0];

				if (dist[there] > thereCost)
				{
					dist[there] = thereCost;
					Q.push(make_pair(-dist[there], there));
				}
			}
		}
	}
}

int solution(int N, vector<vector<int> > road, int K) {
	int answer = 0;
	
	calc(N, road);

	for (int i = 1; i < dist.size(); i++)
	{
		if (dist[i] <= K)
			answer++;
	}

	return answer;
}

int main(void)
{

}