package USACO_Bronze_2021December;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//http://www.usaco.org/index.php?page=viewproblem2&cpid=1155
public class UB_LonelyPhotosAnalysis {
    public static void main(String[] args) {
        MyScanner in = new MyScanner();
        int n = in.nextInt();
        String input = in.next();
        char[] arr = input.toCharArray();
        boolean[] cows = new boolean[n]; //true = G, false = H

        int firstG = -1;
        int secondG = -1;
        int firstH = -1;
        int secondH = -1;
        int count = 0;

        for(int i = 0; i < n; i++) {
            cows[i] = arr[i] == 'G';

            if (count == 4)
                continue;
            if (cows[i]) {
                if (firstG == -1) {
                    firstG = i;
                    count++;
                }
                else if (secondG == -1) {
                    secondG = i;
                    count++;
                }
            } else {
                if (firstH == -1) {
                    firstH = i;
                    count++;
                }
                else if (secondH == -1) {
                    secondH = i;
                    count++;
                }
            }
        }
        if(firstG == -1)
            firstG = n;
        if(secondG == -1)
            secondG = n;
        if(firstH == -1)
            firstH = n;
        if(secondH == -1)
            secondH = n;

        long lonely = 0;
        if (secondH > secondG) {
            lonely += Math.max(secondH - Math.max(firstH, 2), 0);
        } else {
            lonely += Math.max(secondG - Math.max(firstG, 2), 0);
        }
        //System.out.println(firstG + " " + secondG + " " + firstH + " " + secondH + " " + lonely);


        for (int i = 1; i < n - 2; i++) {
            //update vars
            if (firstG < i) {
                firstG = secondG;
                for (int j = firstG + 1; j < n; j++) {
                    if (cows[j]) {
                        secondG = j;
                        break;
                    }
                }
                if(secondG == firstG) {
                    secondG = n;
                }
            } else if (firstH < i) {
                firstH = secondH;
                for (int j = firstH + 1; j < n; j++) {
                    if (!cows[j]) {
                        secondH = j;
                        break;
                    }
                }
                if(secondH == firstH) {
                    secondH = n;
                }
            }
            //calculate lonely
            if (secondH > secondG) {
                lonely += Math.max(secondH - Math.max(firstH, i + 2), 0);
            } else {
                lonely += Math.max(secondG - Math.max(firstG, i + 2), 0);
            }
            //System.out.println(firstG + " " + secondG + " " + firstH + " " + secondH + " " + lonely);
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
