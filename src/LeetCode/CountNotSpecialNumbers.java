package LeetCode;

public class CountNotSpecialNumbers {
    public static int nonSpecialCount(int l, int r) {
        //only squared of primes are special
        //except 1 is not special
        double sqrtL = Math.sqrt(l);
        double sqrtR = Math.sqrt(r);
        int firstSpecialRoot = (int) Math.ceil(sqrtL);
        int secondSpecialRoot = (int) Math.floor(sqrtR);
        int numPrimesBelow = countPrimes(firstSpecialRoot - 1);
        int numPrimesTotal = countPrimes(secondSpecialRoot);
        int numPrimesBetween = numPrimesTotal - numPrimesBelow;
        return (r - l + 1) - numPrimesBetween;
    }

    private static int countPrimes(int n)
    {
        boolean[] prime = new boolean[n + 1];
        for (int i = 0; i <= n; i++)
            prime[i] = true;

        for (int p = 2; p * p <= n; p++) {
            if (prime[p]) {
                for (int i = p * p; i <= n; i += p)
                    prime[i] = false;
            }
        }

        int count = 0;
        for (int i = 2; i <= n; i++) {
            if (prime[i])
                count++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(nonSpecialCount(5, 7));
    }
}
