package USACO_Practice;

import java.io.*;
import java.util.*;

//http://www.usaco.org/index.php?page=viewproblem2&cpid=990
public class US_BerryPicking {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("berries.in"));
        PrintWriter pw = new PrintWriter("berries.out");

        StringTokenizer in = new StringTokenizer(r.readLine());
        int n = Integer.parseInt(in.nextToken());
        int b = Integer.parseInt(in.nextToken());

        StringTokenizer in2 = new StringTokenizer(r.readLine());
        int[] trees = new int[n];
        for(int i = 0; i < n; i ++) {
            trees[i] = Integer.parseInt(in2.nextToken());
        }
        pw.print(solve(n, b, trees));
        pw.close();

//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        int b = in.nextInt();
//        int[] trees = new int[n];
//        for(int i = 0; i < n; i ++) {
//            trees[i] = in.nextInt();
//        }
//
//        System.out.print(solve(n, b, trees));
    }

    private static int solve(int n, int b, int[] trees) {
        Arrays.sort(trees);
        int offset = 0;
        if(n > b) {
            offset = n - b;
        }

        int maxBerries = 0;
        for(int min = 1; min < trees[n - 1]; min++) {
            int num = 0;
            int[] remainders = new int[n];
            for (int i = offset; i < n; i++) {
                num += trees[i] / min;
                remainders[i] = trees[i] % min;
            }
            if(num < b / 2)
                break;
            if(num >= b) {
                maxBerries = Math.max(b / 2 * min, maxBerries);
                continue;
            }
            int temp = (num - b / 2) * min;
            int leftSpots = b - num;
            Arrays.sort(remainders);
            for (int i = 0; i < leftSpots; i++) {
                temp += remainders[remainders.length - 1 - i];
            }
            maxBerries = Math.max(temp, maxBerries);
        }
        return maxBerries;
    }
}
