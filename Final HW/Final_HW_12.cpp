// https://www.acmicpc.net/problem/23248
// 트레쉬 헌터
#include <iostream>
#include <cmath>
#include <vector>
#include <algorithm>
using namespace std;

int main()
{
    int n,m,k,q,p;
    cin >> n >> m >> k;
    int ans = 0;

    list<pair<int, int>> a;

    for(int i=0; i<k; i++)
    {
        cin >> p >> q;
        a.push_back({p, q});
    }
    a.sort();

    pair<int, int> cur;
    while(a.size())
    {
        cur = *a.begin();
        a.erase(a.begin());
        auto it = a.begin();
        while(it != a.end())
        {
            if (cur.first <= it->first && cur.second <= it->second)
            {
                cur *it;
                a.erase(it++);
            }
            else it++;
        }
        ans++;
    }
    cout << ans << endl;
    return 0;
}

#include <bits/stdc++.h>

using namespace std;
using namespace __gnu_cxx;
using ll = long long;

int m, n, k;
set<pair<int, int>> st;
int ans;

void solve()
{
  cin >> m >> n >> k;

  for (int i = 0; i < k; i++)
  {
    int a, b;
    cin >> a >> b;
    st.insert({a, b});
  }

  while (st.size())
  {
    ++ans;
    auto x = *st.begin();
    st.erase(st.begin());

    auto it = st.begin();
    while (it != st.end())
    {
      if (x.first <= it->first && x.second <= it->second)
      {
        x = *it;
        st.erase(it++);
      }
      else
      {
        ++it;
      }
    }
  }

  cout << ans << endl;
}

int main()
{
  cin.tie(0);
  ios::sync_with_stdio(false);

  solve();
}
