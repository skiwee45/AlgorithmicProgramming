package USACO_Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//https://cses.fi/problemset/task/1090/
public class CSES_FerrisWheel {
    public static void main(String[] args) {
        MyScanner in = new MyScanner();
        int numPeople = in.nextInt();
        int maxWeight = in.nextInt();
        int[] people = new int[numPeople];
        for(int i = 0; i < numPeople; i++) {
            people[i] = in.nextInt();
        }

        //sort and classify
        Arrays.sort(people);
        int current = people[numPeople - 1];
        int largest = numPeople - 2;
        int start = people[0];
        while(largest > 0 && maxWeight - start < current) {
            current = people[largest];
            largest--;
        }
        largest++;

        int total = numPeople - largest - 1;

        int smallest = 0;
        while(smallest < largest) {
            if(maxWeight - people[smallest] >= people[largest]) {
                smallest++;
            }
            largest--;
            total++;
        }
        if(smallest == largest)
            total++;
        System.out.print(total);
    }

    public static class MyScanner {
        BufferedReader br;
        StringTokenizer st;

        public MyScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine(){
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

    }
}