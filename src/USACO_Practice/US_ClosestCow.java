package USACO_Practice;

import java.io.*;
import java.util.*;

//http://www.usaco.org/index.php?page=viewproblem2&cpid=1158
public class US_ClosestCow {
    public static void main(String[] args) throws IOException {
        Kattio in = new Kattio();
//        Kattio in = new Kattio("src/USACO_Practice/US_ClosestCowTesters/2");
        int k = in.nextInt(); //num patches
        int m = in.nextInt(); //num enemy cows
        int n = in.nextInt(); //num our cows

        TreeMap<Double, Integer> patches = new TreeMap<>(); //patch position, storage index (input order)
        Patch[] patchStorage = new Patch[k];
        for (int i = 0; i < k; i++) {
            int pos = in.nextInt();
            int points = in.nextInt();
            patchStorage[i] = new Patch(pos, points);
            patches.put((double)pos, i);
        }

        Iterator<Map.Entry<Double, Integer>> it = patches.entrySet().iterator(); //change value to sorted order
        Patch[] patchArr = new Patch[k]; //sorted
        long[] pointSums = new long[k + 1];
        int index = 0;
        while (it.hasNext()) { //sort somehow the patchArr
            Map.Entry<Double, Integer> entry = it.next();
            patchArr[index] = patchStorage[entry.getValue()];
            pointSums[index + 1] = pointSums[index] + patchArr[index].points;
            entry.setValue(index);
            index++;
        }

        int[] enemyArr = new int[m];
        for (int i = 0; i < m; i++) {
            int input = in.nextInt();
            enemyArr[i] = input;
        }
        Arrays.sort(enemyArr);

//        System.out.println(patches);
//        System.out.println(Arrays.toString(patchArr));

        long[] allNums = new long[2 * (m + 1)];
        //calc front
        Map.Entry<Double, Integer> frontEntry = patches.lowerEntry((double)enemyArr[0]);
        int frontIndex = -1;
        if (frontEntry != null) {
            frontIndex = frontEntry.getValue();
            allNums[0] = pointSums[frontIndex + 1] - pointSums[0];
            allNums[1] = 0;
        }
        //calc back
        Map.Entry<Double, Integer> backEntry = patches.higherEntry((double)enemyArr[enemyArr.length - 1]);
        int backIndex = k;
        if (backEntry != null) {
            backIndex = backEntry.getValue();
            allNums[2 * m] = pointSums[pointSums.length - 1] - pointSums[backIndex];
            allNums[2 * m + 1] = 0;
        }

        int leftEnemyIndex = 0;
        for (int i = frontIndex + 1; i < backIndex; i++) {
            Patch patch = patchArr[i];
            while (enemyArr[leftEnemyIndex + 1] < patch.position) {
                leftEnemyIndex++;
            }
            if (enemyArr[leftEnemyIndex] == patch.position) continue;

            int leftEnemy = enemyArr[leftEnemyIndex];
            int rightEnemy = enemyArr[leftEnemyIndex + 1];
            //window starting point is the position of cow i - 0.5
            double cowPos = leftEnemy + (patchArr[i].position - leftEnemy) * 2 - 0.5;
            double endPoint = cowPos < rightEnemy ? cowPos + (rightEnemy - cowPos) / 2.0 : rightEnemy;
            int rightMostPatchIndex = patches.lowerEntry(endPoint).getValue();
            long points = pointSums[rightMostPatchIndex + 1] - pointSums[i];
            allNums[(leftEnemyIndex + 1) * 2] = Math.max(allNums[(leftEnemyIndex + 1) * 2], points);
            if (rightMostPatchIndex == i) {
                int leftBorderPatchIndex = patches.higherEntry((double)enemyArr[leftEnemyIndex]).getValue();
                int rightBorderPatchIndex = patches.lowerEntry((double)enemyArr[leftEnemyIndex + 1]).getValue();
                long sectionTotal = pointSums[rightBorderPatchIndex + 1] - pointSums[leftBorderPatchIndex];
                allNums[(leftEnemyIndex + 1) * 2 + 1] = sectionTotal - allNums[(leftEnemyIndex + 1) * 2];
            }
//            System.out.println("Left Enemy: " + leftEnemy + "  Right Enemy: " + rightEnemy + "  CowPos: " + cowPos + "  EndPoint: " + endPoint + "  RightMost Patch: " + rightMostPatchIndex + "  Points: " +  points);
        }

//        System.out.print(Arrays.toString(allNums));
        Arrays.sort(allNums);
        long out = 0;
        for (int i = allNums.length - 1; i >= allNums.length - n; i--) {
            out += allNums[i];
        }
//        System.out.print(Arrays.toString(allNums));
        System.out.print(out);
        in.close();
    }

    static class Patch implements Comparable<Patch> {
        public int position;
        public int points;
        public int maxDistance;

        public Patch(int pos, int points) {
            this.position = pos;
            this.points = points;
        }

        public String toString() {
            return position + " " + points + " " + maxDistance;
        }


        @Override
        public int compareTo(Patch o) {
            return position - o.position;
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
        public int nextInt() { return Integer.parseInt(next()); }
        public double nextDouble() { return Double.parseDouble(next()); }
        public long nextLong() { return Long.parseLong(next()); }
    }
}
