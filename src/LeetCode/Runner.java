package LeetCode;

import java.util.Arrays;

public class Runner {
    public static void main(String[] args) {
        ProductArrayExceptSelf test = new ProductArrayExceptSelf();
        System.out.println(Arrays.toString(test.productExceptSelf(new int[]{-1,1,0,-3,3})));
    }
}
