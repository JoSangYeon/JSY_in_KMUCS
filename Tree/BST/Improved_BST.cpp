/*
 * 이름: 조상연
 * 학번: 20171706
 *
 * Program ID: File Processing Report#1
 *
 * Description: BST(Binary Search Tree) 구현
 *
 * Summary:	1. Node 클래스: 기본이 되는 최하위 클래스이다. 기본적으로 BST는 2개의 포인터를 가지는 Double Linked List의 형태를
 * 				띄고 있기 때문에 Node클래스의 활용은 필수이다. 좌우의 next Node와의 연결을 위해 left, right이름의 Node 멤버 변수와
 * 				해당 노드의 Key data를 저장하는 Key 멤버 변수가 있다. getNode메소드를 생성자로 구현하여 key값을 인자로 받아 Node를 생성한다.
 *
 *			2. BST 클래스: BST를 구현한 클래스이다. 고정 포인터로 최상위 Node(= root)를 멤버 변수를 가지고 있다. 기본적으로 삽입(insertBST)과
 *				삭제(deleteBST) 메소드를 구현 하였고, 해당 BST가 비어있는지 확인하는 isEmpty(), 그리고 중위순회 출력을 위한 inorder()와
 *				toString() 메소드를 구현하였다. 그밖에 메소드는 deleteBST()메소드 수행시에 사용되는 부분적인 알고리즘(findMax(), findMin(),
 *				getHeight(), getNumofNode())을 deleteKey()(= deleteBST에서 수행되는 재귀메소드)메소드에서 사용한다.
 */

#include <iostream>
#include <string>
#include <algorithm>
using namespace std;

class Node
{
public:
	// 생성자 == getNode()
	Node(int key)
	{
		this->key = key;
		this->left = NULL;
		this->right = NULL;
	}

private:
	int key;
	Node* left;
	Node* right;

	friend class BST;
};

Node* getNode(int key)
{
	return new Node(key);
}

class BST
{
public:
	Node* root; // 최상위 Node의 포인터

	BST() { this->root = NULL; }

	bool isEmpty() { return root == NULL; }

	void insertBST(int key)
	{
		if (this->isEmpty()) { root = getNode(key); }
		else
		{
			Node* p = root; Node* q = NULL;

			while (p != NULL)
			{
				if (p->key == key) { return; } //중복된 key값 처리

				q = p;
				if (p->key > key) { p = p->left; }
				else { p = p->right; } //(p.key < key)
			}
			if (q->key > key) { q->left = getNode(key); }
			else { q->right = getNode(key); } //(q.key < key)
		}
	}

	void deleteBST(int key) { root = this->deleteKey(this->root, key); }

	void print()
	{
		if (this->isEmpty()) { cout << "Tree is Empty\n"; }
		else { inorder(this->root); cout << "\n"; }
	}

private:
	Node* deleteKey(Node* t, int key)
	{
		Node* rt = t;
		Node* temp = rt;

		if (rt != NULL)
		{
			if (key < rt->key) { rt->left = deleteKey(rt->left, key); }
			else if (key > rt->key) { rt->right = deleteKey(rt->right, key); }

			//삭제할 Node의 차수가 0일 경우
			else if (rt->left == NULL && rt->right == NULL)
			{
				rt = NULL;
			}

			//삭제할 Node의 차수가 1일 경우
			else if (rt->left == NULL) //case 1: 왼쪽 node가 비어있을 경우
			{
				temp = rt; rt = rt->right; temp = NULL;
			}
			else if (rt->right == NULL)//case 2: 오른쪽 node가 비어있을 경우
			{
				temp = rt; rt = rt->left; temp = NULL;
			}

			//삭제할 Node의 차수가 2일 경우
			else
			{
				string flag = "middle";

				//case 1: 삭제할 노드의 왼쪽 subTree가 더 높을때
				if (this->getHeight(rt->left) > this->getHeight(rt->right))
				{
					temp = this->findMax(rt->left); flag = "left";
				}

				//case 2: 삭제할 노드의 오른쪽 subTree가 더 높을때
				else if (this->getHeight(rt->left) < this->getHeight(rt->right))
				{
					temp = this->findMin(rt->right); flag = "right";
				}

				//case 3: 양쪽의 subTree의 높이가 같을때
				else
				{
					// case 3-1: 왼쪽 subTree의 node 개수가 더 많을때
					if (this->getNumofNode(rt->left) >= this->getNumofNode(rt->right))
					{
						temp = this->findMax(rt->left); flag = "left";
					}

					//case 3-2: 오른쪽 subTree의 node 개수가 더 많을때
					else //(this.getNumofNode(p.left) < this.getNumofNode(p.right))
					{
						temp = this->findMin(rt->right); flag = "right";
					}
				}

				rt->key = temp->key;

				if (flag == "left") { rt->left = this->deleteKey(rt->left, rt->key); }
				else { rt->right = deleteKey(rt->right, rt->key); }

			}
		}
		else { cout << "Not Found" << endl; }

		return rt;
	}

	//중위 순회 알고리즘
	void inorder(Node* temp)
	{
		if (temp != NULL)
		{
			inorder(temp->left);
			cout << temp->key << " ";
			inorder(temp->right);
		}
	}

	// 해당 Node 높이를 구하는 메소드
	int getHeight(Node* temp)
	{
		if (temp == NULL) { return -1; }

		int leftSubTreeHeight = getHeight(temp->left) + 1;
		int rightSubTreeHegiht = getHeight(temp->right) + 1;

		return max(leftSubTreeHeight, rightSubTreeHegiht);
	}

	//해당 Node의 하위 Node들의 개수를 count하는 메소드
	int getNumofNode(Node* temp)
	{
		if (temp == NULL) { return -1; }

		int leftSubTreeHeight = getHeight(temp->left) + 1;
		int rightSubTreeHegiht = getHeight(temp->right) + 1;

		return leftSubTreeHeight + rightSubTreeHegiht + 1;
	}

	Node* findMin(Node* temp)
	{
		if (temp->left == NULL) { return temp; }
		else { return findMin(temp->left); }
	}

	Node* findMax(Node* temp)
	{
		if (temp->right == NULL) { return temp; }
		else { return findMax(temp->right); }
	}
};

int main()
{
	//20171706_조상연_화일처리_Report1: BST 구현

	BST bst;
	int data[] = { 25, 500, 33, 49, 17, 403, 29, 105, 39, 66,
				305, 44, 19, 441, 390, 12, 81, 50, 100, 999 };

	cout << "데이터 삽입" << endl;
	for (int i = 0; i < 20; i++)
	{
		bst.insertBST(data[i]);
		bst.print();
	}

	cout << "\n데이터 삭제" << endl;
	for (int i = 0; i < 20; i++)
	{
		bst.deleteBST(data[i]);
		bst.print();
	}

	cout << "\nBST 초기화" << endl;
	bst.root = NULL;

	cout << "\n데이터 삽입" << endl;
	for (int i = 0; i < 20; i++)
	{
		bst.insertBST(data[i]);
		bst.print();
	}

	cout << "\n데이터 역순 삭제" << endl;
	for (int i = 19; i >= 0; i--)
	{
		bst.deleteBST(data[i]);
		bst.print();
	}
}