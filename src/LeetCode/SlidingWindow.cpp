#include <vector>
#include <string>
using namespace std;

// abab
// ab
vector<int> findAnagrams(string s, string p)
{
  // all lowercase letters
  vector<int> pFreq(26, 0);
  vector<int> sFreq(26, 0);

  for (int i = 0; i < p.size(); i++)
  {
    pFreq[p[i] - 'a']++;
  }

  int start = 0;
  int end = 0;
  while (end < s.size() && end < p.size())
  {
    sFreq[s[end] - 'a']++;
    end++;
  }

  vector<int> result;

  while (end < s.size())
  {
    // see if anagram
    if (sFreq == pFreq)
      result.push_back(start);

    sFreq[s[end] - 'a']++;
    end++;

    sFreq[s[start] - 'a']--;
    start++;
  }

  if (sFreq == pFreq)
    result.push_back(start);

  return result;
}

int findLongestSubstring(string s, int k, char c)
{
  int bestLength = 0;
  int non_c_chars = 0;
  int start = 0;
  int end = 0;
  while (end < s.size() && non_c_chars <= k)
  {
    bestLength = max(bestLength, end - start);

    if (s[end] != c)
    {
      non_c_chars++;
    }
    end++;

    while (non_c_chars > k)
    {
      if (s[start] != c)
      {
        non_c_chars--;
      }
      start++;
    }
  }
  bestLength = max(bestLength, end - start);
  return bestLength;
}

int characterReplacement(string s, int k)
{
  int bestLength = 0;
  for (char c = 'A'; c <= 'Z'; c++)
  {
    bestLength = max(bestLength, findLongestSubstring(s, k, c));
  }
  return bestLength;
}