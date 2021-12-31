package USACO_Practice;

import java.util.HashSet;
import java.util.Scanner;

//http://usaco.org/index.php?page=viewproblem2&cpid=1060
public class UB_DaisyChains {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        execute(n, arr);
    }

    public static void execute(int n, int[] daisies) {
        int total = n;

        for (int i = 0; i < n; i++) {
            int totalPetals = daisies[i];
            HashSet<Integer> storedFlowers = new HashSet<>();
            storedFlowers.add(daisies[i]);
            for (int j = i + 1; j < n; j++) {
                totalPetals += daisies[j];
                storedFlowers.add(daisies[j]);
                int numFlowers = j - i + 1;
                if(totalPetals % numFlowers == 0 && storedFlowers.contains(totalPetals / numFlowers))
                    total++;
            }
        }

        System.out.print(total);
    }
}
