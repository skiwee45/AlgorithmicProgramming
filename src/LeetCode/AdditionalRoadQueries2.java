package LeetCode;

import java.util.Arrays;
import java.util.HashSet;

public class AdditionalRoadQueries2 {
    public static int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        int shortestPath = n - 1;
        int[] furthestReach = new int[n];
        HashSet<Integer> uselessCities = new HashSet<>(); // cities in between path endpoints (not including the ends)

        for (int i = 0; i < n; i++) {
            furthestReach[i] = i + 1;
        }

        int q = queries.length;
        int[] distances = new int[q];
        for (int i = 0; i < q; i++) {
            int start = queries[i][0];
            int end = queries[i][1];
            if (uselessCities.contains(start) || uselessCities.contains(end)) {
                distances[i] = shortestPath;
                continue;
            }

            // definitely useful
            for (int j = start + 1; j < end; j++) {
                if (uselessCities.contains(j)) {
                    j = furthestReach[j - 1];
                    j--;
                    continue;
                }
                uselessCities.add(j);
                shortestPath--;
            }
            furthestReach[start] = end;
            distances[i] = shortestPath;
        }

        return distances;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(shortestDistanceAfterQueries(5, new int[][]{{2, 4}, {0, 2}, {0, 4}})));
    }
}
