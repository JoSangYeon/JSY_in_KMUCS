#include <iostream>
#include<algorithm>
using namespace std;

int data1[10000];
int data2[10000];
int cnt_H_swap = 0, cnt_L_swap = 0,
cnt_H_comparison = 0, cnt_L_comparison = 0;

void cnt_init()
{
	cnt_H_swap = 0;
	cnt_L_swap = 0;
	cnt_H_comparison = 0;
	cnt_L_comparison = 0;
}

void swap(int &x, int &y) {
	int temp = x;
	x = y;
	y = temp;
}

int partition_H(int p, int r)
{
	int pivot = data1[p];
	int i = p - 1;
	int j = r + 1;

	while (true)
	{
		do
		{
			i++;
			cnt_H_comparison++;
		} while (data1[i] < pivot);

		do
		{
			j--;
			cnt_H_comparison++;
		} while (data1[j] > pivot);

		if (i >= j)
			{return j;}
		swap(data1[i], data1[j]);
		cnt_H_swap++;
	}
}

void quicksort_H(int p, int r)
{
	if (p < r)
	{
		int q = partition_H(p, r);
		quicksort_H(p, q);
		quicksort_H(q + 1, r);
	}
}

int partition_L(int p, int r)
{
	int pivot = data2[p];
	int j = p;
	for (int i = p + 1; i < r + 1; i++)
	{
		if (data2[i] < pivot)
		{
			cnt_L_comparison++;
			j++;
			swap(data2[i], data2[j]);
			cnt_L_swap++;
		}
		else {cnt_L_comparison++;}
	}
	int pivot_pos = j;
	swap(data2[pivot_pos], data2[p]);
	cnt_L_swap++;
	return pivot_pos;
}

void quicksort_L(int p, int r)
{
	if (p < r)
	{
		int q = partition_L(p, r);
		quicksort_L(p, q-1);
		quicksort_L(q + 1, r);
	}
}

int main(void) {
	int n, size, factor;
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> size;
		for (int j = 0; j < size; j++) {
			cin >> factor;
			data1[j] = factor;
			data2[j] = factor;
		}
		quicksort_H(0, size - 1);
		quicksort_L(0, size - 1);
		cout << cnt_H_swap << " " << cnt_L_swap << " " << cnt_H_comparison << " " << cnt_L_comparison << endl;
		cnt_init();
	}
}