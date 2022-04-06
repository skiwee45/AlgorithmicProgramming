package USACO_Practice;

import java.io.*;
import java.util.*;

public class RentalService {
    static class LargestFirstComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    public static void main(String[] args) throws IOException {
        Kattio in = new Kattio("src/rental");
        int cowNum = in.nextInt();
        int shopNum = in.nextInt();
        int rentNum = in.nextInt();
        Integer[] cows = new Integer[cowNum];
        for (int i = 0; i < cowNum; i++) {
            cows[i] = in.nextInt();
        }
        Arrays.sort(cows, new LargestFirstComparator());

        Shop[] shops = new Shop[shopNum];
        for (int i = 0; i < shopNum; i++) {
            Shop shop = new Shop(in.nextInt(), in.nextInt());
            shops[i] = shop;
        }
        Arrays.sort(shops); //best to worst

        Integer[] rents = new Integer[rentNum];
        for (int i = 0; i < rentNum; i++) {
            rents[i] = in.nextInt();
        }
        Arrays.sort(rents, new LargestFirstComparator());

        int lastShop = 0;
        int gallonsLeft = shops[0].gallons;
        long[] shopLUT = new long[cowNum + 1];
        for(int i = 0; i < cowNum; i++) {
            shopLUT[i + 1] = shopLUT[i];
            if(lastShop >= shopNum) {
                continue;
            }
            int milk = cows[i];
            if(gallonsLeft > milk) {
                gallonsLeft -= milk;
                shopLUT[i + 1] += milk * shops[lastShop].price;
                continue;
            }
            for(;;) {
                int origMilk = milk;
                milk -= gallonsLeft;
                if(milk < 0) {
                    shopLUT[i + 1] += (long) origMilk * shops[lastShop].price;
                    gallonsLeft = -milk;
                    break;
                }
                shopLUT[i + 1] += (long) gallonsLeft * shops[lastShop].price;
                lastShop++;
                if(lastShop >= shopNum) {
                    break;
                }
                gallonsLeft = shops[lastShop].gallons;
            }
        }

        long maxMoney = 0;
        long totalRentedMoney = 0;
        for (int i = 0; i < cowNum; i++) {
            //i represents num cows rented out
            long money = 0;
            if(i < rentNum) {
                totalRentedMoney += rents[i];
            }
            money += totalRentedMoney;

            //sell just add up all milk and do best shops
            money += shopLUT[cowNum - i - 1];
            maxMoney = Math.max(maxMoney, money);
        }
        in.print(maxMoney);
        in.close();
    }

    static class Shop implements Comparable<Shop>{
        public int gallons;
        public int price;

        public Shop(int g, int p) {
            gallons = g;
            price = p;
        }

        @Override
        public int compareTo(Shop o) {
            return o.price - price;
        }

        @Override
        public String toString() {
            return "Shop{" +
                    "gallons=" + gallons +
                    ", price=" + price +
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
