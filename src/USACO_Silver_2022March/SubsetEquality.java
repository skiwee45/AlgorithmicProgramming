package USACO_Silver_2022March;

import java.io.*;
import java.util.*;

public class SubsetEquality {
    public static void main(String[] args) {
        //check each pair of letters
        Kattio in = new Kattio();
        char[] s = in.next().toCharArray();
        int sN = s.length;
        char[] t = in.next().toCharArray();
        int tN = t.length;
        boolean[][] pairChecks = new boolean[18][18];
        for (int i = 0; i < 18; i++) {
            for (int j = i; j < 18; j++) {
                pairChecks[i][j] = true;
                int sI = 0, tI = 0;
                for(;;) {
                    while(sI < sN && (s[sI] - 'a' != i && s[sI] - 'a' != j)) {
                        sI++;
                    }
                    while(tI < tN && (t[tI] - 'a' != i && t[tI] - 'a' != j)) {
                        tI++;
                    }
                    if(sI >= sN && tI >= tN) break;
                    if(sI >= sN || tI >= tN) {
                        pairChecks[i][j] = false;
                        break;
                    }
                    if(s[sI] != t[tI]) {
                        pairChecks[i][j] = false;
                        break;
                    }
                    sI++;
                    tI++;
                }
            }
        }

        //System.out.println(Arrays.deepToString(pairChecks));

        int q = in.nextInt();
        for (int i = 0; i < q; i++) {
            char[] query = in.next().toCharArray();
            boolean yes = true;
            outer: for (int j = 0; j < query.length; j++) {
                for (int k = j; k < query.length; k++) {
                    if (!pairChecks[query[j] - 'a'][query[k] - 'a']) {
                        yes = false;
                        break outer;
                    }
                }
            }
            if(yes) {
                in.print("Y");
            } else {
                in.print("N");
            }
        }
        in.close();

//        int q = in.nextInt();
//        String[] queries = new String[q];
//        for (int i = 0; i < q; i++) {
//            queries[i] = in.next();
//        }
//        for (int i = 0; i < q; i++) {
//            HashSet<Character> checker = new HashSet<>();
//            for (char c : queries[i].toCharArray()) {
//                checker.add(c);
//            }
//
//            StringBuilder sB = new StringBuilder();
//            for (char c : s) {
//                if(checker.contains(c)) {
//                    sB.append(c);
//                }
//            }
//
//            StringBuilder tB = new StringBuilder();
//            for (char c : t) {
//                if(checker.contains(c)) {
//                    tB.append(c);
//                }
//            }
//
//            if(sB.toString().equals(tB.toString())) {
//                in.print("Y");
//            } else {
//                in.print("N");
//            }
//        }
//        in.close();
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
