#include <iostream>
#include <string>
#include <cstring>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

int table[3][1001] = { 0 };

int getEdge(int size)
{
	int cnt = 0;
	for (int i = 0; i < 3; i++)
	{
		for (int j = 0; j < size + 1; j++)
		{
			if (table[i][j] != 0)
				cnt++;
		}
	}
	return cnt;
}

void display(int size)
{
	int cnt = 0;
	for (int i = 0; i < 3; i++)
	{
		for (int j = 0; j < size + 1; j++)
		{
			cout << table[i][j] << " ";
		}
		cout << endl;
	}
}

int wordToInt(char ch)
{
	if (ch == 'A')
	{
		return 0;
	} else if (ch == 'B')
	{
		return 1;
	} else if (ch == 'C')
	{
		return 2;
	} else
	{
		return 3;
	}
}

int makeTable(string pattern)
{	int patLength = pattern.length();

	table[wordToInt(pattern[0])][0] = 1;

	int x = 0;
	for (int j = 1; j < patLength; j++)
	{
		for (int c = 0; c < 3; c++)
		{
			table[c][j] = table[c][x];
		}
		table[wordToInt(pattern[j])][j] = j + 1;
		x = table[wordToInt(pattern[j])][x];
	}
	for (int c = 0; c < 3; c++)
	{
		table[c][patLength] = table[c][x];
	}
	//display(patLength);
	return getEdge(patLength);
}

int DFA(string pattern, string text)
{
	int i, j, patLength ,txtLength;
	int cnt = 0;

	patLength = pattern.length();
	txtLength = text.length();

	for (i = 0, j = 0; i < txtLength; i++)
	{
		j = table[wordToInt(text[i])][j];
		if (j == patLength)
		{
			cnt++;
		}
	}
	return cnt;
}

int main(void)
{
	int t;
	string pattern, text;
	cin >> t;
	while (t-- > 0)
	{
		cin >> pattern >> text;

		memset(table, 0, sizeof(table));
		int edge = makeTable(pattern);
		int cnt = DFA(pattern, text);

		cout << edge << " " << cnt << endl;
	}
}