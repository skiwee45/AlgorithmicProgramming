package ACC_ProblemOfTheWeek;

import java.util.*;

public class p1_BitGrid {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int h = in.nextInt();
        int w = in.nextInt();
        int[][] input = new int[h][w];
        for(int i = 0; i < h; i++) {
            String line = in.next();
            for(int j = 0; j < w; j++) {
                input[i][j] = Character.getNumericValue(line.charAt(j));
            }
        }

        int[][] cascades = new int[h][w];
        int cost = 0;
        for(int i = h - 1; i >= 0; i--) {
            for(int j = w - 1; j >= 0; j--) {
                boolean inR = (j + 1 < w);
                boolean inD = (i + 1 < h);
                int right = inR ? cascades[i][j + 1] : 0;
                int down = inD ? cascades[i + 1][j] : 0;
                int back = inD && inR ? cascades[i + 1][j + 1] : 0;
                int flipped = right ^ down ^ back;
                if (flipped != input[i][j]) {
                    cost++;
                    cascades[i][j] = flipped ^ 1;
                } else {
                    cascades[i][j] = flipped;
                }
            }
        }
        if (input[h - 1][w - 1] == 1)
            cost--;
        System.out.print(cost);
    }
}