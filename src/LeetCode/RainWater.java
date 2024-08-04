package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RainWater {
    public int trap(int[] height) {
        //track maximums one way and prefix sum
        //then go the other way
        //make sure to track when 2 peaks are equal to max too
        //but when going the other way, don't track equal peaks
        int n = height.length;

        //create prefix sum
        int[] prefixSum = new int[n];
        prefixSum[0] = height[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + height[i];
        }
        System.out.println(Arrays.toString(prefixSum));


        //go left to right
        List<Integer> maxIndexes = new ArrayList<>();
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (height[i] > 0 && height[i] >= max) {
                max = height[i];
                maxIndexes.add(i);
            }
        }

        System.out.println(maxIndexes);

        //calculate answer

        return getWater(height, maxIndexes, prefixSum);
    }

    private static int getWater(int[] height, List<Integer> maxIndexes, int[] prefixSum) {
        int water = 0;
        for (int i = 0; i < maxIndexes.size() - 1; i++) {
            int leftIndex = maxIndexes.get(i);
            int rightIndex = maxIndexes.get(i + 1);
            //we know leftHeight is <= to rightHeight, so no need for rightHeight
            int leftHeight = height[leftIndex];
            int blocksInBetween = prefixSum[rightIndex - 1] - prefixSum[leftIndex];
            int areaInBetween = (rightIndex - leftIndex - 1) * leftHeight;
            int waterInBetween = areaInBetween - blocksInBetween;
            water += waterInBetween;
        }
        return water;
    }
}
