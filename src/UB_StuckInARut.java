import java.util.*;

public class UB_StuckInARut {
    public static void main(String[] args) {
        List<int[]> allMap = new ArrayList<>();
        List<int[]> northMap = new ArrayList<>();
        List<int[]> eastMap = new ArrayList<>();
        Scanner in  = new Scanner(System.in);
        int n = in.nextInt();
        for(int i = 0; i < n; i++) {
            boolean isNorth = in.next().equals("N");
            int x = in.nextInt();
            int y = in.nextInt();
            int[] temp;
            if(isNorth) {
                temp = new int[] {x, y, Integer.MAX_VALUE};
                northMap.add(temp);
            } else {
                temp = new int[] {y, x, Integer.MAX_VALUE};
                eastMap.add(temp);
            }
            allMap.add(temp);
        }

        Collections.sort(northMap, new coordComparator());
        Collections.sort(eastMap, new coordComparator());

        for (int[] cow : northMap){
            int size = eastMap.size();
            for(int i = 0; i < size; i++) {
                int[] oCow = eastMap.get(i);
                if(oCow[1] >= cow[0] || oCow[0] <= cow[1]) //check in front of this point
                    continue;
                int time = cow[0] - oCow[1];
                int reachTime = oCow[0] - cow[1];
                if(reachTime > time) { //hits
                    cow[2] = Math.min(reachTime, cow[2]);
                    break;
                } else if (time > reachTime){
                    oCow[2] = Math.min(time, oCow[2]);
                    eastMap.remove(oCow);
                    i--;
                    size--;
                }
            }
        }
        for(int i = 0; i < allMap.size() - 1; i++) {
            int[] cow = allMap.get(i);
            String prt = cow[2] == Integer.MAX_VALUE ? "Infinity" : Integer.toString(cow[2]);
            System.out.println(prt);
        }
        System.out.print(allMap.get(allMap.size() - 1)[2] == Integer.MAX_VALUE ? "Infinity" : Integer.toString(allMap.get(allMap.size() - 1)[2]));
    }

    static class coordComparator implements Comparator<int[]> {

        @Override
        public int compare(int[] o1, int[] o2) {
            return o1[0] - o2[0];
        }
    }
}
