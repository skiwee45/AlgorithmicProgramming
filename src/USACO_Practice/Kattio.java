package USACO_Practice;

import java.io.*;
import java.util.StringTokenizer;

public class Kattio {
    private BufferedReader r;
    private StringTokenizer st;
    // standard input
    public Kattio() { this(System.in); }
    public Kattio(InputStream i) {
        r = new BufferedReader(new InputStreamReader(i));
    }
    // USACO-style file input
    public Kattio(String file) throws IOException {
        r = new BufferedReader(new FileReader(file));
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
    public int nextInt() { return Integer.parseInt(next()); }
    public double nextDouble() { return Double.parseDouble(next()); }
    public long nextLong() { return Long.parseLong(next()); }
}
