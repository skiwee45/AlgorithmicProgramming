package LeetCode_DynamicProgramming;

public class Runner {
    public static void main(String[] args) {
        MaximalSquare test = new MaximalSquare();
        System.out.println(test.maximalSquare(
                new char[][]{
                        {'0', '1'},
                        {'1', '0'}
                }));
    }
}
