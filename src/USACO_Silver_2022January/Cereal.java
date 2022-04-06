package USACO_Silver_2022January;

import java.util.*;

public class Cereal {
    private static Cow extraCow = null;
    private static HashSet<Cow> hungry = new HashSet<>();
    private static Queue<Cow> order = new LinkedList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); //cows
        int m = in.nextInt(); //cereals
        List<List<Cow>> graph = new ArrayList<>(m);
        for(int i = 0; i < m; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i = 0; i < n; i++) {
            Cow cow = new Cow(i, in.nextInt() - 1, in.nextInt() - 1);
            graph.get(cow.fav).add(cow);
            graph.get(cow.secFav).add(cow);
        }

        trimCows(graph, 0, null, new boolean[m]);
        System.out.println(extraCow);
        System.out.println(hungry);

        findCowOrder(graph, extraCow.secFav, extraCow, new boolean[m]);
        System.out.println(order);

//		Stack<Integer> cowOrder = new Stack<>();
//		boolean[] travelled = new boolean[m];
//		for(int i = 0; i < m; i++) {
//			if(travelled[i]) continue;
//
//		}
    }

    static void trimCows(List<List<Cow>> graph, int current, Cow cow, boolean[] travelled) {
        if(cow != null) {
            if(cow.travelled) return;
            cow.travelled = true;
        }

        if(travelled[current]) {
            if(extraCow == null)
                extraCow = cow;
            else {
                hungry.add(cow);
            }
            return;
        }
        travelled[current] = true;
        for(Cow otherCow : graph.get(current)) {
            if(cow != null && otherCow.index == cow.index) continue;
            int end = otherCow.getEnd(current);
            trimCows(graph, end, otherCow, travelled);
        }
    }

    static void findCowOrder(List<List<Cow>> graph, int current, Cow cow, boolean[] travelled) {
        if(travelled[current] || hungry.contains(cow))
            return;

        travelled[current] = true;
        order.add(cow);
        for(Cow otherCow : graph.get(current)) {
            if(otherCow.index == cow.index) continue;
            int end = otherCow.getEnd(current);
            findCowOrder(graph, end, otherCow, travelled);
        }
    }

    static class Cow{
        public int index, fav, secFav;
        public boolean travelled = false;
        public Cow(int index, int fav, int secFav) {
            this.index = index;
            this.fav = fav;
            this.secFav = secFav;
        }

        public int getEnd(int start) {
            if(start == fav)
                return secFav;
            return fav;
        }

        public String toString() {
            return "I- " + index + "  F- " + (fav + 1) + "  S- " + (secFav + 1);
        }
    }
}
