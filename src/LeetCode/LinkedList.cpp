#include <iostream>
#include <list>
#include <unordered_map>
using namespace std;

class Node
{
public:
  Node() : value(0), next(nullptr) {};
  Node(int v, Node *n) : value(v), next(n) {};
  int value;
  Node *next;
};

void prepend(Node *&head, int v)
{
  Node *newHead = new Node();
  newHead->value = v;
  newHead->next = head;
  head = newHead;
}

void printList(const Node *head)
{
  while (head != nullptr)
  {
    std::cout << head->value << " ";
    head = head->next;
  }
  std::cout << std::endl;
}

void reverseListIter(Node *&head)
{
  Node *prev = nullptr;
  Node *curr = head;

  while (curr != nullptr)
  {
    auto nextCurr = curr->next;
    curr->next = prev;
    prev = curr;
    curr = nextCurr;
  }

  head = prev;
}

Node *reverseListRecur(Node *head)
{
  // base cases
  if (head == nullptr || head->next == nullptr)
  {
    return head;
  }

  auto nextNode = head->next;
  auto newHead = reverseListRecur(nextNode);
  nextNode->next = head; // points next back at current
  head->next = nullptr;  // this will be set by previous if there is previous
  return newHead;
}

// assume k < length of list
// if k = 0, remove last
void removeKthFromEnd(Node *&head, int k)
{
  // two pointer strategy
  auto scoutPtr = head; // get this one k + 2 ahead
  for (int i = 0; i < k + 2; i++)
  {
    if (scoutPtr == nullptr) // remove first node
    {
      auto temp = head;
      head = head->next;
      delete temp;
      return;
    }
    scoutPtr = scoutPtr->next;
  }

  auto followPtr = head;

  while (scoutPtr != nullptr)
  {
    scoutPtr = scoutPtr->next;
    followPtr = followPtr->next;
  }

  // now followPtr is one previous to the removed
  auto temp = followPtr->next;
  followPtr->next = followPtr->next->next;
  delete temp;
}

int findLength(Node *head)
{
  int length = 0;
  while (head != nullptr)
  {
    length++;
    head = head->next;
  }

  return length;
}

Node *findIntersection(Node *h1, Node *h2)
{
  // first find lengths
  int l1 = findLength(h1);
  int l2 = findLength(h2);
  int diff = abs(l1 - l2);

  // let h1 be longer and h2 shorter
  if (l1 < l2)
  {
    auto temp = h1;
    h1 = h2;
    h2 = temp;
  }

  for (int i = 0; i < diff; i++)
  {
    h1 = h1->next;
  }

  while (h1 != nullptr)
  {
    if (h1 == h2)
    {
      return h1;
    }

    h1 = h1->next;
    h2 = h2->next;
  }

  return nullptr;
}

bool detectCycle(Node *head)
{
  // init fast and slow pointers
  Node *slow, *fast;
  slow = fast = head;

  while (fast != nullptr)
  {
    fast = fast->next;
    if (fast == nullptr)
      return false;

    fast = fast->next;
    slow = slow->next;

    if (fast == slow)
      return true;
  }

  return false;
}

int calcNextNum(int n)
{
  int next = 0;
  while (n > 0)
  {
    int digit = n % 10;
    next += digit * digit;
    n /= 10;
  }

  return next;
}

bool isHappyNumber(int n)
{
  // essentially look for a loop
  int slow = n;
  int fast = n;

  while (fast != 1)
  {
    fast = calcNextNum(calcNextNum(fast)); // don't need to check in the middle, cuz 1^2 = 1
    slow = calcNextNum(slow);
    if (fast != 1 && fast == slow)
    {
      return false;
    }
  }

  return true;
}

int main(int argc, char const *argv[])
{
  Node *head = nullptr;
  prepend(head, 5);
  prepend(head, 4);
  prepend(head, 3);
  auto otherList = head;
  prepend(head, 2);
  prepend(head, 1);

  printList(head);

  reverseListIter(head);
  printList(head);

  head = reverseListRecur(head);
  printList(head);

  removeKthFromEnd(head, 4);
  printList(head);

  prepend(otherList, 0);
  prepend(otherList, -1);
  prepend(otherList, -2);
  prepend(otherList, -3);
  printList(otherList);
  auto intersection = findIntersection(head, otherList);
  printList(intersection);

  // intersection->next = otherList;
  cout << detectCycle(otherList) << endl;
}
