package USACO_Bronze_2021December;

import java.util.Arrays;
import java.util.Scanner;

//http://www.usaco.org/index.php?page=viewproblem2&cpid=1157
public class UB_WalkingHome {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        int[] answers = new int[t];
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int k = in.nextInt();
            boolean[][] grid = new boolean[n][n];
            for (int j = 0; j < n; j++) {
                String line = in.next();
                for (int l = 0; l < n; l++) {
                    grid[j][l] = line.charAt(l) == '.';
                }
            }
            answers[i] = solve(grid, n, true, 0, 1, k) + solve(grid, n, false, 1, 0, k);
        }
        for (int i = 0; i < t - 1; i++) {
            System.out.println(answers[i]);
        }
        System.out.print(answers[t - 1]);
    }

    private static int solve(boolean[][] grid, int gridSize, boolean dir, int xPos, int yPos, int k) {
        if(xPos >= gridSize || yPos >= gridSize || !grid[yPos][xPos] || k < 0)
            return 0;
        if(xPos == gridSize - 1 && yPos == gridSize - 1)
            return 1;
        //System.out.println(xPos + ", " + yPos + "  k " + k);
        return solve(grid, gridSize, true, xPos, yPos + 1, dir ? k : k - 1) +
                solve(grid, gridSize, false, xPos + 1, yPos, !dir ? k : k - 1);
    }
}
