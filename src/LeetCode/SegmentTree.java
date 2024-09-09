package LeetCode;

public class SegmentTree {
    public static void main(String[] args) {
        // goal is just to have a segment tree that sums a range
        SegmentTree st = new SegmentTree(new int[]{1, 2, 3, 4, 5, 6, 7});
        System.out.println(st.sumRange(3, 5));

        st.updateTree(3, 14);
        System.out.println(st.sumRange(2, 4));
    }

    private static class Node{
        int start, end, sum;
        Node left, right;

        public Node(int start, int end, int sum){
            this.start = start;
            this.end = end;
            this.sum = sum;
        }
    }

    private Node root;

    public SegmentTree(int[] nums) {
        root = buildTree(nums, 0, nums.length - 1);
    }

    private Node buildTree(int[] nums, int start, int end) {
        if (start == end) {
            return new Node(start, end, nums[start]);
        }

        int mid = (start + end) / 2;
        Node left = buildTree(nums, start, mid);
        Node right = buildTree(nums, mid + 1, end);
        Node current = new Node(start, end, left.sum + right.sum);
        current.left = left;
        current.right = right;
        System.out.println(start + "-" + end + ": " + current.sum);
        return current;
    }

    private int sumRange(int start, int end) {
        return sumRangeHelper(start, end, root);
    }

    private int sumRangeHelper(int start, int end, Node current) {
        if (current.start > end || current.end < start) {
            return 0;
        }

        if (current.start >= start && current.end <= end) {
            return current.sum;
        }

        return sumRangeHelper(start, end, current.left) + sumRangeHelper(start, end, current.right);
    }

    private void updateTree(int i, int newValue) {
        updateTreeHelper(i, newValue, root);
    }

    private void updateTreeHelper(int i, int newValue, Node current) {
        if (current.start == current.end && current.start == i) {
            current.sum = newValue;
            return;
        }

        if (i <= current.left.end)
            updateTreeHelper(i, newValue, current.left);
        else
            updateTreeHelper(i, newValue, current.right);

        current.sum = current.left.sum + current.right.sum;
    }
}
