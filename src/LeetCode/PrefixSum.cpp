#include <vector>
#include <unordered_map>
using namespace std;

int subarraySum(vector<int> &nums, int k)
{
  // instead of prefix sum, do prefix frequency hash
  int sum = 0;
  unordered_map<int, int> prefixFreq; // key is to use hash, just like pair sum can be solved with hash
  prefixFreq[0] = 1;
  int result = 0;

  for (int num : nums)
  {
    sum += num;
    result += prefixFreq[sum - k];
    prefixFreq[sum] = prefixFreq[sum] + 1;
  }

  return result;
}

vector<int> productExceptSelf(vector<int> &nums)
{
  vector<int> forwardMults;
  forwardMults.push_back(1); // its offset by one
  for (int i = 0; i < nums.size(); i++)
  {
    forwardMults.push_back(forwardMults.back() * nums[i]);
  }

  vector<int> backMults(nums.size() + 1);
  backMults[nums.size()] = 1;
  for (int i = nums.size() - 1; i >= 0; i--)
  {
    backMults[i] = backMults[i + 1] * nums[i];
  }

  vector<int> result;
  for (int i = 0; i < nums.size(); i++)
  {
    int mult = forwardMults[i] * backMults[i + 1];
    result.push_back(mult);
  }

  return result;
}