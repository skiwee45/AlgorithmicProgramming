package LeetCode_DynamicProgramming;

import java.util.Arrays;

public class HouseRobber {
    public int rob(int[] nums) {
        int[] maxMoneyAtHouse = new int[nums.length];
        Arrays.fill(maxMoneyAtHouse, Integer.MIN_VALUE);
        int start0 = robRecursive(nums, 0, maxMoneyAtHouse);
        int start1 = robRecursive(nums, 1, maxMoneyAtHouse);
        return Math.max(start0, start1);
    }

    private int robRecursive(int[] nums, int currentHouse, int[] maxMoneyAtHouse) {
        if (currentHouse >= nums.length)
            return 0;

        if (maxMoneyAtHouse[currentHouse] >= 0)
            return maxMoneyAtHouse[currentHouse];

        int money = nums[currentHouse];

        int maxMoneyJump2 = robRecursive(nums, currentHouse + 2, maxMoneyAtHouse);
        int maxMoneyJump3 = robRecursive(nums, currentHouse + 3, maxMoneyAtHouse);

        int maxMoney = money + Math.max(maxMoneyJump2, maxMoneyJump3);
        maxMoneyAtHouse[currentHouse] = maxMoney;
        return maxMoney;
    }
}
