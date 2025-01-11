#include <deque>
#include <string>
#include <vector>
using namespace std;

int maxBracketWidth(string s)
{
  deque<int> stack;
}

// this is for a circular nums
vector<int> nextGreaterElements(vector<int> &nums)
{
  // first find largest element
  int startIndex = nums.size() - 1;
  int maxElement = nums[startIndex];
  for (int i = 0; i < nums.size(); i++)
  {
    if (nums[i] > maxElement)
    {
      maxElement = nums[i];
      startIndex = i;
    }
  }

  deque<int> stack;
  vector<int> result(nums.size(), 0);
  for (int i = 0; i < nums.size(); i++)
  {
    // go from startIndex backwards
    int j = (startIndex - i + nums.size()) % nums.size();
    while (!stack.empty() && stack.front() <= nums[j])
    {
      stack.pop_front();
    }
    if (stack.empty())
      result[j] = -1;
    else
      result[j] = stack.front();

    stack.push_front(nums[j]);
  }

  return result;
}
