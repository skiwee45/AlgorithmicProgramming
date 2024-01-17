package LeetCode;

public class JumpGame {
    public boolean canJump(int[] nums) {
        // at each iteration store max jump
        int maxJump = 0;
        int i;
        for (i = 0; i < nums.length - 1; i++) {
            if (nums[i] > maxJump)
                maxJump = nums[i];
            if (maxJump <= 0)
                break;
            maxJump--;
        }
        return i == nums.length - 1;
    }
}
