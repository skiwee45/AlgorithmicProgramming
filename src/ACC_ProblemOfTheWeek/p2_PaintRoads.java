package ACC_ProblemOfTheWeek;

import java.util.*;

public class p2_PaintRoads {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();

        //create graph
        List<int[]> graph = constructGraph(n);
        for (int[] node :
                graph) {
            System.out.println(Arrays.toString(node));
        }

        //construct string
        String out = "";
        int max = 0;
        for (int i = 0; i < graph.size(); i++) {
            int groupNum = i / k;
            for (int connectedNode:
                    graph.get(i)) {
                int otherGroupNum = connectedNode / k;
                int roadColor;
                if (groupNum == otherGroupNum) {
                    roadColor = 1;
                } else {
                    roadColor = groupNum + 2;
                }
                max = Math.max(roadColor, max);
                out += roadColor + " ";
            }
        }
        System.out.println(max);
        System.out.print(out.substring(0, out.length() - 1));
//
//        //construct string
//        String out = "";
//        for (int i = 0; i < graph.size(); i++) {
//            int groupNum = i / k;
//            for (int connectedNode:
//                    graph.get(i)) {
//                int otherGroupNum = connectedNode / k;
//                if (groupNum == otherGroupNum) {
//                    out += 1;
//                } else {
//                    out += groupNum + 2;
//                }
//                out += " ";
//            }
//        }
//        System.out.print(out.substring(0, out.length() - 1));

        //find all roads >= size k
        //List<Path> paths = findAllPaths(k, graph);
        //System.out.println(paths);
    }

    private static List<Path> findAllPaths(int k, List<List<Integer>> graph) {
        List<Path> temp = findAllPaths(k, graph, 0, 0);
        Iterator<Path> it = temp.iterator();
        while(it.hasNext()) {
            if(it.next().nodes.length != k) {
                it.remove();
            }
        }
        return temp;
    }

    private static List<Path> findAllPaths(int k, List<List<Integer>> graph, int node, int length) {
        if(length > k) {
            return new ArrayList<>();
        }
        length++;
        List<Path> output = new ArrayList<>();
        for(int e : graph.get(node)) {
            List<Path> temp = findAllPaths(k, graph, e, length);
            if (temp == null) {
                break;
            }
            for (Path path : temp) {
                output.add(new Path(path, node));
            }
            output.addAll(temp);
        }
        if(output.isEmpty()) {
            output.add(new Path(new int[] {node}));
        }

        return output;
    }

//    private static List<List<Integer>> constructGraph(int n) {
//        List<Integer> allNums = new ArrayList<>();
//        for(int i = 0; i < n; i++) {
//            allNums.add(i);
//        }
//
//        List<List<Integer>> output = new ArrayList<>();
//        for(int i = 0; i < n; i++) {
//            allNums.remove(0);
//            output.add(new ArrayList<>(allNums));
//        }
//        return output;
//    }

    private static List<int[]> constructGraph(int n) {
        int[] allNums = new int[n];
        for(int i = 0; i < n; i++) {
            allNums[i] = i;
        }

        List<int[]> output = new ArrayList<>();
        for(int i = 0; i < n - 1; i++) {
            output.add(Arrays.copyOfRange(allNums, i + 1, allNums.length));
        }
        return output;
    }

    private static class Path {
        public boolean satisfied = false;
        public int[] nodes;
        public Path(int[] nodes) {
            this.nodes = nodes;
        }

        public Path(Path path, int originNode) {
            nodes = new int[path.nodes.length];
            nodes[0] = originNode;
            for(int i = 1; i < nodes.length; i++) {
                nodes[i] = path.nodes[i];
            }
        }

        @Override
        public String toString() {
            return "Path{" +
                    "satisfied=" + satisfied +
                    ", nodes=" + Arrays.toString(nodes) +
                    '}';
        }
    }

}
