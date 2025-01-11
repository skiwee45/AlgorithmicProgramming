#include <vector>
#include <algorithm>
using namespace std;

vector<vector<int>> merge(vector<vector<int>> &intervals)
{
  sort(intervals.begin(), intervals.end());

  vector<vector<int>> mergedResult;
  int currentEnd = -1;
  for (auto interval : intervals)
  {
    if (interval[0] <= currentEnd)
    {
      currentEnd = max(currentEnd, interval[1]);
      mergedResult[mergedResult.size() - 1][1] = currentEnd;
    }
    else
    {
      currentEnd = interval[1];
      mergedResult.push_back(interval);
    }
  }
  return mergedResult;
}

vector<vector<int>> intervalIntersection(vector<vector<int>> &firstList, vector<vector<int>> &secondList)
{
  int i = 0;
  int j = 0;

  vector<vector<int>> overlaps;
  while (i < firstList.size() && j < secondList.size())
  {
    // try to overlap i and j
    int start = max(firstList[i][0], secondList[j][0]);
    int end = min(firstList[i][1], secondList[j][1]);

    if (start <= end)
    {
      overlaps.push_back({start, end});
    }

    if (firstList[i][1] > secondList[j][1])
      j++;
    else
      i++;
  }
  return overlaps;
}

// hard problem
int bestRotation(vector<int> &nums)
{
  // create intervals
  int n = nums.size();
  vector<int> starts;
  vector<int> ends;

  for (int i = 0; i < n; i++)
  {
    if (nums[i] <= i)
    {
      starts.push_back(0);
      ends.push_back(i - nums[i] + 1);
    }

    starts.push_back(i + 1);
    ends.push_back(i + 1 + n - nums[i]);
  }

  sort(starts.begin(), starts.end());
  sort(ends.begin(), ends.end());

  int currentDepth = 0;
  int maxDepth = -1;
  int bestRotation = 0;
  int j = 0;
  for (int i = 0; i < starts.size(); i++)
  {
    currentDepth++;
    while (j < ends.size() && ends[j] <= starts[i])
    {
      currentDepth--;
      j++;
    }

    if (currentDepth > maxDepth)
    {
      maxDepth = currentDepth;
      bestRotation = starts[i];
    }
  }

  return bestRotation;
}