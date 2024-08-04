package LeetCode;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class CountDominantSubstrings {
    public static int numberOfSubstrings(String s) {
        // trick is to use the binary nature of the string
        // mark the positions of zeros
        ArrayList<Integer> zeros = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                zeros.add(i);
            }
        }
        zeros.add(s.length()); // simulates a zero at the end so that it doesn't overflow

        int nextZerosIndex = 0;
        int count = 0;
        // still same structure of count each substring that starts at each index
        for (int i = 0; i < s.length(); i++) {
            // find the next zero
            int currentIndex = i - 1;
            while (nextZerosIndex < zeros.size() && zeros.get(nextZerosIndex) <= currentIndex) {
                nextZerosIndex++;
            }

            int zerosSoFar = 0, onesSoFar = 0;
            for (int zeroIndex = nextZerosIndex; zeroIndex < zeros.size(); zeroIndex++) {
                int newOnes = zeros.get(zeroIndex) - currentIndex - 1;
                int numOnesNeeded = zerosSoFar * zerosSoFar - onesSoFar;

                if (numOnesNeeded <= 0) {
                    if (zeroIndex == nextZerosIndex) // if we are at the first zero, we haven't passed a zero yet
                        count += newOnes;
                    else
                        count += newOnes + 1; // +1 for the zero we just passed
                }
                else if (numOnesNeeded <= newOnes) {
                    count += newOnes - numOnesNeeded + 1;
                }

                currentIndex = zeros.get(zeroIndex);
                onesSoFar += newOnes;
                zerosSoFar++;

                if (zerosSoFar * zerosSoFar > onesSoFar + s.length() - currentIndex - 1) {
                    break;
                }
            }
        }

        return count;
    }

    public static int numberOfSubstringsAnswerKey(String s) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                deque.offer(i);
            }
        }
        deque.offer(s.length());
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            while (!deque.isEmpty() && deque.peek() < i) {
                deque.poll();
            }
            //i is the starting index of the substring
            //k is the position of the last passed zero (-1 if i = 0)
            int k = i - 1, x = 0, y = 0; //x is num zeros so far
            //y is how many ones have passed
            for (int j : deque) { //j is the next zero after k
                //j - k - 1 is the number of ones between k and the next zero
                //x * x - y is the squared zeros minus total ones
                count += j - k - 1 < x * x - y ? 0 : j - k - Math.max(1, x * x - y);
                //increments x (since 1 zero is passed), adds the number of ones passed to y
                //makes k = j since k is the last zero passed and j is the next zero
                if (++x * x <= (y += j - k - 1) & (k = j) < s.length()) {
                    //this adds one to count if including the next zero, the substring is still dominant
                    count++;
                } else if (x * x > y + s.length() - j - 1) {
                    //if the squared zeros is greater than the remaining ones, break
                    break;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.print(numberOfSubstrings("101101"));
    }
}
