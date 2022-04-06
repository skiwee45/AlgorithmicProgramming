package USACO_Practice;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

//http://usaco.org/index.php?page=viewproblem2&cpid=1060
public class UB_DaisyChains {
    public static void main(String[] args) throws NoSuchMethodException, IOException, InvocationTargetException, IllegalAccessException {
        boolean[] corrects = TestRunner.debugTests(UB_DaisyChains.class.getDeclaredMethod("solve", new Class[] {Kattio.class}), "src/USACO_Practice/UB_DaisyChainsTesters", 10);
        System.out.println(Arrays.toString(corrects));
    }

    public static String[] solve(Kattio in) {
        int n = in.nextInt();
        int[] daisies = new int[n];
        for (int i = 0; i < n; i++) {
            daisies[i] = in.nextInt();
        }
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

        return new String[] {Integer.toString(total)};
    }
}
