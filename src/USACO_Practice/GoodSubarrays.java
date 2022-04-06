package USACO_Practice;

import java.util.*;

public class GoodSubarrays {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        HashMap<Integer, Long> map = new HashMap<>();
        map.put(0, (long)1);
        int[] nums = new int[n + 1];
        for(int i = 0; i < n; i++) {
            nums[i + 1] = (((nums[i] + in.nextInt()) % n) + n) % n;
            map.put(nums[i + 1], map.getOrDefault(nums[i + 1], (long)0) + 1);
        }
        //System.out.println(Arrays.toString(nums));
        long total = 0;
        for(Map.Entry<Integer, Long> entry : map.entrySet()) {
            long o = entry.getValue();
            total += o * (o - 1) / 2;
        }
        System.out.print(total);
    }
}
