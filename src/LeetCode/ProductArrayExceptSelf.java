package LeetCode;

import java.util.Arrays;

public class ProductArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int[] prefix = prefixProduct(nums);
        int[] postfix = postfixProduct(nums);
        int[] output = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            output[i] = prefix[i] * postfix[i + 1];
        }
        return output;
    }

    private int[] prefixProduct(int[] nums) {
        int[] prods = new int[nums.length + 1];
        prods[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            prods[i + 1] = prods[i] * nums[i];
        }
        return prods;
    }

    private int[] postfixProduct(int[] nums) {
        int n = nums.length;
        int[] prods = new int[n + 1];
        prods[n] = 1;
        for (int i = n; i >= 1; i--) {
            prods[i - 1] = prods[i] * nums[i - 1];
        }
        return prods;
    }
}
