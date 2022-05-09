//https://www.acmicpc.net/problem/1949
// 우수마을
//N개의 마을로 이루어진 나라가 있다.
//편의상 마을에는 1부터 N까지 번호가 붙어 있다고 하자.
//이 나라는 트리(Tree) 구조로 이루어져 있다.
//즉 마을과 마을 사이를 직접 잇는 N-1개의 길이 있으며,
//    각 길은 방향성이 없어서 A번 마을에서 B번 마을로 갈 수 있다면 B번 마을에서 A번 마을로 갈 수 있다.
//    또, 모든 마을은 연결되어 있다. 두 마을 사이에 직접 잇는 길이 있을 때, 두 마을이 인접해 있다고 한다.
//
//이 나라의 주민들에게 성취감을 높여 주기 위해,
//    다음 세 가지 조건을 만족하면서 N개의 마을 중 몇 개의 마을을 '우수 마을'로 선정하려고 한다.
//
//'우수 마을'로 선정된 마을 주민 수의 총 합을 최대로 해야 한다.
//마을 사이의 충돌을 방지하기 위해서, 만일 두 마을이 인접해 있으면 두 마을을 모두 '우수 마을'로 선정할 수는 없다.
//즉 '우수 마을'끼리는 서로 인접해 있을 수 없다.
//선정되지 못한 마을에 경각심을 불러일으키기 위해서,
//    '우수 마을'로 선정되지 못한 마을은 적어도 하나의 '우수 마을'과는 인접해 있어야 한다.
//
//각 마을 주민 수와 마을 사이의 길에 대한 정보가 주어졌을 때,
//    주어진 조건을 만족하도록 '우수 마을'을 선정하는 프로그램을 작성하시오.
//
//dp[?][0] => 일반마을
//dp[?][1] => 우수마을
//
//dp[now]가 우수마을일때 우수마을 다음에는 무조건 일반마을이 와야한다.
//dp[now]가 일반마을일때 일반 마을 다음에는 우수마을이 올 수도 있고, 일반마을이 올 수도 있다.
//따라서, 일반마을 다음에는 일반마을과 우수마을 중 큰 값을 취해아 한다.
//
//점화식
//dp[now][0] += max(dp[next][0], dp[next][1])
//dp[now][1] += dp[next][0]

#include <iostream>
#include <vector>
#include <climits>
#include <cmath>
#include <algorithm>
using namespace std;

int dp[10001][2];
int valige_num[10001];
bool visited[10001];
vector<int> graph[10001];

void calc(int now)
{
    visited[now] = true;

    for (int i=0; i<graph[now].size(); i++)
    {
        int next = graph[now][i];
        if (visited[next] == true)
            continue;
        calc(next);

        dp[now][0] += max(dp[next][0], dp[next][1]);
        dp[now][1] += dp[next][0];
    }
}


int main()
{
    int n;
    cin >> n;

    for (int i=1; i<n+1; i++)
    {
        cin >> valige_num[i];
    }

    for (int i=0; i<n-1; i++)
    {
        int a,b;
        cin >> a >> b;
        graph[a].push_back(b);
        graph[b].push_back(a);
    }
    for (int i = 1; i<n+1; i++)
    {
       dp[i][0] = 0;
       dp[i][1] = valige_num[i];
    }
    calc(1);
    cout << max(dp[1][0], dp[1][1]) << endl;
}