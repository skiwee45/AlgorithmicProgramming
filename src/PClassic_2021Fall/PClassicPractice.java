package PClassic_2021Fall;

import java.util.ArrayList;
import java.util.List;

//https://pclassic.org/problems
public class PClassicPractice {
    public static void main(String[] args) {
        System.out.println(potionBrewing(6, 2));
    }

//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\raymo\\IdeaProjects\\AT-CS\\src\\ACC\\XORIN.txt"));
//        String[] data = br.readLine().split(" ");
//        int testCases = Integer.parseInt(data[0]);
//        for( ; testCases > 0; testCases--) {
//            int n = Integer.parseInt(br.readLine());
//            int[] arr = new int[n];
//            for (int i = 0; i < n; i++) {
//                arr[i] = Integer.parseInt(br.readLine());
//            }
//            System.out.println(xorSub(n, arr));
//        }
//        br.close();
//    }

    public static long potionBrewing(int n, int k) {
        // Lay1 = n + 1
        // Lay2 = (n-k+1) / 2 * (n-k)
        // Lay3 = (Lay2 + 1) / 2 * (n-2k)
        // Lay4 = (Lay3 + 1) / 2 * (n-3k)
        long total = n+1;
        int i = 1;
        while(n - k * i > 0) {
            total += recurPotion(n, k, i,i);
            i++;
        }
        return total;
    }

    private static long recurPotion(int n, int k, int f, int lay) {
        if(lay == 0) {
            return n;
        }
        return (recurPotion(n, k, f, lay - 1) - k * f + 1) / 2 * (n - k * f);
    }

    public static Integer[] getFactors(int n, int k)
    {
        List<Integer> out = new ArrayList<>();
        for (int i = 1; i <= n; i++)
            if (n % i == 0 && i >= k)
                out.add(i);
        return out.toArray(new Integer[0]);
    }

    public static int xorSub(int n, int[] arr) {
        List<Integer> p_xor = new ArrayList<>();
        p_xor.add(0);
        for (int i = 0; i < n; i++) {
            p_xor.add(p_xor.get(i) ^ arr[i]);
        }

        int total = 0;
        for (int i = 0; i < p_xor.size(); i++) {
            for (int j = i + 1; j < p_xor.size(); j++) {
                if((p_xor.get(j) ^ p_xor.get(i)) == 0)
                    total += j - i - 1;
            }
        }
        return total;
    }
}
