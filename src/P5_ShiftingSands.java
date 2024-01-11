import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P5_ShiftingSands {

    public static int[][] shiftingSands(int[][] sands, String[] moves) {
        for(int i = 0; i < moves.length; i++) {
            String move = moves[i];
            switch (move) {
                case "E": shiftEast(sands);
                    break;
                case "W": shiftWest(sands);
                    break;
                case "N": shiftNorth(sands);
                    break;
                case "S": shiftSouth(sands);
                    break;
            }
        }
        return sands;
    }

    private static void shiftEast(int[][] sands) {
        for (int y = 0; y < 4; y++) {
            int lastX = 4;
            int lastSize = -1;
            boolean canCombine = true;
            for (int x = 3; x >= 0; x--) {
                int curSize = sands[y][x];
                if(curSize == 0) continue;
                if(canCombine && lastSize == curSize) {
                    sands[y][lastX] += lastSize;
                    lastSize += lastSize;
                    sands[y][x] = 0;
                    canCombine = false;
                } else {
                    sands[y][x] = 0;
                    sands[y][lastX - 1] = curSize;
                    lastX -= 1;
                    lastSize = curSize;
                    canCombine = true;
                }
            }
        }
    }

    private static void shiftWest(int[][] sands) {
        for (int y = 0; y < 4; y++) {
            int lastX = -1;
            int lastSize = -1;
            boolean canCombine = true;
            for (int x = 0; x < 4; x++) {
                int curSize = sands[y][x];
                if(curSize == 0) continue;
                if(canCombine && lastSize == curSize) {
                    sands[y][lastX] += lastSize;
                    lastSize += lastSize;
                    sands[y][x] = 0;
                    canCombine = false;
                } else {
                    sands[y][x] = 0;
                    sands[y][lastX + 1] = curSize;
                    lastX += 1;
                    lastSize = curSize;
                    canCombine = true;
                }
            }
        }
    }

    private static void shiftNorth(int[][] sands) {
        for (int x = 0; x < 4; x++) {
            int lastY = -1;
            int lastSize = -1;
            boolean canCombine = true;
            for (int y = 0; y < 4; y++) {
                int curSize = sands[y][x];
                if(curSize == 0) continue;
                if(canCombine && lastSize == curSize) {
                    sands[lastY][x] += lastSize;
                    lastSize += lastSize;
                    sands[y][x] = 0;
                    canCombine = false;
                } else {
                    sands[y][x] = 0;
                    sands[lastY + 1][x] = curSize;
                    lastY += 1;
                    lastSize = curSize;
                    canCombine = true;
                }
            }
        }
    }

    private static void shiftSouth(int[][] sands) {
        for (int x = 0; x < 4; x++) {
            int lastY = 4;
            int lastSize = -1;
            boolean canCombine = true;
            for (int y = 3; y >= 0; y--) {
                int curSize = sands[y][x];
                if(curSize == 0) continue;
                if(canCombine && lastSize == curSize) {
                    sands[lastY][x] += lastSize;
                    lastSize += lastSize;
                    sands[y][x] = 0;
                    canCombine = false;
                } else {
                    sands[y][x] = 0;
                    sands[lastY - 1][x] = curSize;
                    lastY -= 1;
                    lastSize = curSize;
                    canCombine = true;
                }
            }
        }
    }

    // Do not modify below this line
    public static void main(String args[]) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.valueOf(reader.readLine());

        for (int i = 0; i < t; i++) {
            int[][] sands = new int[5][5];

            for (int j = 0; j < 4; j++) {

                String line = reader.readLine();
                String[] elems = line.split(" ");
                for (int k = 0; k < 4; k++)
                    sands[j][k] = Integer.valueOf(elems[k]);
            }

            String[] moves = reader.readLine().split(" ");
            int[][] output = shiftingSands(sands, moves);

            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    System.out.print(output[j][k] + " ");
                }
                System.out.println();
            }
        }
    }
}