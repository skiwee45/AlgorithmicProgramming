import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P6_LanternFestival {

    public static int[] lanternFestival(int n, int[][] prefs) {
        Lantern[] lanterns = new Lantern[n];
        for (int i = 0; i < n; i++) {
            lanterns[i] = new Lantern(i, null);
        }
        for (int i = 0; i < prefs.length; i++) {
            int top = prefs[i][0];
            int bottom = prefs[i][1];
            boolean successfulChild = lanterns[top].addChild(lanterns[bottom]);
            if(!successfulChild) {
                return new int[] {-1};
            }
            boolean successfulParent = lanterns[bottom].addParent(lanterns[top]);
            if(!successfulParent) {
                return new int[] {-1};
            }
        }

        PriorityQueue<IndependentLantern> independentLanterns = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            Lantern current = lanterns[i];
            if(current.parent == null) {
                //is independent
                IndependentLantern newInd = new IndependentLantern(current);
                newInd.createNeedyChildren();
                independentLanterns.add(newInd);
            }
        }

        IndependentLantern first = independentLanterns.poll();
        Lantern head = first.head;
        if (!first.fillNeedy(independentLanterns)) return new int[] {-1};
        return head.output(n);
    }

    static class IndependentLantern implements Comparable<IndependentLantern> {
        public Lantern head;
        public List<Lantern> needyChildren;

        public IndependentLantern(Lantern h) {
            head = h;
            needyChildren = new ArrayList<>();
        }

        public void createNeedyChildren() {
            recurChildren(head, needyChildren);
        }

        private static void recurChildren(Lantern head, List<Lantern> needy) {
            if(!head.isValid()) {
                needy.add(head);
            }
            for (Lantern child : head.children) {
                recurChildren(child, needy);
            }
        }

        public boolean fillNeedy(PriorityQueue<IndependentLantern> availableLanterns) {
            for (Lantern l : needyChildren) {
                if(availableLanterns.isEmpty()) {
                    return false;
                }
                IndependentLantern next = availableLanterns.poll();
                l.addChild(next.head);
                if(!next.fillNeedy(availableLanterns)) return false;
            }
            return true;
        }

        @Override
        public int compareTo(IndependentLantern o) {
            return o.needyChildren.size() - needyChildren.size();
        }
    }

    static class Lantern implements Comparable<Lantern>{
        public int number;
        public Lantern parent;
        public TreeSet<Lantern> children;

        public Lantern(int n, Lantern p) {
            number = n;
            parent = p;
            children = new TreeSet<>();
        }

        public int[] output(int n){
            Queue<Lantern> q = new LinkedList<>();
            q.add(this);
            int[] out = new int[n];
            int i = 0;
            while(!q.isEmpty()) {
                Lantern cur = q.poll();
                out[i] = cur.number;
                i++;
                q.addAll(cur.children);
            }
            return out;
        }

        public boolean addChild(Lantern child) {
            if(children.size() == 2) return false;
            //check all children
            if (!checkParentForChild(child)) return false;
            children.add(child);
            return true;
        }

        public boolean checkParentForChild(Lantern c) {
            if(parent == null) return true;
            if(parent.equals(c)) return false;

            return parent.checkParentForChild(c);
        }

        public boolean addParent(Lantern p) {
            if(parent != null) return false;
            //check all children
            if (!checkChildrenForParent(p)) return false;

            parent = p;
            return true;
        }

        public boolean checkChildrenForParent(Lantern p) {
            for(Lantern child : children) {
                if(child.equals(p)) return false;
                if(child.checkChildrenForParent(p)) return false;
            }
            return true;
        }

        public boolean isValid() {
            return children.size() == 2 || children.size() == 0;
        }

        @Override
        public int compareTo(Lantern o) {
            return number - o.number;
        }

        public boolean equals(Lantern o) {
            return compareTo(o) == 0;
        }
    }


    // Do not modify below this line
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.valueOf(reader.readLine());

        for (int i = 0; i < t; i++) {
            String inputNodes = reader.readLine();
            int n = Integer.valueOf(inputNodes);
            String edgeNum = reader.readLine();
            int k = Integer.valueOf(edgeNum);
            int[][] prefs = new int[k][2];
            for (int z = 0; z < k; z++) {
                String inputEdges = reader.readLine();
                String[] inputE = inputEdges.split(" ");
                prefs[z][0] = Integer.valueOf(inputE[0]);
                prefs[z][1] = Integer.valueOf(inputE[1]);
            }

            int [] output = lanternFestival(n, prefs);
            for (Integer e: output) {
                System.out.println(e);
            }
        }
    }
}