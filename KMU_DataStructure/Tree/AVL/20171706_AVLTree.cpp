/**
 * File processing, 2020
 * AVL.cpp
 * implementation of AVL tree
 * 20171706 ����
 */

#include <stdio.h>
#include <string>
#include <algorithm>
using namespace std;

 /**
  * Node represents a single node in AVL tree.
  */
typedef struct Node {
	int key, bf;
	struct Node *left, *right;
} Node;

typedef Node *Tree;
Node *unbalNode = nullptr;	//������ ���� Node�� �����ϴ� ����
Node *P = nullptr;			// ������ ���� Node�� �θ� Node�� �����ϴ� ����

/**
 * getNode returns a new node.
 * @return a new node
 */
Node *getNode(int newKey) {
	Node* temp = new Node();
	temp->bf = 0; temp->key = newKey;
	temp->left = temp->right = NULL;
	return temp;
}

// �ش� Node ���̸� ���ϴ� �Լ�
int getHeight(Node *temp) {
	if (temp == nullptr) { return 0; }

	int leftSubTreeHeight = getHeight(temp->left);
	int rightSubTreeHegiht = getHeight(temp->right);

	return max(leftSubTreeHeight, rightSubTreeHegiht) + 1;
}

//�ش� Node�� ���� Node���� ������ count�ϴ� �Լ�
int getNumofNode(Node *temp) {
	if (temp == nullptr) { return 0; }

	int leftSubTreeHeight = getHeight(temp->left);
	int rightSubTreeHegiht = getHeight(temp->right);

	return leftSubTreeHeight + rightSubTreeHegiht + 1;
}

//���� ���ڸ� ����ϴ� �Լ�
int getBF(Node *temp)
{
	if (temp == nullptr) { return 0; }
	return getHeight(temp->left) - getHeight(temp->right);
}

// ���� subTree���� ���� ū key���� ���� Node�� ��ȯ�ϴ� �Լ�
Node *findMin(Node *temp) {
	if (temp->left == nullptr) { return temp; }
	else { return findMin(temp->left); }
}

// ������ subTree���� ���� ���� key���� ���� Node�� ��ȯ�ϴ� �Լ�
Node *findMax(Node *temp) {
	if (temp->right == nullptr) { return temp; }
	else { return findMax(temp->right); }
}

/**
 * updateBF updates balancing factors of nodes in T
 * and finds the unbalanced node nearest to y.
 * @param T: an AVL tree
 * @param y: the inserted/deleted node
 * @param x: the unbalanced node nearest to y
 * @param P: parent node of x
 * root���� �ش� Node(== y)���� bf�� �����.
 * x�� p�� ���� unbalNode�� P�� �ּҰ��� ������
 * bf�� ����ϸ鼭 ������ ���� ���� �� ����� �θ� ������
 * �ѹ��� �����ϴ� �Լ�
 */
void updateBF(Tree *T, Node *y, Node **x, Node **p)
{
	Node *temp = *T;
	Node *q = nullptr;

	int key = y->key;

	while (temp != nullptr)
	{
		temp->bf = getBF(temp);
		if (temp->bf > 1 || temp->bf < -1) { *p = q; *x = temp; }

		if (temp == y) { break; }
		q = temp;

		if (key < temp->key) { temp = temp->left; }
		else { temp = temp->right; }
	}
}

// updateBF �ߺ����� ��
// deleteKey�� �޾Ƽ� ������ Node���� root������ ����� bf�� ���
void updateBF(Tree *T, int deleteKey, Node **x, Node **p)
{
	Node *temp = *T;
	Node *q = nullptr;

	int key = deleteKey;

	while (temp != nullptr)
	{
		temp->bf = getBF(temp);
		if (temp->bf > 1 || temp->bf < -1) { *p = q; *x = temp; }

		if (temp->left == nullptr && temp->right == nullptr) { break; }
		q = temp;

		if (key < temp->key) { temp = temp->left; }
		else { temp = temp->right; }
	}
}

/**
 * rotateLL implements LL rotation in subtree rooted with x.
 * @param T: an AVL tree
 * @param x: root node of subtree
 * @param P: parent node of x
 */
void rotateLL(Tree *T, Node *x, Node *p)
{
	bool check = true;
	if (p == nullptr)
	{	// Node x�� root�� ���
		// Node sub�� root�� ��
		Node *sub = x->left;
		x->left = sub->right;
		sub->right = x;
		*T = sub;
		return;
	}
	if (p->right == x) { check = false; }

	Node *sub = x->left;
	x->left = sub->right;
	sub->right = x;

	check ? p->left = sub : p->right = sub;
}

/**
 * rotateRR implements RR rotation in subtree rooted with x.
 * @param T: an AVL tree
 * @param x: root node of subtree
 * @param P: parent node of x
 */
