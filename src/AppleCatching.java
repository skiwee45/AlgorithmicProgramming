import java.io.*;
import java.util.*;

public class AppleCatching {
    public static void main(String[] args) {
        Kattio in = new Kattio();
        int n = in.nextInt();
        System.out.println(n);
        List<Apple> apples = new ArrayList<>();
        List<Cow> cows = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (in.nextInt() == 1) {
                cows.add(new Cow(in.nextInt(), in.nextInt(), in.nextInt()));
            } else {
                apples.add(new Apple(in.nextInt(), in.nextInt(), in.nextInt()));
            }
        }
        Collections.sort(apples);
        Collections.sort(cows);
        System.out.println(apples);
        System.out.println(cows);


        long totalPoints = 0;
        int iApple = 0;
        int iCow = 0;
        for(;;) {
            if (iCow >= cows.size()) {
                break;
            }
            Cow current = cows.get(iCow);
            while (iApple < apples.size() && !current.checkLower(apples.get(iApple))) { //cannot get it
                iApple++;
            }
            if (iApple >= apples.size()) {
                break;
            }
            Apple curApple = apples.get(iApple);
            if (current.checkUpper(curApple)) {
                int available = curApple.count;
                int canGet = current.count;
                if (canGet > available) { //leftover on this cow so don't move forward
                    totalPoints += available;
                    current.count -= available;
                    iApple++;
                } else {
                    totalPoints += canGet;
                    curApple.count -= canGet;
                    iCow++;
                }
            } else {
                iCow++;
            }
        }
        in.println(totalPoints);
        in.close();
    }

    static class Apple implements Comparable<Apple> {
        public int time;
        public int distance;
        public int sum;
        public int count;

        public Apple(int t, int d, int c) {
            time = t;
            distance = d;
            sum = time + distance;
            count = c;
        }

        @Override
        public int compareTo(Apple o) {
            return sum - o.sum;
        }

        @Override
        public String toString() {
            return "Apple{" +
                    "time=" + time +
                    ", distance=" + distance +
                    ", sum=" + sum +
                    ", count=" + count +
                    '}';
        }
    }

    static class Cow implements Comparable<Cow> {
        public int time;
        public int distance;
        public int sum;
        public int count;

        public Cow(int t, int d, int c) {
            time = t;
            distance = d;
            sum = time + distance;
            count = c;
        }

        public boolean checkLower(Apple a) {
            return checkLower(a.time, a.distance);
        }

        public boolean checkLower(int t, int d) {
            int deltaT = t - time;
            int distAtT = distance - deltaT;
            return distAtT <= d;
        }

        public boolean checkUpper(Apple a) {
            return checkUpper(a.time, a.distance);
        }

        public boolean checkUpper(int t, int d) {
            int deltaT = t - time;
            int distAtT = distance + deltaT;
            return distAtT >= d;
        }

        @Override
        public int compareTo(Cow o) {
            return sum - o.sum;
        }

        @Override
        public String toString() {
            return "Cow{" +
                    "time=" + time +
                    ", distance=" + distance +
                    ", sum=" + sum +
                    ", count=" + count +
                    '}';
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
