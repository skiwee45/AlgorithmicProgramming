package LeetCode;

public class HouseRobber_Tabulation {
    public int rob(int[] nums) {
        return robTabulation(nums);
    }

    private int robTabulation(int[] houses) {
        // imagine the robber traveling right to left (backwards) so that the first house is the last to be robbed

        int n = houses.length;

        int[] maxMoneyAtHouse = new int[n];
        for (int i = 0; i < n; i++) {
            // each house can get max from 2 or 3 houses before it
            maxMoneyAtHouse[i] =
                    Math.max(
                        findMaxMoneyAtHouse(maxMoneyAtHouse, i - 2),
                        findMaxMoneyAtHouse(maxMoneyAtHouse, i - 3))
                    + houses[i];
        }

        // the robber can either start at house n-1 or n-2, so max them
        return Math.max(
                findMaxMoneyAtHouse(maxMoneyAtHouse, n - 1),
                findMaxMoneyAtHouse(maxMoneyAtHouse, n - 2));
    }

    private int findMaxMoneyAtHouse(int[] maxMoneyAtHouse, int currentHouse) {
        try {
            return maxMoneyAtHouse[currentHouse];
        } catch (Exception e) {
            return 0;
        }
    }
}
