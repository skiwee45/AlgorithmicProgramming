package USACO_Practice;

import java.io.*;
import java.util.*;

//http://www.usaco.org/index.php?page=viewproblem2&cpid=857
public class UB_BackAndForth {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        HashMap<Integer, Integer> buckets1 = new HashMap<>();
        for(int i = 0; i < 10; i++) {
            int cap = scan.nextInt();
            if(buckets1.containsKey(cap))
                buckets1.replace(cap, buckets1.get(cap) + 1);
            else
                buckets1.put(cap, 1);
        }

        HashMap<Integer, Integer> buckets2 = new HashMap<>();
        for(int i = 0; i < 10; i++) {
            int cap = scan.nextInt();
            if(buckets2.containsKey(cap))
                buckets2.replace(cap, buckets2.get(cap) + 1);
            else
                buckets2.put(cap, 1);
        }

        System.out.println(testAll(buckets1, buckets2, 1000, 0).size());


//        BufferedReader r = new BufferedReader(new FileReader("backforth.in"));
//        PrintWriter pw = new PrintWriter("backforth.out");
//
//        StringTokenizer st1 = new StringTokenizer(r.readLine());
//        HashMap<Integer, Integer> buckets1 = new HashMap<>();
//        for(int i = 0; i < 10; i++) {
//            int cap = Integer.parseInt(st1.nextToken());
//            if(buckets1.containsKey(cap))
//                buckets1.replace(cap, buckets1.get(cap) + 1);
//            else
//                buckets1.put(cap, 1);
//        }
//
//        StringTokenizer st2 = new StringTokenizer(r.readLine());
//        HashMap<Integer, Integer> buckets2 = new HashMap<>();
//        for(int i = 0; i < 10; i++) {
//            int cap = Integer.parseInt(st2.nextToken());
//            if(buckets2.containsKey(cap))
//                buckets2.replace(cap, buckets2.get(cap) + 1);
//            else
//                buckets2.put(cap, 1);
//        }
//        pw.print(testAll(buckets1, buckets2, 1000, 0).size());
//        pw.close();
    }

    private static HashSet<Integer> testAll(HashMap<Integer, Integer> buckets1, HashMap<Integer, Integer> buckets2, int b, int day) {
        HashMap<Integer, Integer> bucket;
        boolean isTank1 = day % 2 == 0;
        if(isTank1) {
            bucket = buckets1;
        } else {
            bucket = buckets2;
        }

        if(day == 3) {
            HashSet<Integer> outcomes = new HashSet<>();
            for (Integer buck : bucket.keySet()) {
                outcomes.add(isTank1 ? b - buck : b + buck);
            }
            return outcomes;
        }

        HashSet<Integer> outcomes = new HashSet<>();
        Iterator<Map.Entry<Integer, Integer>> it = bucket.entrySet().iterator();
        while(it.hasNext()) {
            HashMap<Integer, Integer> buckets;
            HashMap<Integer, Integer> otherBuckets;
            HashMap<Integer, Integer> b1 = copyHashMap(buckets1);
            HashMap<Integer, Integer> b2 = copyHashMap(buckets2);
            if(isTank1) {
                buckets = b1;
                otherBuckets = b2;
            } else {
                buckets = b2;
                otherBuckets = b1;
            }
            Map.Entry<Integer, Integer> temp = it.next();
            int buck = temp.getKey();
            int count = temp.getValue();
            buckets.replace(buck, count - 1);
            if(count - 1 == 0) {
                buckets.remove(buck);
            }
            otherBuckets.put(buck, otherBuckets.getOrDefault(buck, 0) + 1);
            HashSet<Integer> recur = testAll(b1, b2, isTank1 ? b - buck : b + buck, day + 1);

            outcomes.addAll(recur);
        }
        return outcomes;
    }

    private static HashMap<Integer, Integer> copyHashMap(HashMap<Integer, Integer> hash) {
        Iterator<Map.Entry<Integer, Integer>> it = hash.entrySet().iterator();
        HashMap<Integer, Integer> out = new HashMap<>();
        while(it.hasNext()) {
            Map.Entry<Integer, Integer> temp = it.next();
            int key = temp.getKey();
            int val = temp.getValue();
            out.put(key, val);
        }
        return out;
    }
}