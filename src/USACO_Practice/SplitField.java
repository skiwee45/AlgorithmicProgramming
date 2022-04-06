package USACO_Practice;

import java.io.*;
import java.util.*;

public class SplitField {
    static class XComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] o1, int[] o2) {
            return o1[0] - o2[0];
        }
    }

    static class YComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] o1, int[] o2) {
            return o1[1] - o2[1];
        }
    }


    public static void main(String[] args) throws IOException {
        Kattio in = new Kattio("split");
        int n = in.nextInt();
        int[][] cows = new int[n][2];
        for(int i = 0; i < n; i++) {
            cows[i][0] = in.nextInt();
            cows[i][1] = in.nextInt();
        }

        int[][] cowsXDir = cows.clone();
        Arrays.sort(cowsXDir, new XComparator());
        //System.out.println(Arrays.deepToString(cowsXDir));

        int[][] cowsYDir = cows.clone();
        Arrays.sort(cowsYDir, new YComparator());
        //System.out.println(Arrays.deepToString(cowsYDir));



        //find total area with just 1 fence
        int minX = cowsXDir[0][0];
        int maxX = cowsXDir[n - 1][0];
        int minY = cowsYDir[0][1];
        int maxY = cowsYDir[n - 1][1];
        long totalArea = (long)(maxX - minX) * (maxY - minY);

        long minArea = totalArea;

        //try x direction every combination
        int[] reverseYMaxStorage = new int[n]; //the maxes for each position going reverse
        int[] reverseYMinStorage = new int[n]; //the maxes for each position going reverse
        int YmaxStore = 0;
        int YminStore = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            int y = cowsXDir[n - i - 1][1];
            YmaxStore = Math.max(YmaxStore, y);
            reverseYMaxStorage[i] = YmaxStore;
            YminStore = Math.min(YminStore, y);
            reverseYMinStorage[i] = YminStore;
        }

        int Ymax = 0;
        int Ymin = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            //calculate in positive direction
            int minX1 = cowsXDir[0][0];
            int maxX1 = cowsXDir[i][0];
            int y = cowsXDir[i][1];
            Ymax = Math.max(Ymax, y);
            Ymin = Math.min(Ymin, y);
            long posArea = (long)(maxX1 - minX1) * (Ymax - Ymin);

            //calculate in negative direction
            long negArea;
            if(i + 1 < n) {
                int minX2 = cowsXDir[i + 1][0];
                int maxX2 = cowsXDir[n - 1][0];
                int minY2 = reverseYMinStorage[n - i - 2];
                int maxY2 = reverseYMaxStorage[n - i - 2];
                negArea = (long)(maxX2 - minX2) * (maxY2 - minY2);
            } else {
                negArea = 0;
            }

            //System.out.println(i + " " + posArea + " " + negArea);

            minArea = Math.min(minArea, posArea + negArea);
        }

        //try y direction
        int[] reverseXMaxStorage = new int[n]; //the maxes for each position going reverse
        int[] reverseXMinStorage = new int[n]; //the maxes for each position going reverse
        int XmaxStore = 0;
        int XminStore = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            int x = cowsYDir[n - i - 1][0];
            XmaxStore = Math.max(XmaxStore, x);
            reverseXMaxStorage[i] = XmaxStore;
            XminStore = Math.min(XminStore, x);
            reverseXMinStorage[i] = XminStore;
        }

        //System.out.println(Arrays.toString(reverseXMaxStorage));
        //System.out.println(Arrays.toString(reverseXMinStorage));

        int Xmax = 0;
        int Xmin = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            //calculate in positive direction
            int minY1 = cowsYDir[0][1];
            int maxY1 = cowsYDir[i][1];
            int x = cowsYDir[i][0];
            Xmax = Math.max(Xmax, x);
            Xmin = Math.min(Xmin, x);
            long posArea = (long)(maxY1 - minY1) * (Xmax - Xmin);

            //calculate in negative direction
            long negArea;
            if(i + 1 < n) {
                int minY2 = cowsYDir[i + 1][1];
                int maxY2 = cowsYDir[n - 1][1];
                int minX2 = reverseXMinStorage[n - i - 2];
                int maxX2 = reverseXMaxStorage[n - i - 2];
                negArea = (long)(maxX2 - minX2) * (maxY2 - minY2);
            } else {
                negArea = 0;
            }

            //System.out.println(i + " " + posArea + " " + negArea);

            minArea = Math.min(minArea, posArea + negArea);
        }


        //System.out.println(totalArea);
        in.print(totalArea - minArea);
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
