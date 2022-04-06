package USACO_Practice;

import java.io.*;
import java.util.*;

public class Triangles {
    public static final long MOD = (long) (10e9 + 7);

    static class XComparator implements Comparator<Point> {
        @Override
        public int compare(Point o1, Point o2) {
            return o1.x - o2.x;
        }
    }

    static class YComparator implements Comparator<Point> {
        @Override
        public int compare(Point o1, Point o2) {
            return o1.y - o2.y;
        }
    }

    public static void main(String[] args) throws IOException {
        Kattio in = new Kattio();
        int n = in.nextInt();
        Point[] points = new Point[n];
        HashMap<Integer, List<Point>> xMap = new HashMap<>();
        HashMap<Integer, List<Point>> yMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            points[i] = new Point(x, y);
            if(!xMap.containsKey(x)) {
                xMap.put(x, new ArrayList<>());
                //TODO: Must sort points by y
            }
            xMap.get(x).add(points[i]);

            if(!yMap.containsKey(y)) {
                yMap.put(y, new ArrayList<>());
            }
            yMap.get(y).add(points[i]);
        }

        //calculate x sums
        for(Map.Entry<Integer, List<Point>> entry : yMap.entrySet()){
            //get first x
            List<Point> parallelPoints = entry.getValue();
            parallelPoints.sort(new XComparator());
            Point first = parallelPoints.get(0);
            for(int i = 0; i < parallelPoints.size(); i++) {
                first.xSum += Math.abs(parallelPoints.get(i).x - first.x);
            }

            for(int i = 1; i < parallelPoints.size(); i++) {
                Point current = parallelPoints.get(i);
                int delta = Math.abs(current.x - parallelPoints.get(i - 1).x);
                current.xSum = parallelPoints.get(i - 1).xSum + delta * (i) - delta * (parallelPoints.size() - i);
            }
        }

        //calculate y sums
        for(Map.Entry<Integer, List<Point>> entry : xMap.entrySet()){
            //get first y
            List<Point> parallelPoints = entry.getValue();
            parallelPoints.sort(new YComparator());
            Point first = parallelPoints.get(0);
            for(int i = 0; i < parallelPoints.size(); i++) {
                first.ySum += Math.abs(parallelPoints.get(i).y - first.y);
            }

            for(int i = 1; i < parallelPoints.size(); i++) {
                Point current = parallelPoints.get(i);
                int delta = Math.abs(current.y - parallelPoints.get(i - 1).y);
                current.ySum = parallelPoints.get(i - 1).ySum + delta * (i) - delta * (parallelPoints.size() - i);
            }
        }

//        System.out.println(xMap);
//        System.out.println(yMap);
        System.out.println(Arrays.toString(points));

        int totalArea = 0;
        for (Point p : points) {
            totalArea += p.xSum * p.ySum % MOD;
            totalArea %= MOD;
        }
        in.println(totalArea);
        in.close();
    }

    static class Point{
        public int x;
        public int y;
        public int xSum;
        public int ySum;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            xSum = 0;
            ySum = 0;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", xSum=" + xSum +
                    ", ySum=" + ySum +
                    '}';
        }
    }

    static class Kattio extends PrintWriter {
        private BufferedReader r;
        private StringTokenizer st;
        // standard input
        public Kattio() { this(System.in, System.out); }
        public Kattio(InputStream i, OutputStream o) {
            super(o);
            r = new BufferedReader(new InputStreamReader(i));
        }
        // USACO-style file input
        public Kattio(String problemName) throws IOException {
            super(problemName + ".out");
            r = new BufferedReader(new FileReader(problemName + ".in"));
        }
        // returns null if no more input
        public String next() {
            try {
                while (st == null || !st.hasMoreTokens())
                    st = new StringTokenizer(r.readLine());
                return st.nextToken();
            } catch (Exception e) { }
            return null;
        }
        public void skipLine() {
            try {
                st = new StringTokenizer(r.readLine());
            } catch (Exception e) { }
        }
        public int nextInt() { return Integer.parseInt(next()); }
        public double nextDouble() { return Double.parseDouble(next()); }
        public long nextLong() { return Long.parseLong(next()); }
    }
}
