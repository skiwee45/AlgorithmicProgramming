package USACO_Silver_2022February;

import java.io.*;
import java.util.*;

public class RobotInstructions {

    public static void main(String[] args) {
        //loops (some paths go back to the same spot) this can be toggled on and off
        //find all the ways to get there, don't go by each number

        //just brute force, order does not matter, so using 1 and 2 is same as 2 and 1
        //after something is used, never use it again
        Kattio in = new Kattio();
        int n = in.nextInt();
        Vector2 target = new Vector2(in.nextInt(), in.nextInt());
        Vector2[] choices1 = new Vector2[n / 2];
        for(int i = 0; i < n / 2; i++) {
            choices1[i] = new Vector2(in.nextInt(), in.nextInt());
        }

        Vector2[] choices2 = new Vector2[n - n / 2];
        for(int i = 0; i < n - n / 2; i++) {
            choices2[i] = new Vector2(in.nextInt(), in.nextInt());
        }

        //make side1
        List<Path> side1 = new ArrayList<>((int)Math.pow(2, choices1.length) - 1);
        side1.add(new Path(new Vector2(0, 0), 0));

        for(int i = 0; i < n / 2; i++) {
            findPaths1(i, new Vector2(0, 0), 1, choices1, side1);
        }
        //System.out.println(side1);

        //make side2
        HashMap<Vector2, List<Integer>> side2 = new HashMap<>(); //key is ending pos, value is list of all # of instructions to get there
        side2.put(new Vector2(0, 0), new ArrayList<>());
        side2.get(new Vector2(0, 0)).add(0);
        for(int i = 0; i < n - n / 2; i++) {
            findPaths2(i, new Vector2(0, 0), 1, choices2, side2);
        }
        //System.out.println(side2);

        int[] numWays = new int[n];
        //match combos
        for(int i = 0; i < side1.size(); i++) {
            Vector2 current = side1.get(i).pos;
            Vector2 diff = new Vector2(target.x - current.x, target.y - current.y);
            if(side2.containsKey(diff)) {
                int num1 = side1.get(i).instructions;
                for(int num2 : side2.get(diff)) {
                    numWays[num1 + num2 - 1]++;
                }
            }
        }

        for(int i = 0; i < n; i++) {
            in.println(numWays[i]);
        }

        in.close();
    }

    static void findPaths1(int lastChoice, Vector2 current, int numInstructions, Vector2[] choices, List<Path> side) {
        Vector2 newCur = new Vector2();
        newCur.x = current.x + choices[lastChoice].x;
        newCur.y = current.y + choices[lastChoice].y;
        side.add(new Path(newCur, numInstructions));
        for(int i = lastChoice + 1; i < choices.length; i++) {
            findPaths1(i, newCur, numInstructions + 1, choices, side);
        }
    }

    static void findPaths2(int lastChoice, Vector2 current, int numInstructions, Vector2[] choices, HashMap<Vector2, List<Integer>> side) {
        Vector2 newCur = new Vector2();
        newCur.x = current.x + choices[lastChoice].x;
        newCur.y = current.y + choices[lastChoice].y;
        if(!side.containsKey(newCur)) {
            side.put(newCur, new ArrayList<>());
        }
        side.get(newCur).add(numInstructions);
        for(int i = lastChoice + 1; i < choices.length; i++) {
            findPaths2(i, newCur, numInstructions + 1, choices, side);
        }
    }

    static class Vector2 {
        public int x;
        public int y;

        public Vector2() {
            this(0, 0);
        }

        public Vector2(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vector2 vector2 = (Vector2) o;
            return x == vector2.x && y == vector2.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "<" + x + ", " + y + ">";
        }
    }

    static class Path {
        public Vector2 pos;
        public int instructions;

        public Path(Vector2 pos, int instructions) {
            this.pos = pos;
            this.instructions = instructions;
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
