package USACO_Practice;

import java.io.*;
import java.util.*;

public class MountainView {
    public static void main(String[] args) throws IOException {
        Kattio in = new Kattio("mountains");
        int n = in.nextInt();
        Mountain[] mountains = new Mountain[n];
        for (int i = 0; i < n; i++) {
            mountains[i] = new Mountain(in.nextInt(), in.nextInt());
        }
        Arrays.sort(mountains); //only mountains before can possibly cover mountains behind

        int distinguishable = 1;
        Mountain checkMountain = mountains[0];
        for (int i = 1; i < n; i++) {
            if(!mountains[i].isInside(checkMountain)) {
                distinguishable++;
                checkMountain = mountains[i];
            }
        }
        in.print(distinguishable);
        in.close();
    }

    static class Mountain implements Comparable<Mountain> {
        public int leftEdge, rightEdge;

        public Mountain(int center, int height) {
            leftEdge = center - height;
            rightEdge = center + height;
        }

        @Override
        public int compareTo(Mountain o) {
            int leftCompare = leftEdge - o.leftEdge;
            if(leftCompare == 0) {
                return o.rightEdge - rightEdge;
            }
            return leftCompare;
        }

        public boolean isInside(Mountain check) {
            return leftEdge >= check.leftEdge && rightEdge <= check.rightEdge;
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
