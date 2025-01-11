#include <string>
#include <array>
#include <iostream>
using namespace std;

class Trie
{
public:
  struct Node
  {
    char character;
    array<Node *, 26> children;
    bool isWord;

    Node() : character('a'), isWord(false), children{} {}
    Node(char c) : character(c), isWord(false), children{} {}
  };

  Node *root;

  Trie()
  {
    root = new Node();
  }

  void insert(string word)
  {
    insert(word, 0, root);
  }

  bool search(string word)
  {
    return search(word, 0, root);
  }

  bool startsWith(string prefix)
  {
    return startsWith(prefix, 0, root);
  }

private:
  void insert(string &word, int index, Node *root)
  {
    char c = word[index];
    int cIndex = c - 'a';
    cout << cIndex << endl;
    if (root->children[cIndex] == nullptr)
    {
      // create new node there
      root->children[cIndex] = new Node(c);
    }

    if (index == word.size() - 1)
      root->children[cIndex]->isWord = true;
    else
      insert(word, index + 1, root->children[cIndex]);
  }

  bool search(string &word, int index, Node *root)
  {
    char c = word[index];
    int cIndex = c - 'a';
    if (root->children[cIndex] == nullptr)
      return false;

    if (index == word.size() - 1)
      return root->children[cIndex]->isWord;

    return search(word, index + 1, root->children[cIndex]);
  }

  bool startsWith(string &word, int index, Node *root)
  {
    char c = word[index];
    int cIndex = c - 'a';
    if (root->children[cIndex] == nullptr)
      return false;

    if (index == word.size() - 1)
      return true;

    return startsWith(word, index + 1, root->children[cIndex]);
  }
};

int main(int argc, char const *argv[])
{
  Trie t;
  t.insert("fivebigboys");
  return 0;
}
