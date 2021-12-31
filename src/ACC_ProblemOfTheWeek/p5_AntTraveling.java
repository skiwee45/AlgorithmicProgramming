package ACC_ProblemOfTheWeek;

import java.util.*;
import java.io.*;

public class p5_AntTraveling {
    public static void main(String[] args) {
        MyScanner in = new MyScanner();
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = in.nextInt() - 1;
        }

        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        for(int i = 0; i < n; i++) {
            if(map.containsKey(i)) {
                continue;
            }
            List<Integer> list = new ArrayList<>();
            int current = arr[i];
            int total = 0;
            for(;;) {
                list.add(current);
                total++;
                if(current == i)
                    break;
                current = arr[current];
            }

            for(int e : list) {
                map.put(e, total);
            }
        }

        int count = 0;
        for (Map.Entry<Integer, Integer> i : map.entrySet()) {
            String str = i.getValue().toString();
            if(count < map.size() - 1)
                str += " ";
            System.out.print(str);
        }
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