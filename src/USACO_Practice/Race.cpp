#include <iostream>
#include <string>
#include <fstream>
#include <cmath>
using namespace std;

// Some rounding and int / long issues, but overall works
int gauss(int x)
{
  return (1LL + x) * x / 2;
}

int findRaceTime(int length, int endSpeed)
{
  // binary search
  int start = 0;
  int end = ceil(sqrt(2.0L * length));

  int bestVel = 1;
  int skippable = gauss(endSpeed);

  while (start <= end)
  {
    int mid = (start + end) / 2;

    int mandatoryLength = gauss(mid) + max(0, gauss(mid - 1) - skippable + 1);
    // cout << "mid " << mid << " length " << mandatoryLength << endl;

    if (mandatoryLength <= length)
    {
      bestVel = mid;
      start = mid + 1;
    }
    else
    {
      end = mid - 1;
    }
  }

  int diff = length - (gauss(bestVel) + max(0, gauss(bestVel - 1) - skippable + endSpeed));
  int mandatoryTime = bestVel + max(0, bestVel - endSpeed);

  // cout << bestVel << endl;
  // cout << diff << endl;

  if (diff <= 0)
  {
    return mandatoryTime;
  }
  if (diff <= bestVel)
  {
    return mandatoryTime + 1;
  }
  return mandatoryTime + 2;
}

int main(int argc, char const *argv[])
{
  // cout << findRaceTime(1000000000, 14339) << endl;

  // gausser
  // int g;
  // cin >> g;
  // cout << gauss(g);

  // return 0;

  ifstream in("race.in");
  int length;
  in >> length;
  int n;
  in >> n;

  ofstream out("race.out");

  for (int i = 0; i < n; i++)
  {
    int endSpeed;
    in >> endSpeed;
    out << findRaceTime(length, endSpeed) << endl;
  }
}
