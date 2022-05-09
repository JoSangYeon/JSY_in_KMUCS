#include <iostream>
#include <string>
#include <cstring>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

void display(vector<int> f)
{
	for (int i = 0; i < f.size(); i++)
		cout << f[i] << " ";
	cout << endl;
}

vector<int> getFail(string pattern)
{
	int m = pattern.length();
	int j = 0;
	vector<int> fail(m, 0);
	for (int i = 1; i < m; i++)
	{
		while(j > 0 && pattern[i] != pattern[j])
		{
			j = fail[j - 1];
		}
		if (pattern[i] == pattern[j])
		{
			fail[i] = ++j;
		}
		else
		{
			fail[i] = j;
		}
	}
	return fail;
}

int kmp(string text, string pattern)
{
	vector<int> ans;
	vector<int>fail = getFail(pattern);
	int n = text.length();
	int m = pattern.size();
	int j = 0;

	display(fail);
	for (int i = 0; i < n; i++)
	{
		while (j > 0 && text[i] != pattern[j])
		{
			j = fail[j - 1];
		}
		if (text[i] == pattern[j])
		{
			if (j == m - 1)
			{
				ans.push_back(i - j);
				j = fail[j];
			}
			else
			{
				j++;
			}
		}
	}
	return ans.size();
}


int main(void)
{
	int t;
	string pattern, text;
	cin >> t;
	while (t-- > 0)
	{
		cin >> pattern >> text;

		cout << kmp(text, pattern) << endl;
	}
}