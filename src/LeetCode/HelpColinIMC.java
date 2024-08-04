package LeetCode;

import java.util.Arrays;
import java.util.List;

public class HelpColinIMC {
    public static void main(String[] args) {
        String[] grid = new String[5];
        grid[0] = "-x--";
        grid[1] = "-xx-";
        grid[2] = "----";
        grid[3] = "-x-x";
        grid[4] = "-xx-";
        System.out.println(exploreGrid(grid, 3, 0, 0, 2));
    }

    public static int exploreGrid(List<String> grid, int row_start, int column_start, int row_end, int column_end) {
        return exploreGrid(grid.toArray(new String[0]), row_start, column_start, row_end, column_end);
    }
    //map traversal
    //there are obstacles
    //moving down or right is free, up or left costs 1
    //returns min cost from start to end
    //return -1 if not possible
    //free spaces in the grid are '-' and obstacles are 'x'
    private static int exploreGrid(String[] grid, int row_start, int column_start, int row_end, int column_end) {
        //create graph
        int cols = grid[0].length();
        int rows = grid.length;
        Graph graph = new Graph(cols * rows);
        var map = convert(grid);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!check(map, i, j)) continue;

                if (check(map, i, j + 1)) {
                    graph.addEdge(i * cols + j, i*cols+j+1, 0);
                }
                if (check(map, i, j - 1)) {
                    graph.addEdge(i * cols + j, i*cols+j-1, 1);
                }
                if (check(map, i + 1, j)) {
                    graph.addEdge(i * cols + j, (i+1)*cols+j, 0);
                }
                if (check(map, i - 1, j)) {
                    graph.addEdge(i * cols + j, (i-1)*cols+j, 1);
                }
            }
        }

        graph.setStart(row_start * cols + column_start);
        graph.setEnd(row_end * cols + column_end);
        //run dijkstra
        int result = graph.dijkstra();
        if (result == Integer.MAX_VALUE) result = -1;
        return result;
    }

    private static boolean check(char[][] map, int row, int col) {
        try {
            return map[row][col] == '-';
        } catch (Exception e) {
            return false;
        }
    }

    private static char[][] convert(String[] grid) {
        int cols = grid[0].length();
        int rows = grid.length;
        char[][] chars = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                chars[i][j] = grid[i].charAt(j);
            }
        }
        return chars;
    }


    static class Graph {
        private int[][] adjMatrix;
        private int size;
        private int startVertex;
        private int endVertex;

        public Graph(int size) {
            this.size = size;
            this.adjMatrix = new int[size][size];
            for (int[] matrix : adjMatrix) {
                Arrays.fill(matrix, -1);
            }
        }

        public void addEdge(int u, int v, int weight) {
            adjMatrix[u][v] = weight;
        }

        public void setStart(int vertex) {
            startVertex = vertex;
        }

        public void setEnd(int vertex) {endVertex = vertex;}

        public int dijkstra() {
            int[] distances = new int[size];
            Arrays.fill(distances, Integer.MAX_VALUE);
            distances[startVertex] = 0;
            boolean[] visited = new boolean[size];

            for (int count = 0; count < size - 1; count++) {
                int u = minDistance(distances, visited);

                if (u == -1) {
                    break;
                }

                if (u == endVertex) {
                    break;
                }

                visited[u] = true;

                for (int v = 0; v < size; v++) {
                    if (!visited[v] && adjMatrix[u][v] != -1 && distances[u] != Integer.MAX_VALUE && distances[u] + adjMatrix[u][v] < distances[v]) {
                        distances[v] = distances[u] + adjMatrix[u][v];
                    }
                }
            }

            return distances[endVertex];
        }

        private int minDistance(int[] distances, boolean[] visited) {
            int min = Integer.MAX_VALUE, minIndex = -1;

            for (int v = 0; v < size; v++) {
                if (!visited[v] && distances[v] <= min) {
                    min = distances[v];
                    minIndex = v;
                }
            }

            return minIndex;
        }
    }
}
