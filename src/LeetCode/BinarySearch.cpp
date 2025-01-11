#include <vector>
#include <iostream>
#include <cmath>
using namespace std;

vector<int> searchRange(vector<int> &nums, int target)
{
  // binary search twice, once for range start, once for end
  int start = 0;
  int end = nums.size() - 1;
  int rangeStart = -1;

  while (start <= end)
  {
    int mid = (start + end) / 2;
    int num = nums[mid];
    if (num < target)
    {
      start = mid + 1;
    }
    else
    {
      end = mid - 1;
      if (num == target)
      {
        rangeStart = mid;
      }
    }
  }

  start = 0;
  end = nums.size() - 1;
  int rangeEnd = -1;

  while (start <= end)
  {
    int mid = (start + end) / 2;
    int num = nums[mid];
    if (num <= target)
    {
      start = mid + 1;
      if (num == target)
      {
        rangeEnd = mid;
      }
    }
    else
    {
      end = mid - 1;
    }
  }

  return vector<int>{rangeStart, rangeEnd};
}

bool finishesEating(vector<int> &piles, int hours, int speed)
{
  int hoursEating = 0;
  double fSpeed = static_cast<double>(speed);

  for (int pile : piles)
  {
    hoursEating += ceil(pile / fSpeed);
  }

  // cout << " hours " << hoursEating << endl;

  return hoursEating <= hours;
}

int minEatingSpeed(vector<int> &piles, int h)
{
  int start = 1;     // speed can't be 0
  int end = INT_MAX; // h >= piles.size()
  int bestSpeed = 0;

  while (start <= end)
  {
    int mid = (static_cast<long long>(start) + end) / 2;

    // cout << "speed " << mid;

    if (finishesEating(piles, h, mid))
    {
      bestSpeed = mid;
      end = mid - 1;
    }
    else
    {
      start = mid + 1;
    }
  }

  return bestSpeed;
}

int searchRotatedArray(vector<int> nums, int target)
{
  // key is, one half will always be fully sorted
  int start = 0;
  int end = nums.size() - 1;

  while (start <= end)
  {
    int mid = (start + end) / 2;

    if (nums[mid] == target)
      return mid;

    if (nums[mid] <= nums[end])
    {
      if (nums[mid] < target && nums[end] >= target)
        start = mid + 1;
      else
        end = mid - 1;
    }
    else
    {
      if (nums[mid] > target && nums[start] <= target)
        end = mid - 1;
      else
        start = mid + 1;
    }
  }

  return -1;
}

int main(int argc, char const *argv[])
{
  vector<int> vec{1000000000};
  cout << minEatingSpeed(vec, 2) << endl;
  // cout << searchRotatedArray({1, 2, 3, 4, 5}, 6) << endl;
  // cout << searchRotatedArray({1, 2, 3, 4, 5}, 3) << endl;
  // cout << searchRotatedArray({1, 2, 3, 4, 5}, 1) << endl;
  // cout << searchRotatedArray({6, 7, 8, 1, 2, 3, 4, 5}, 7) << endl;
  // cout << searchRotatedArray({6, 7, 8, 1, 2, 3, 4, 5}, 4) << endl;
  // cout << searchRotatedArray({6, 7, 8, 1, 2, 3, 4, 5}, 1) << endl;
  // cout << searchRotatedArray({6, 7, 8, 1, 2, 3, 4, 5}, 10) << endl;

  return 0;
}