void rotateRR(Tree *T, Node *x, Node *p)
{
	bool check = true;
	if (p == nullptr)
	{	// Node x�� root�� ���
		// Node sub�� root�� ��
		Node *sub = x->right;
		x->right = sub->left;
		sub->left = x;
		*T = sub;
		return;
	}
	if (p->right == x) { check = false; }

	Node *sub = x->right;
	x->right = sub->left;
	sub->left = x;

	check ? p->left = sub : p->right = sub;
}

/**
 * rotateLR implements LR rotation in subtree rooted with x.
 * @param T: an AVL tree
 * @param x: root node of subtree
 * @param P: parent node of x
 */
void rotateLR(Tree *T, Node *x, Node *p)
{
	Node *sub = x->left;
	bool check = true;

	if (p == nullptr)
	{	// Node x�� root�� ���
		// Node q�� root�� ��
		Node* q = x->left->right;
		rotateRR(T, sub, x);
		rotateLL(T, x, p);
		*T = q;
		return;
	}
	if (p->right == x) { check = false; }

	rotateRR(T, sub, x);
	rotateLL(T, x, p);
}

/**
 * rotateRL implements RL rotation in subtree rooted with x.
 * @param T: an AVL tree
 * @param x: root node of subtree
 * @param P: parent node of x
 */
void rotateRL(Tree *T, Node *x, Node *p)
{
	Node *sub = x->right;
	bool check = true;
	if (p == nullptr)
	{	// Node x�� root�� ���
		// Node q�� root�� ��
		Node* q = x->right->left;
		rotateLL(T, sub, x);
		rotateRR(T, x, p);
		*T = q;
		return;
	}
	if (p->right == x) { check = false; }

	rotateLL(T, sub, x);
	rotateRR(T, x, p);
}

/**
 * insertBST inserts newKey into T
 * and returns the inserted node.
 * @param T: a binary search tree
 * @param newKey: a key to insert
 * @return the inserted node
 */
Node *insertBST(Tree *T, int newKey) {
	Node *p = *T;
	Node *q = nullptr;

	while (p != nullptr) {
		if (p->key == newKey) { return nullptr; } // �ߺ��Է�
		else {
			q = p;
			if (newKey > p->key) { p = p->right; }
			else { p = p->left; }
		}
	}
	Node *temp = getNode(newKey);
	if (*T == nullptr) { *T = temp; }
	else if (newKey > q->key) { q->right = temp; }
	else { q->left = temp; }

	return temp;
}

/**
 * insertAVL inserts newKey into T.
 * @param T: an AVL tree
 * @param newKey: a key to insert
 */
void insertAVL(Tree *T, int newKey) {
	/**
	 * print "NO" if not rotates
	 * print "LL" if rotates LL
	 * print "RR" if rotates RR
	 * print "LR" if rotates LR
	 * print "RL" if rotates RL
	 */
	unbalNode = nullptr;
	P = nullptr;
	Node* temp = insertBST(T, newKey);
	updateBF(T, temp, &unbalNode, &P);

	if (unbalNode == nullptr) { printf("No"); return; }

	if (unbalNode->bf > 1)
	{
		if (getBF(unbalNode->left) >= 0)
		{
			rotateLL(T, unbalNode, P);
			printf("LL");
			return; 
		}
		else
		{ 
			rotateLR(T, unbalNode, P);
			printf("LR"); 
			return;
		}
	}
	else if (unbalNode->bf < -1)
	{
		if (getBF(unbalNode->right) <= 0) 
		{ 
			rotateRR(T, unbalNode, P);
			printf("RR"); 
			return; 
		}
		else 
		{ 
			rotateRL(T, unbalNode, P);
			printf("RL"); 
			return; 
		}
	}
	else { printf("No");}
}

/**
 * deleteBST deletes deleteKey from T
 * and returns the parent node of the deleted node.
 * @param T: a binary search tree
 * @param deleteKey: a key to delete
 * @return the parent node of the deleted node
 */
