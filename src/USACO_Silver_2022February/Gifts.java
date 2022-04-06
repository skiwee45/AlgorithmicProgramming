package USACO_Silver_2022February;

import java.util.*;
import java.io.*;

public class Gifts {
    public static void main(String[] args) {
        //graph
        //each node is a cow
        //directed edges going to each cow that has a gift that the cow wants more than its own
        //loops in the graph allow for gifts to be exchanged
        //find all loops in order of most preferred to less preferred (starting edge)
        Kattio in = new Kattio();
        int n = in.nextInt();
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
            List<Integer> list = graph.get(i);
            boolean add = true;
            for(int j = 0; j < n; j++) {
                int gift = in.nextInt() - 1;
                if(gift == i) add = false;
                if (add) {
                    list.add(gift);
                }
            }
        }

        //look for cycles
        for(int i = 0; i < n; i++) {
            boolean[] travelled = new boolean[n];
            int bestGift = i;
            List<Integer> list = graph.get(i);
            for(int j = 0; j < list.size(); j++) {
                int jPos = list.get(j);
                if (findWayBack(jPos, i, graph, travelled)) {
                    bestGift = jPos;
                    break;
                }
            }
            in.println(bestGift + 1);
        }
        in.close();
    }

    static boolean findWayBack(int current, int target, List<List<Integer>> graph, boolean[] travelled) {
        if(current == target) return true;
        if(travelled[current]) return false;
        travelled[current] = true;
        List<Integer> list = graph.get(current);
        for(int i = 0; i < list.size(); i++) {
            if (findWayBack(list.get(i), target, graph, travelled)) {
                return true;
            }
        }
        return false;
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
