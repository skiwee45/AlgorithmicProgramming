import java.util.*;

public class US_StuckInARut {
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
                temp = new int[] {x, y, 0};
                northMap.add(temp);
            } else {
                temp = new int[] {y, x, 0};
                eastMap.add(temp);
            }
            allMap.add(temp);
        }

        northMap.sort(new coordComparator());
        eastMap.sort(new coordComparator());

        for (int[] cow : northMap){
            int size = eastMap.size();
            for(int i = 0; i < size; i++) {
                int[] oCow = eastMap.get(i);
                if(oCow[1] >= cow[0] || oCow[0] <= cow[1]) //check in front of this point
                    continue;
                int time = cow[0] - oCow[1];
                int reachTime = oCow[0] - cow[1];
                if(reachTime > time) { //hits
                    oCow[2] += cow[2] + 1;
                    break;
                } else if (time > reachTime){
                    cow[2] += oCow[2] + 1;
                    eastMap.remove(oCow);
                    i--;
                    size--;
                }
            }
        }
        for(int i = 0; i < allMap.size() - 1; i++) {
            int[] cow = allMap.get(i);
            System.out.println(cow[2]);
        }
        System.out.print(allMap.get(allMap.size() - 1)[2]);
    }

    static class coordComparator implements Comparator<int[]> {

        @Override
        public int compare(int[] o1, int[] o2) {
            return o1[0] - o2[0];
        }
    }
}
