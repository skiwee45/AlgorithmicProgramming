package USACO_Practice;

import java.util.*;

//http://usaco.org/index.php?page=viewproblem2&cpid=1063
public class US_RectangularPasture {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] cows = new int[n][2];
        Integer[] xRankToIndex = new Integer[n]; //holds indexes of cows in sorted x order
        Integer[] yRankToIndex = new Integer[n]; //same thing but x order
        for (int i = 0; i < n; i++) {
            cows[i] = new int[] {in.nextInt(), in.nextInt()};
            xRankToIndex[i] = i;
            yRankToIndex[i] = i;
        }

        class CowsXSort implements Comparator<Integer> {
            @Override
            public int compare(Integer o1, Integer o2) {
                return cows[o1][0] - cows[o2][0];
            }
        }

        class CowsYSort implements Comparator<Integer> {
            @Override
            public int compare(Integer o1, Integer o2) {
                return cows[o1][1] - cows[o2][1];
            }
        }

        Arrays.sort(xRankToIndex, new CowsXSort());
        Arrays.sort(yRankToIndex, new CowsYSort());

        int[] indexToXRank = new int[n];
        int[] indexToYRank = new int[n];
        for (int i = 0; i < n; i++) {
            indexToXRank[xRankToIndex[i]] = i;
            indexToYRank[yRankToIndex[i]] = i;
        }

        int[][] aboveLists = new int[n][n+1]; //in xRank order, prefix sums
        int[][] belowLists = new int[n][n+1];
        for (int i = 0; i < n; i++) {
            aboveLists[i][0] = 0;
            belowLists[i][0] = 0;
        }
        for (int i = 0; i < n; i++) {
            int cowIndexI = xRankToIndex[i];
            for (int j = 0; j < n; j++) {
                int cowIndexJ = xRankToIndex[j];
                boolean above = indexToYRank[cowIndexJ] > indexToYRank[cowIndexI];
                if (i == j) {
                    aboveLists[i][j + 1] = aboveLists[i][j];
                    belowLists[i][j + 1] = belowLists[i][j];
                } else if (above) {
                    aboveLists[i][j + 1] = aboveLists[i][j] + 1;
                    belowLists[i][j + 1] = belowLists[i][j];
                } else {
                    aboveLists[i][j + 1] = aboveLists[i][j];
                    belowLists[i][j + 1] = belowLists[i][j] + 1;
                }
            }
        }
//        for(int[] list : aboveLists) {
//            System.out.println(Arrays.toString(list));
//        }
//        System.out.println("\n");
//        for(int[] list : belowLists) {
//            System.out.println(Arrays.toString(list));
//        }

        long total = 1;
        for (int p = 0; p < n; p++) {
            total++;
            for (int o = p + 1; o < n; o++) {
                total++;
                //if point o is below point p, aboveNum = points above p, belowNum = points below o
                int aboveNum;
                int belowNum;
                if (indexToYRank[xRankToIndex[o]] > indexToYRank[xRankToIndex[p]]) {
                    aboveNum = aboveLists[o][o] - aboveLists[o][p+1];
                    belowNum = belowLists[p][o] - belowLists[p][p+1];
                } else {
                    aboveNum = aboveLists[p][o] - aboveLists[p][p+1];
                    belowNum = belowLists[o][o] - belowLists[o][p+1];
                }
                //System.out.println("P- " + p + " O- " + o + " Above- " + aboveNum + " Below- " + belowNum);
                total += aboveNum + belowNum + aboveNum * belowNum;
            }
        }
        System.out.print(total);
    }
}