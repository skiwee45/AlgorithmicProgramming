package USACO_Silver_2022January;

import java.util.Arrays;
import java.util.Scanner;

public class Frisbee {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        int[] cows = new int[n];
//        for (int i = 0; i < n; i++) {
//            cows[i] = in.nextInt();
//        }
//
//        int totalDistance = 0;
//        for (int i = 0; i < n; i++) {
//            int max = 0;
//            int current = cows[i];
//            for (int j = i + 1; j < n; j++) {
//                if (current < max) break;
//                if (cows[j] <= max) continue;
//                max = cows[j];
//                totalDistance += j - i + 1;
//            }
//        }
//        System.out.print(totalDistance);
//    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] cows = new int[n];
        for (int i = 0; i < n; i++) {
            cows[i] = in.nextInt();
        }

        //calculate blockers
        //go right to left
        //blocker direction is left to right
        int[] rightBlockers = new int[n];
        rightBlockers[n - 1] = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            int j = i + 1;
            while(j < n) {
                if (cows[j] < cows[i]) {
                    j = rightBlockers[j];
                } else {
                    break;
                }
            }
            rightBlockers[i] = j;
        }
        System.out.println(Arrays.toString(rightBlockers));

        int totalDistance = 0;
        int largeObstacle = 0;
        int[] distanceSums = new int[n]; //no need for +1 since cow #1 has no distance
        for (int i = 0; i < n; i++) {
            int current = cows[i];
            if (i < largeObstacle) { //find the furthest this current one can see
                totalDistance += Math.max(distanceSums[rightBlockers[i]] - distanceSums[i], 2);
                continue;
            }

            int max = 0;
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (current < max) {
                    largeObstacle = maxIndex;
                    break;
                }
                if (cows[j] <= max) {
                    distanceSums[j] = distanceSums[j - 1];
                    continue;
                }
                max = cows[j];
                maxIndex = j;
                int dist = j - i + 1;
                distanceSums[j] = distanceSums[j - 1] + dist;
                totalDistance += dist;
            }
        }
        System.out.println(Arrays.toString(distanceSums));
        System.out.print(totalDistance);
    }
}
