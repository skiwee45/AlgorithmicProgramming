#include <iostream>
#include <string>

char turnRight(char direction)
{
  switch (direction)
  {
  case 'N':
    return 'E';
  case 'E':
    return 'S';
  case 'S':
    return 'W';
  case 'W':
    return 'N';
  }
  return 'N';
}

bool isCW(std::string path)
{
  char lastStep = path[0];
  int numRightTurns = 0;
  for (auto step : path)
  {
    if (step != lastStep)
    {
      if (step == turnRight(lastStep))
      {
        numRightTurns++;
      }
      else
      {
        numRightTurns--;
      }
    }

    lastStep = step;
  }

  return numRightTurns > 0;
}

int main(int argc, char const *argv[])
{
  std::string line;
  std::cin >> line;
  int n = std::stoi(line);

  for (int i = 0; i < n; i++)
  {
    std::cin >> line;
    std::cout << (isCW(line) ? "CW" : "CCW") << std::endl;
  }
}
