package USACO_Silver_2022March;

import java.io.*;
import java.util.*;

public class COWOperations {
    public static void main(String[] args) {
        Kattio in = new Kattio();
        char[] str = in.next().toCharArray();
        int n = str.length;
        int[] cSums = new int[str.length + 1];
        int[] oSums = new int[str.length + 1];
        int[] wSums = new int[str.length + 1];

        for (int i = 0; i < n; i++) {
            cSums[i + 1] = cSums[i];
            oSums[i + 1] = oSums[i];
            wSums[i + 1] = wSums[i];

            if (str[i] == 'C') {
                cSums[i + 1]++;
            } else if (str[i] == 'O') {
                oSums[i + 1]++;
            } else if (str[i] == 'W') {
                wSums[i + 1]++;
            }
        }
        //System.out.println(Arrays.toString(cSums));
        //System.out.println(Arrays.toString(oSums));
        //System.out.println(Arrays.toString(wSums));

        int q = in.nextInt();
        for (int i = 0; i < q; i++) {
            int start = in.nextInt() - 1;
            int end = in.nextInt() - 1;
            int cLeft = (cSums[end + 1] - cSums[start]) % 2;
            int oLeft = (oSums[end + 1] - oSums[start]) % 2;
            int wLeft = (wSums[end + 1] - wSums[start]) % 2;
            //System.out.println(cLeft + " " + oLeft + " " + wLeft);
            if (cLeft == 1) {
                if (oLeft == 0 && wLeft == 0) {
                    in.print("Y");
                    continue;
                }
            } else {
                if (oLeft + wLeft == 2) {
                    in.print("Y");
                    continue;
                }
            }

            in.print("N");
        }
        in.close();
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
