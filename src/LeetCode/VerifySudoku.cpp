#include <vector>
#include <unordered_set>
#include <iostream>
using namespace std;

bool isValidSudoku(vector<vector<char>> &board)
{
  // 9 sets for rows, 9 for cols, 9 for subgrids
  vector<unordered_set<int>> rowSets(9);
  vector<unordered_set<int>> colSets(9);
  vector<vector<unordered_set<int>>> subgridSets(3, vector<unordered_set<int>>(3));

  for (int r = 0; r < 9; r++)
  {
    for (int c = 0; c < 9; c++)
    {
      int cell = board[r][c];
      if (cell == '.')
        continue;

      if (rowSets[r].find(cell) != rowSets[r].end())
        return false;

      if (colSets[c].find(cell) != colSets[c].end())
        return false;

      if (subgridSets[r / 3][c / 3].find(cell) != subgridSets[r / 3][c / 3].end())
        return false;

      rowSets[r].insert(cell);
      colSets[c].insert(cell);
      subgridSets[r / 3][c / 3].insert(cell);
    }
  }

  return true;
}
