/*
 * �̸�: ����
 * �й�: 20171706
 *
 * Program ID: File Processing Report#1
 *
 * Description: BST(Binary Search Tree) ����
 *
 * Summary:	1. Node Ŭ����: �⺻�� �Ǵ� ������ Ŭ�����̴�. �⺻������ BST�� 2���� �����͸� ������ Double Linked List�� ���¸�
 * 				��� �ֱ� ������ NodeŬ������ Ȱ���� �ʼ��̴�. �¿��� next Node���� ������ ���� left, right�̸��� Node ��� ������
 * 				�ش� ����� Key data�� �����ϴ� Key ��� ������ �ִ�. getNode�޼ҵ带 �����ڷ� �����Ͽ� key���� ���ڷ� �޾� Node�� �����Ѵ�.
 *
 *			2. BST Ŭ����: BST�� ������ Ŭ�����̴�. ���� �����ͷ� �ֻ��� Node(= root)�� ��� ������ ������ �ִ�. �⺻������ ����(insertBST)��
 *				����(deleteBST) �޼ҵ带 ���� �Ͽ���, �ش� BST�� ����ִ��� Ȯ���ϴ� isEmpty(), �׸��� ������ȸ ����� ���� inorder()��
 *				toString() �޼ҵ带 �����Ͽ���. �׹ۿ� �޼ҵ�� deleteBST()�޼ҵ� ����ÿ� ���Ǵ� �κ����� �˰���(findMax(), findMin(),
 *				getHeight(), getNumofNode())�� deleteKey()(= deleteBST���� ����Ǵ� ��͸޼ҵ�)�޼ҵ忡�� ����Ѵ�.
 */

#include <iostream>
#include <string>
#include <algorithm>
using namespace std;

class Node
{
public:
	// ������ == getNode()
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
	Node* root; // �ֻ��� Node�� ������

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
				if (p->key == key) { return; } //�ߺ��� key�� ó��

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

			//������ Node�� ������ 0�� ���
			else if (rt->left == NULL && rt->right == NULL)
			{
				rt = NULL;
			}

			//������ Node�� ������ 1�� ���
			else if (rt->left == NULL) //case 1: ���� node�� ������� ���
			{
				temp = rt; rt = rt->right; temp = NULL;
			}
			else if (rt->right == NULL)//case 2: ������ node�� ������� ���
			{
				temp = rt; rt = rt->left; temp = NULL;
			}

			//������ Node�� ������ 2�� ���
			else
			{
				string flag = "middle";

				//case 1: ������ ����� ���� subTree�� �� ������
				if (this->getHeight(rt->left) > this->getHeight(rt->right))
				{
					temp = this->findMax(rt->left); flag = "left";
				}

				//case 2: ������ ����� ������ subTree�� �� ������
				else if (this->getHeight(rt->left) < this->getHeight(rt->right))
				{
					temp = this->findMin(rt->right); flag = "right";
				}

				//case 3: ������ subTree�� ���̰� ������
				else
				{
					// case 3-1: ���� subTree�� node ������ �� ������
					if (this->getNumofNode(rt->left) >= this->getNumofNode(rt->right))
					{
						temp = this->findMax(rt->left); flag = "left";
					}

					//case 3-2: ������ subTree�� node ������ �� ������
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

	//���� ��ȸ �˰���
	void inorder(Node* temp)
	{
		if (temp != NULL)
		{
			inorder(temp->left);
			cout << temp->key << " ";
			inorder(temp->right);
		}
	}

	// �ش� Node ���̸� ���ϴ� �޼ҵ�
	int getHeight(Node* temp)
	{
		if (temp == NULL) { return -1; }

		int leftSubTreeHeight = getHeight(temp->left) + 1;
		int rightSubTreeHegiht = getHeight(temp->right) + 1;

		return max(leftSubTreeHeight, rightSubTreeHegiht);
	}

	//�ش� Node�� ���� Node���� ������ count�ϴ� �޼ҵ�
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
	//20171706_����_ȭ��ó��_Report1: BST ����

	BST bst;
	int data[] = { 25, 500, 33, 49, 17, 403, 29, 105, 39, 66,
				305, 44, 19, 441, 390, 12, 81, 50, 100, 999 };

	cout << "������ ����" << endl;
	for (int i = 0; i < 20; i++)
	{
		bst.insertBST(data[i]);
		bst.print();
	}

	cout << "\n������ ����" << endl;
	for (int i = 0; i < 20; i++)
	{
		bst.deleteBST(data[i]);
		bst.print();
	}

	cout << "\nBST �ʱ�ȭ" << endl;
	bst.root = NULL;

	cout << "\n������ ����" << endl;
	for (int i = 0; i < 20; i++)
	{
		bst.insertBST(data[i]);
		bst.print();
	}

	cout << "\n������ ���� ����" << endl;
	for (int i = 19; i >= 0; i--)
	{
		bst.deleteBST(data[i]);
		bst.print();
	}
}