package USACO_Bronze_2021December;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//http://www.usaco.org/index.php?page=viewproblem2&cpid=1155
public class UB_LonelyPhotosContestSubmission {
    public static void main(String[] args) {
        MyScanner in = new MyScanner();
        int n = in.nextInt();
        String input = in.next();
        char[] arr = input.toCharArray();
        int gUnicode = 'G';
        int[] cows = new int[n]; //true = G, false = H
        for(int i = 0; i < n; i++) {
            cows[i] = arr[i] - gUnicode;
        }

        long lonely = 0;
        for (int i = 0; i < n - 2; i++) {
            long countOfCows = 0;
            long totalCows = 0;
            for (int j = i; j < n; j++) {
                totalCows++;
                countOfCows += cows[j];
                if(totalCows < 3)
                    continue;
                if(totalCows - countOfCows == 1 || totalCows - countOfCows == totalCows - 1) {
                    lonely++;
                } else if(totalCows - countOfCows > 1 && countOfCows > 1) {
                    break;
                }
            }
        }
        System.out.print(lonely);
    }

     static class MyScanner {
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
