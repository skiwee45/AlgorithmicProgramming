package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdditionalRoadQueries1 {
    public static int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        int[] distFromStart = new int[n];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            distFromStart[i] = i;
            graph.add(new ArrayList<>());
            graph.get(i).add(i + 1);
        }
        graph.get(n - 1).remove(0); //no road at last

        int q = queries.length;
        int[] distances = new int[q];
        for (int i = 0; i < q; i++) {
            int roadStart = queries[i][0];
            int roadEnd = queries[i][1];
            graph.get(roadStart).add(roadEnd);
            updateDistances(graph, distFromStart, roadEnd, distFromStart[roadStart] + 1);
            distances[i] = distFromStart[n - 1];
        }

        return distances;
    }

    private static void updateDistances(List<List<Integer>> graph, int[] distFromStart, int curr, int currNewDist) {
        if (currNewDist < distFromStart[curr]) {
            distFromStart[curr] = currNewDist;
            for (var node:
                 graph.get(curr)) {
                updateDistances(graph, distFromStart, node, distFromStart[curr] + 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(shortestDistanceAfterQueries(5, new int[][]{{2, 4}, {0, 2}, {0, 4}})));
    }
}
