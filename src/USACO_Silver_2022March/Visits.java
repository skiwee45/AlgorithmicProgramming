package USACO_Silver_2022March;

import java.io.*;
import java.util.*;

public class Visits {

    private static int[] findTravelled; //0 = not travelled, anything else is index of which it was travelled
    private static boolean[] travelled;
    private static List<Integer> loops = new ArrayList<>();

    public static void main(String[] args) {
        Kattio in = new Kattio();
        int n = in.nextInt();
        Edge[] graph = new Edge[n];
        for (int i = 0; i < n; i++) {
            Edge e = new Edge(i, in.nextInt() - 1, in.nextInt());
            graph[i] = e;
        }

        //first find loops
        findTravelled = new int[n];
        for (int i = 0; i < n; i++) {
            findLoops(graph, i, i + 1);
        }
        //System.out.println(loops);

        travelled = new boolean[n];
        long totalPoints = 0;
        for (int i = 0; i < loops.size(); i++) {
            int starter = loops.get(i);
            totalPoints += getPoints(graph, starter, 0, Integer.MAX_VALUE);
        }

        for (int i = 0; i < n; i++) {
            if (travelled[i]) continue;
            totalPoints += graph[i].points;
        }
        in.println(totalPoints);
        in.close();
    }

    private static void findLoops(Edge[] graph, int current, int start) {
        if (findTravelled[current] == start) {
            loops.add(current);
            return;
        }
        if (findTravelled[current] > 0) {
            return;
        }
        findTravelled[current] = start;
        findLoops(graph, graph[current].to, start);
    }

    private static long getPoints(Edge[] graph, int start, long pointSum, int minSoFar) {
        if (travelled[start]) {
            return pointSum - minSoFar;
        }
        Edge current = graph[start];
        pointSum += current.points;
        minSoFar = Math.min(minSoFar, current.points);
        travelled[start] = true;
        return getPoints(graph, current.to, pointSum, minSoFar);
    }

    static class Edge {
        public int from;
        public int to;
        public int points;

        public Edge(int f, int t, int p) {
            from = f;
            to = t;
            points = p;
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
