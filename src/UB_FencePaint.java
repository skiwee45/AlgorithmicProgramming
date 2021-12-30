import java.util.HashMap;
import java.util.HashSet;

public class UB_FencePaint {
    public static void main(String[] args) {
        calculateFencePaint(7, 10, 4, 8);
    }

    //http://www.usaco.org/index.php?page=viewproblem2&cpid=567
    public static void calculateFencePaint(int a, int b, int c, int d) {
        boolean overlap = (a >= c && a <= d) || (b >= c && b <= d);
        if (overlap)
            System.out.print(Math.max(b, d) - Math.min(a, c));
        else
            System.out.print((b - a) + (d - c));
    }
}
