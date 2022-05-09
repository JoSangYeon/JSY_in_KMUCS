// https://programmers.co.kr/learn/courses/30/lessons/42627
// HDD 컨트롤러
#include <string>
#include <bits/stdc++.h>
#include <vector>
using namespace std;

bool cmp(pair<int, int>p1, pair<int, int> p2)
{
    if (p1.second == p2.second)
    {
        return p1.first < p2.first;
    }
    return p1.second < p2.second;
}

int solution(vector<vector<int>> jobs) {
    int total=0;
    int time = 0;
    int idx = 0;
    
    vector<pair<int, int>> v;
    
    sort(jobs.begin(), jobs.end());
    
    while(idx < jobs.size() || !v.empty())
    {
        if(idx < jobs.size() && jobs[idx][0] <= time)
        {
            v.push_back(make_pair(jobs[idx][0], jobs[idx][1]));
            idx ++;
            continue;
        }
        sort(v.begin(), v.end(), cmp);
        
        if (!v.empty())
        {
            time += v[0].second;
            total += time - v[0].first;
            v.erase(v.begin());
        }
        else
        {
            time = jobs[idx][0];
        }
    }
    return total / jobs.size();
}