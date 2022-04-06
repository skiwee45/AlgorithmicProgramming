package USACO_Silver_2022January;

import java.util.Scanner;

public class Soulmates {
    public static void main(String[] args) {
        //Mult 2, Div 2, Add 1
        //Can only add, not subtract
        //If larger than target, must divide
        //Can only divide if even so may need to add 1 first
        //If smaller than target, mult (if mult is not larger than target) else add
        //Hardest part is multiplication, because adding before multiplying is good

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] cowPairs = new int[n][2];
        for(int i = 0; i < n; i++) {
            cowPairs[i][0] = in.nextInt();
            cowPairs[i][1] = in.nextInt();
        }

        for(int i = 0; i < n; i++) {
            int moves = solve(cowPairs[i][0], cowPairs[i][1]);
            if(i != n - 1) {
                System.out.println(moves);
            } else {
                System.out.print(moves);
            }
        }
    }

    //returns moves
    private static int solve(int current, int target) {
        if (current == target) return 0;
        //System.out.println(current + " " + target);
        int moves = 0;
        if(current > target) { //if more divide
            if(current % 2 != 0) {
                current++;
                moves++;
            }
            moves++;
            current /= 2;
        } else {
            if(current * 2 <= target) { //if less multiply
                if(target % (target / 2) == 1) {
                    moves++;
                }
                moves += solve(current, target / 2);
                moves++;
                current = target;
            } else {
                moves += target - current;
                return moves;
            }
        }
        return moves + solve(current, target);
    }
}
