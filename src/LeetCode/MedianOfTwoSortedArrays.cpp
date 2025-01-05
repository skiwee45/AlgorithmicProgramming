#include <vector>
#include <iostream>
using namespace std;

int iv(vector<int> &vec, int i)
{
  if (i < 0)
    return INT_MIN;

  if (i >= vec.size())
    return INT_MAX;

  return vec[i];
}

double findMedianSortedArrays(vector<int> &nums1, vector<int> &nums2)
{
  // convention, 0 is gap between index 0 and 1

  int s1 = nums1.size();
  int s2 = nums2.size();
  if (s1 == 0 || s1 > s2)
  {
    return findMedianSortedArrays(nums2, nums1);
  }

  bool oddTotal = (s1 + s2) % 2 == 1;
  int leftTotal = (s1 + s2) / 2; // this is true for odd and even

  int start = 0;
  int end = nums1.size() - 1;

  while (start <= end)
  {
    int mid = (start + end) / 2;

    int left1 = mid + 1;
    int left2 = leftTotal - left1;

    cout << left1 << " " << left2 << endl;

    int leftNum = max(iv(nums1, left1 - 1), iv(nums2, left2 - 1));
    int rightNum = min(iv(nums1, left1), iv(nums2, left2));

    if (leftNum <= rightNum)
    {
      // correct split
      if (oddTotal)
        return rightNum;
      else
        return (leftNum + rightNum) / 2.0;
    }

    if (leftNum == iv(nums1, left1 - 1))
    {
      // left is too large
      end = mid - 1;
    }
    else
    {
      start = mid + 1;
    }
  }

  return -454545;
}

int main(int argc, char const *argv[])
{
  vector<int> nums1 = {2};
  vector<int> nums2 = {1, 3};

  cout << findMedianSortedArrays(nums1, nums2);

  return 0;
}
