#include <tuple>
#include <list>
#include <unordered_map>
using namespace std;

// Done with my own linkedlist
template <typename T>
struct Node
{
  T value;
  Node *next;
  Node *prev;
  Node(const T &v) : value(v) {};
};

class LRUCache
{
private:
  int capacity;
  int size;
  Node<pair<int, int>> *head;
  Node<pair<int, int>> *tail;
  unordered_map<int, decltype(head)> map;
  void removeNode(decltype(head) node)
  {
    size--;
    node->prev->next = node->next;
    node->next->prev = node->prev;
    delete node;
  }

  void pushFront(pair<int, int> value)
  {
    size++;
    auto node = new Node(value);
    node->prev = head;
    node->next = head->next;
    head->next->prev = node;
    head->next = node;
  }

  void pushBack(pair<int, int> value)
  {
    size++;
    auto node = new Node(value);
    node->next = tail;
    node->prev = tail->prev;
    tail->prev->next = node;
    tail->prev = node;
  }

public:
  LRUCache(int capacity) : capacity(capacity), size(0)
  {
    head = new Node(make_pair(0, 0));
    tail = new Node(make_pair(0, 0));
    head->next = tail;
    tail->prev = head;
  }

  int get(int key)
  {
    if (map.find(key) == map.end())
    {
      return -1;
    }
    auto node = map[key];
    int value = node->value.second;

    removeNode(node);
    pushBack({key, value});
    map[key] = tail->prev;

    return value;
  }

  void put(int key, int value)
  {
    if (map.find(key) != map.end())
    {
      auto node = map[key];
      node->value.second = value;

      removeNode(node);
      pushBack({key, value});
      map[key] = tail->prev;
      return;
    }

    pushBack({key, value});
    map[key] = tail->prev;

    if (size > capacity)
    {
      auto node = head->next;
      map.erase(node->value.first);
      removeNode(node);
    }
  }
};

// Done with C++ linkedlist
class LRUCache
{
private:
  int capacity;
  list<pair<int, int>> cache;
  unordered_map<int, decltype(cache)::iterator> map;

public:
  LRUCache(int capacity) : capacity(capacity) {};

  int get(int key)
  {
    if (map.find(key) == map.end())
    {
      return -1;
    }
    auto iter = map[key];
    int value = (*iter).second;

    // remove and move to back
    cache.erase(iter);
    cache.push_back({key, value});

    map[key] = --cache.end();

    return value;
  }

  void put(int key, int value)
  {
    if (map.find(key) != map.end())
    {
      auto iter = map[key];
      cache.erase(iter);
      cache.push_back({key, value});
      map[key] = --cache.end();
      return;
    }

    cache.push_back({key, value});
    map[key] = --cache.end();

    if (cache.size() > capacity)
    {
      auto kv = cache.front();
      map.erase(kv.first);
      cache.pop_front();
    }
  }
};