Node *deleteBST(Tree *T, int deleteKey)
{
	Node *p, *q, *r;

	p = *T;
	q = nullptr;
	while (p != nullptr)
	{
		if (p->key == deleteKey) { break; }

		q = p;
		if (p->key < deleteKey) { p = p->right; }
		else { p = p->left; }
	}

	if (p == nullptr) { return q; }
	if (p->left == nullptr && p->right == nullptr)
	{
		if (q == nullptr) *T = nullptr;
		else if (q->left == p) q->left = nullptr;
		else q->right = nullptr;

		delete p;
		return q;
	}
	else if (p->left == nullptr || p->right == nullptr)
	{
		if (q == nullptr) {
			if (p->left != nullptr) *T = p->left;
			else *T = p->right;
		}
		else if (p->left != nullptr) {
			if (q->left == p) q->left = p->left;
			else q->right = p->left;
		}
		else {
			if (q->left == p) q->left = p->right;
			else q->right = p->right;
		}
		delete p;
		return q;
	}
	else
	{
		string flag = "middle";
		if (getHeight(p->left) > getHeight(p->right)) {
			r = findMax(p->left);
			flag = "left";
		}
		else if (getHeight(p->left) < getHeight(p->right)) {
			r = findMin(p->right);
			flag = "right";
		}
		else {
			if (getNumofNode(p->left) >= getNumofNode(p->right)) {
				r = findMax(p->left);
				flag = "left";
			}
			else {
				r = findMin(p->right);
				flag = "right";
			}
		}
		p->key = r->key;

		if (flag == "left") { deleteBST(&(p->left), r->key); }
		else { deleteBST(&(p->right), r->key); }
		return q;
	}
}


/**
 * deleteAVL deletes deleteKey from T.
 * @param T: an AVL tree
 * @param deleteKey: a key to delete
 */
void deleteAVL(Tree *T, int deleteKey) {
	/**
	 * print "NO" if not rotates
	 * print "LL" if rotates LL
	 * print "RR" if rotates RR
	 * print "LR" if rotates LR
	 * print "RL" if rotates RL
	 */
	unbalNode = nullptr;
	P = nullptr;

	Node* temp = deleteBST(T, deleteKey);
	updateBF(T, deleteKey, &unbalNode, &P);

	if (unbalNode == nullptr) { printf("No"); return; }

	if (unbalNode->bf > 1)
	{
		if (getBF(unbalNode->left) >= 0)
		{
			rotateLL(T, unbalNode, P);
			printf("LL");
			return;
		}
		else
		{
			rotateLR(T, unbalNode, P);
			printf("LR");
			return;
		}
	}
	else if (unbalNode->bf < -1)
	{
		if (getBF(unbalNode->right) <= 0)
		{
			rotateRR(T, unbalNode, P);
			printf("RR");
			return;
		}
		else
		{
			rotateRL(T, unbalNode, P);
			printf("RL");
			return;
		}
	}
	else { printf("No"); }
}

/**
 * inorderAVL implements inorder traversal in T.
 * @param T: an AVL tree
 */
void inorderAVL(Tree T) {
	if (T != nullptr) {
		inorderAVL(T->left);
		printf("%d ", T->key);
		inorderAVL(T->right);
	}
}


int main() {
	/* Do not modify the code below */

	const int testcase[] = { 40, 11, 77, 33, 20, 90, 99, 70, 88, 80, 66, 10, 22, 30, 44, 55, 50, 60, 25, 49 };

	Tree T = NULL;

	// insertion
	for (int i = 0; i < 20; i++)
	{ 
		printf("%d ", testcase[i]);
		insertAVL(&T, testcase[i]); 
		printf(" : ");
		inorderAVL(T); printf("\n"); 
	}

	
	// deletion
	for (int i = 0; i < 20; i++)
	{ 
		printf("%d ", testcase[i]);
		deleteAVL(&T, testcase[i]);
		printf(" : "); inorderAVL(T);
		printf("\n"); 
	}

	T = NULL;

	// reinsertion
	for (int i = 0; i < 20; i++)
	{ 
		printf("%d ", testcase[i]); 
		insertAVL(&T, testcase[i]); 
		printf(" : "); 
		inorderAVL(T); 
		printf("\n"); 
	}

	// redeletion
	for (int i = 19; 0 <= i; i--)
	{ 
		printf("%d ", testcase[i]);
		deleteAVL(&T, testcase[i]);
		printf(" : ");
		inorderAVL(T);
		printf("\n");
	}
}

/*
ť�� ���� ������� ������ ���� �� ���� �ڷᱸ������, 
��ȭ�Ǵ� ���� �� �ְ� ������ ����� �˰���� �� ���� �ڷᱸ���� �ƴմϴ�.
�̷� �� ������ �ϳ� ���� �� ���¸� �ٲٴ� ������ �����ϴ� �� �ǹ������γ� ���������γ� �� �����ϴ�.
updateBF�� ���� ���ڸ� 4�� �� �� �ƴմϴ�.
�θ� ��� �������� �Ѳ����� �ֱ� ���ؼ� ���̷����� �׷��� ���� �̴ϴ�. 
�̹� �� �� ��Ʈ���� �����Դµ� �ٽ� �θ� ��� ã������ �ð� ������ ������ �����ϴ�. 
�������� ���� ����� ���� �� ���ٸ�, ���̷��� �ڵ忡 �߰��� �Ͻð� ���� ���ȿ� ����ϰ� ¥�� ���� ���� �����ϴ�.
*/