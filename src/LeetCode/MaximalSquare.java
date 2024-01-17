package LeetCode;

public class MaximalSquare {
    public int maximalSquare(char[][] orig) {
        int maxSideLength = 0;

        int[][] map = new int[orig.length][orig[0].length];

        for (int i = 0; i < orig.length; i++) {
            map[i][0] = orig[i][0] - '0';
            if (map[i][0] == 1)
                maxSideLength = 1;
        }

        for (int i = 0; i < orig[0].length; i++) {
            map[0][i] = orig[0][i] - '0';
            if (map[0][i] == 1)
                maxSideLength = 1;
        }

        for (int i = 1; i < orig.length; i++) {
            for (int j = 1; j < orig[0].length; j++) {
                if (orig[i][j] - '0' == 1) {
                    map[i][j] = min3(map[i - 1][j], map[i][j - 1], map[i - 1][j - 1]) + 1;
                    if (map[i][j] > maxSideLength)
                        maxSideLength = map[i][j];
                }
            }
        }

        return maxSideLength * maxSideLength;
    }

    private int min3(int v1, int v2, int v3) {
        return Math.min(Math.min(v1, v2), v3);
    }
}
