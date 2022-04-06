package USACO_Practice;

import java.io.*;
import java.util.*;

public class US_ConnectingTwoBarns {
    private static boolean[] travelled;

    public static void main(String[] args) {
        Kattio in = new Kattio();
        int numCases = in.nextInt();
        for(;;) {
            numCases--;

            //solve
            int n = in.nextInt();
            int p = in.nextInt();
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }
            int[] points = new int[p * 2];
            for (int i = 0; i < p; i++) {
                int start = in.nextInt() - 1;
                int end = in.nextInt() - 1;
                graph.get(start).add(end);
                graph.get(end).add(start);
                points[i * 2] = start;
                points[i * 2 + 1] = end;
            }
            System.out.println(graph);

            //create chunks
            travelled = new boolean[n];
            List<Integer> startChunk = createChunk(0, graph);
            List<Integer> endChunk = createChunk(n - 1, graph);
            List<List<Integer>> middleChunks = new ArrayList<>();
            for(int i = 0; i < p; i++) {
                List<Integer> temp = createChunk(points[i], graph);
                if(temp.size() > 1) {
                    middleChunks.add(temp);
                }
            }
            System.out.println(startChunk);
            System.out.println(endChunk);
            System.out.println(middleChunks);

            //process between start and end
            int[] indexPair = findClosestIndexPair(startChunk, endChunk);
            System.out.println(Arrays.toString(indexPair));
            int midPoint = startChunk.get(indexPair[0]) + getDistance(startChunk.get(indexPair[0]), endChunk.get(indexPair[1])) / 2;
            int minCost = (int) (Math.pow(midPoint - startChunk.get(indexPair[0]), 2) + Math.pow(endChunk.get(indexPair[1]) - midPoint, 2));

            //process all middle chunks
            for (List<Integer> chunk : middleChunks) {
                int[] firstToMidPair = findClosestIndexPair(startChunk, chunk);
                int firstToMidCost = (int) Math.pow(firstToMidPair[2], 2);
                //System.out.println(Arrays.toString(firstToMidPair));

                int[] midToEndPair = findClosestIndexPair(chunk, endChunk);
                int midToEndCost = (int) Math.pow(midToEndPair[2], 2);
                //System.out.println(Arrays.toString(midToEndPair));

                int totalCost = firstToMidCost + midToEndCost;
                minCost = Math.min(minCost, totalCost);
            }

            if(numCases > 0) {
                in.println(minCost);
            } else {
                in.print(minCost);
                in.close();
                break;
            }
        }
    }

    private static int[] findClosestIndexPair(List<Integer> l1, List<Integer> l2) {
        int[] output = new int[3];
        output[2] = Integer.MAX_VALUE;
        int j = 0; //index of largest left (of i) element in l2
        for(int i =  0; i < l1.size(); i++) {
            int e = l1.get(i);
            while(j + 1 < l2.size() && l2.get(j + 1) < e) { //TODO: problem line when there is only 1 value in l2
                j++;
            }
            if (j + 1 >= l2.size()) {
                int distance = getDistance(l2.get(j), e);
                if (distance < output[2]) {
                    output[1] = j;
                    output[2] = distance;
                    output[0] = i;
                }
                break;
            }
            int[] temp = findClosestIndex(new int[] {j, j + 1}, e, l2);
            if (temp[1] < output[2]) {
                output[1] = temp[0];
                output[2] = temp[1];
                output[0] = i;
            }
        }
        return output;
    }

    //returns best index and the distance of that index
    private static int[] findClosestIndex(int[] indices, int targetPos, List<Integer> list) {
        int[] output = new int[2];
        output[1] = getDistance(list.get(indices[0]), targetPos);
        for(int i = 1; i < indices.length; i++) {
            output = findClosestIndex(output[0], indices[i], targetPos, list);
        }
        return output;
    }

    private static int[] findClosestIndex(int a, int b, int targetPos, List<Integer> list) {
        int aDist = getDistance(list.get(a), targetPos);
        int bDist = getDistance(list.get(b), targetPos);
        if (aDist < bDist) {
            return new int[] {a, aDist};
        }
        return new int[] {b, bDist};
    }

    private static int getDistance(int a, int b) {
        return Math.abs(a - b);
    }

    //storage: list of all nodes, 0 = min cost using 1, 1 = min cost using 2
    private static List<Integer> createChunk(int start, List<List<Integer>> graph) {
        travelled[start] = true;
        List<Integer> ends = graph.get(start);
        List<Integer> out = new ArrayList<>();
        out.add(start);
        for (int e : ends) {
            if(travelled[e]) continue;
            out.addAll(createChunk(e, graph));
        }
        out.sort(null);
        return out;
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
        public int nextInt() { return Integer.parseInt(next()); }
        public double nextDouble() { return Double.parseDouble(next()); }
        public long nextLong() { return Long.parseLong(next()); }
    }
}