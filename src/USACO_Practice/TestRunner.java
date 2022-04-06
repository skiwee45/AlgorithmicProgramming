package USACO_Practice;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestRunner {
    public static boolean[] runTests(Method solver, String folderPath, int numCases) throws IOException, InvocationTargetException, IllegalAccessException {
        boolean[] correct = new boolean[numCases];
        for(int i = 1; i <= numCases; i++) {
            Kattio in = new Kattio(folderPath + "/" + i + ".in");
            String[] output = (String[]) solver.invoke(null, in);
            Kattio correctOutputReader = new Kattio(folderPath + "/" + i + ".out");
            String outputToken = correctOutputReader.next();
            int j = 0;
            correct[i - 1] = true;
            while (outputToken != null) {
                if (!output[j].equals(outputToken)) {
                    correct[i - 1] = false;
                    break;
                }
                outputToken = correctOutputReader.next();
                j++;
            }
        }
        return correct;
    }

    //prints your output and the correct output for all cases
    public static boolean[] debugTests(Method solver, String folderPath, int numCases) throws IOException, InvocationTargetException, IllegalAccessException {
        boolean[] correct = new boolean[numCases];
        for(int i = 1; i <= numCases; i++) {
            System.out.println("\n\n\nCase " + i);
            Kattio in = new Kattio(folderPath + "/" + i + ".in");
            String[] output = (String[]) solver.invoke(null, in);
            Kattio correctOutputReader = new Kattio(folderPath + "/" + i + ".out");
            String outputToken = correctOutputReader.next();
            int j = 0;
            correct[i - 1] = true;
            System.out.println("Correct");
            while (outputToken != null) {
                System.out.println(outputToken);
                if (!output[j].equals(outputToken)) {
                    correct[i - 1] = false;
                }
                outputToken = correctOutputReader.next();
                j++;
            }
            System.out.println("Yours");
            for(String s : output) {
                System.out.println(s);
            }
        }
        return correct;
    }
}
