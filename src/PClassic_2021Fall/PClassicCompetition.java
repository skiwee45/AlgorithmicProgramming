package PClassic_2021Fall;

import java.util.Arrays;

//https://pclassic.org/problems
public class PClassicCompetition {
    public static void main(String[] args) {
        System.out.println(pandaLand(2, 3, "RDR"));
    }

    public static int testing(int bears, int testtime, int totaltime) {
        int times = totaltime / testtime;
        if(times >= bears - 1) {
            return 1;
        }
        if(times == 1) {
            return (int) Math.ceil(Math.log(bears) / Math.log(2));
        }
        return testing((int) Math.ceil(bears / 2.0), testtime, totaltime - testtime) + 1;
    }

    public static int pandaLand(int n, int m, String path) {
        int[][] totals = new int[n][m];
        int r = 0;
        int c = 0;

        for (int j = 0; j < n; j++) {
            totals[j][c]++;
        }

        for (int j = 0; j < m; j++) {
            totals[r][j]++;
        }
        totals[r][c]--;

        for (int i = 0; i < path.length(); i++) {
            if(path.charAt(i) == 'D')
                r++;
            else
                c++;
            for (int j = 0; j < n; j++) {
                totals[j][c]++;
            }

            for (int j = 0; j < m; j++) {
                totals[r][j]++;
            }
            totals[r][c]--;
        }

        //sum them
        int[] flat = new int[n*m];
        int j = 0;
        for (int i = 0; i < totals.length; i++) {
            System.arraycopy(totals[i], 0, flat, j, totals[i].length);
            j += totals[i].length;
        }

        Arrays.sort(flat);
        reverse(flat, n * m);
        long total = 0;
        long counter = 0;
        for (int e :
                flat) {
            total += counter * e;
            counter++;
        }
        return (int) (total % 1000000007);
    }

    static void reverse(int a[], int n) {
        int i, t;
        for (i = 0; i < n / 2; i++) {
            t = a[i];
            a[i] = a[n - i - 1];
            a[n - i - 1] = t;
        }
    }
}
