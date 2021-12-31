package ACC_ProblemOfTheWeek;

import java.util.Arrays;
import java.util.Scanner;

public class p4_SmallestContainingCircle {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] points = new int[n][2];
        for (int i = 0; i < n; i++) {
            points[i][0] = in.nextInt();
            points[i][1] = in.nextInt();
        }

        System.out.print(Math.round(solve(points, points.length, new int[3][2], 0)[2]));
    }

    private static double[] solve(int[][] points, int pLength, int[][] triangle, int triLength) {
        if(pLength == 0 || triLength == 3) {
            return triangleSolve(triangle, triLength);
        }
        double[] circle = solve(points, pLength - 1, triangle, triLength);
        if(distance(points[pLength - 1], new double[] {circle[0], circle[1]}) <= circle[2]) {
            return circle;
        }

        triangle[triLength] = points[pLength - 1];
        return solve(points, pLength - 1, triangle, triLength + 1);
    }

    private static double[] triangleSolve(int[][] triangle, int n) {
        if(n <= 1)
            return new double[] {0, 0, 0};
        if(n == 2) {
            double[] mp = midPoint(triangle[0], triangle[1]);
            return new double[] {mp[0], mp[1], distance(triangle[0], triangle[1]) / 2};
        }

        //get bisector 1
        double m1 = (triangle[0][1] - triangle[1][1]) / (double) (triangle[0][0] - triangle[1][0]);

        double mB1 = -1 / m1;
        double bB1 = (triangle[0][1] + triangle[1][1]) / 2.0 - m1 * (triangle[0][0] + triangle[1][0]) / 2;

        //get bisector 2
        double m2 = (triangle[1][1] - triangle[2][1]) / (double) (triangle[1][0] - triangle[2][0]);

        double mB2 = -1 / m2;
        double bB2 = (triangle[1][1] + triangle[2][1]) / 2.0 - m1 * (triangle[1][0] + triangle[2][0]) / 2;

        double[] center = intersect(mB1, bB1, mB2, bB2);
        return new double[] {center[0], center[1], distance(center, Arrays.stream(triangle[0]).asDoubleStream().toArray())};
    }

    private static double distance(double[] a, double[] b) {
        return Math.abs(Math.sqrt(Math.pow(b[0] - a[0], 2) + Math.pow(b[1] - a[1], 2)));
    }

    private static double distance(int[] a, int[] b) {
        return distance(Arrays.stream(a).asDoubleStream().toArray(), Arrays.stream(b).asDoubleStream().toArray());
    }

    private static double distance(int[] a, double[] b) {
        return distance(Arrays.stream(a).asDoubleStream().toArray(), b);
    }

    private static double[] midPoint(double[] a, double[] b) {
        return new double[] {(a[0] + b[0]) / 2.0, (a[1] + b[1]) / 2.0};
    }

    private static double[] midPoint(int[] a, int[] b) {
        return midPoint(Arrays.stream(a).asDoubleStream().toArray(), Arrays.stream(b).asDoubleStream().toArray());
    }

    private static double[] intersect(double m1, double b1, double m2, double b2) {
        double x = (b2 - b1) / (m1 - m2);
        double y = x * m1 + b1;
        return new double[] {x, y};
    }

    private class Path {
        public boolean satisfied = false;
        public int[] nodes;
        public Path(int[] nodes) {
            this.nodes = nodes;
        }

        public Path(Path path, int originNode) {
            nodes = new int[path.nodes.length];
            nodes[0] = originNode;
            for(int i = 1; i < nodes.length; i++) {
                nodes[i] = path.nodes[i];
            }
        }

        @Override
        public String toString() {
            return "Path{" +
                    "satisfied=" + satisfied +
                    ", nodes=" + Arrays.toString(nodes) +
                    '}';
        }
    }

}
