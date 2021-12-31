package USACO_Bronze_2021December;

import java.util.Arrays;
import java.util.Scanner;

//http://www.usaco.org/index.php?page=viewproblem2&cpid=1156
public class UB_AirConditioning {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] prefs = new int[n];
        int[] deltas = new int[n];
        for (int i = 0; i < n; i++) {
            prefs[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            deltas[i] = prefs[i] - in.nextInt();
        }
        //System.out.println(Arrays.toString(deltas));

        int total = 0;
        int i = 0;
        while(i < n) {
            if(deltas[i] == 0) {
                i++;
                continue;
            }
            //find chain and minimum
            int min = Math.abs(deltas[i]);
            int j;
            for(j = i + 1; j < n; j++) {
                if(deltas[j] * deltas[i] <= 0)
                    break;
                min = Math.min(min, Math.abs(deltas[j]));
            }
            j--;
            total += min;
            if(deltas[i] < 0)
                min *= -1;
            for(int k = i; k <= j; k++) {
                deltas[k] -= min;
            }
        }
        System.out.print(total);
    }
}
