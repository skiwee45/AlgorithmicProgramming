#include <queue>
#include <string>
#include <vector>
#include <unordered_map>
#include <iostream>
#include <set>
using namespace std;

// sort by freq, then by lexigraphical order
vector<string> topKFrequent(vector<string> &words, int k)
{
  unordered_map<string, int> freq;
  vector<string> uniqueWords;
  for (string word : words)
  {
    if (freq.find(word) == freq.end())
      uniqueWords.push_back(word);
    freq[word] = freq[word] + 1;
  }

  auto comparator = [&freq](string a, string b)
  {
    if (freq[a] == freq[b]) // all reversed, because heaps are flipped
      return a > b;

    return freq[a] < freq[b];
  };

  for (string word : uniqueWords)
  {
    cout << word << endl;
  }

  // CAREFUL, HEAPS HAVE LARGER ELEMENTS ON TOP
  make_heap(uniqueWords.begin(), uniqueWords.end(), comparator);
  vector<string> result;
  for (int i = 0; i < k; i++)
  {
    result.push_back(uniqueWords.front());

    // pop heap
    pop_heap(uniqueWords.begin(), uniqueWords.end(), comparator);
    uniqueWords.pop_back();

    // push heap
    // v.push_back(6);
    // std::push_heap(v.begin(), v.end(), comparator);
  }

  return result;
}

struct ListNode
{
  int val;
  ListNode *next;
  ListNode() : val(0), next(nullptr) {}
  ListNode(int x) : val(x), next(nullptr) {}
  ListNode(int x, ListNode *next) : val(x), next(next) {}
};

ListNode *mergeKLists(vector<ListNode *> &lists) // mutates lists!
{
  // heap for easy merge
  ListNode *dummyHead = new ListNode();
  ListNode *current = dummyHead;
  vector<pair<int, int>> heap; // first int is value, second is index of the list the came from

  for (size_t i = 0; i < lists.size(); i++)
  {
    if (lists[i] != nullptr)
    {
      heap.push_back({lists[i]->val, i});
    }
  }

  make_heap(heap.begin(), heap.end(), greater<>()); // min heap

  while (!heap.empty())
  {
    // pop, then replace using the list it popped from
    pop_heap(heap.begin(), heap.end(), greater<>());
    auto [val, i] = heap.back();
    heap.pop_back();
    current->next = lists[i];
    current = current->next;
    lists[i] = lists[i]->next;

    if (lists[i] != nullptr)
    {
      heap.push_back({lists[i]->val, i});
      push_heap(heap.begin(), heap.end(), greater<>());
    }
  }

  auto finalHead = dummyHead->next;
  delete dummyHead;
  return finalHead;
}

class MedianFinder
{
private:
  // use a balanced bst
  multiset<int> bst;
  multiset<int>::iterator rootIter;

public:
  MedianFinder()
  {
    rootIter = bst.end();
  }

  void addNum(int num)
  {
    bst.insert(num);
    if (rootIter == bst.end())
      rootIter = bst.begin();
    else
    {
      if (bst.size() % 2 == 0 && num < *rootIter)
        rootIter--;

      else if (bst.size() % 2 == 1 && num >= *rootIter)
        rootIter++;
    }
  }

  double findMedian()
  {
    auto nextIter = rootIter;
    nextIter++;
    if (bst.size() % 2 == 0)
      return (*rootIter + *nextIter) / 2.0;

    return *rootIter;
  }
};
