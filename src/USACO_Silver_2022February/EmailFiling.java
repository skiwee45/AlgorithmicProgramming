package USACO_Silver_2022February;

import java.io.*;
import java.util.*;

public class EmailFiling {
    public static void main(String[] args) {
        Kattio in = new Kattio();
        int num = in.nextInt();
        for(int i = 0; i < num; i++) {
            boolean yes = solve(in);
            if(yes) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    private static boolean solve(Kattio in) {
        int numFolders = in.nextInt();
        int numEmails = in.nextInt();
        int viewSpace = in.nextInt();
        int[] lastOccurancesIndex = new int[numFolders]; //must be updated on the fly to do remove
        Email[] lastOccurances = new Email[numFolders];
        int[] counts = new int[numFolders];
        Arrays.fill(lastOccurancesIndex, -1);
        LinkedList<Email> emails = new LinkedList<>();

        for(int i = 0; i < numEmails; i++) {
            int prefFolder = in.nextInt() - 1;
            Email email = new Email(prefFolder);
            emails.addLast(email);
            counts[prefFolder]++;
            lastOccurancesIndex[prefFolder] = i;
            lastOccurances[prefFolder] = email;
        }

        for (int i = 0; i < lastOccurances.length; i++) {
            lastOccurances[i].isLast = true;
        }

        //going down
        ListIterator<Email> it = emails.listIterator();
        int folder;
        for(folder = 0; folder < numFolders; folder++) {
            if(counts[folder] == 0) continue;
            if(lastOccurancesIndex[folder] < it.nextIndex()) { //email is above current
                //going up
                int skippedCounter = 0;
                while(it.hasNext()) { //remove as much as possible
                    if(skippedCounter >= viewSpace) return false;
                    Email current = it.next();
                    if(isWithin(folder, folder + viewSpace, current.folder)) {
                        it.remove();
                        counts[current.folder]--;
                        if(current.isLast) {
                            lastOccurancesIndex[current.folder] = -1;
                        }
                    } else {
                        skippedCounter++;
                    }
                }
                while(it.hasPrevious()) {
                    if(emails.size() - (it.nextIndex() - 1) > viewSpace) return false;
                    Email current = it.previous();
                    if(isWithin(folder, folder + viewSpace, current.folder)) {
                        it.remove();
                        counts[current.folder]--;
                        if(counts[current.folder] == 0 && current.folder == folder) {
                            break;
                        }
                    }
                }
                continue;
            }
            while (it.nextIndex() <= lastOccurancesIndex[folder]) {
                if(!it.hasNext()) break;
                Email current = it.next();
                if (current.isLast) {
                    lastOccurancesIndex[current.folder] = it.nextIndex() - 1;
                }
                if (isWithin(folder, folder + viewSpace, current.folder)) {
                    it.remove();
                    counts[current.folder]--;
                    if (current.isLast) {
                        lastOccurancesIndex[current.folder] = -1;
                        if(current.folder == folder) {
                            break;
                        }
                    }
                }
            }
            for(int i = 0; i < viewSpace - 1; i++) {
                if(!it.hasPrevious()) break;
                it.previous();
            }
            if(counts[folder] > 0)
                folder--;
        }
        return emails.isEmpty();
    }

    static boolean isWithin(int start, int end, int test) {
        return start <= test && test < end;
    }

    static class Email {
        public int folder;
        public boolean isLast;

        public Email(int f) {
            folder = f;
        }
    }

    static class Kattio extends PrintWriter {
        private BufferedReader r;
        private StringTokenizer st;
        // standard input
        public Kattio() { this(System.in, System.out); }
        public Kattio(InputStream i, OutputStream o) {
            super(o);
            r = new BufferedReader(new InputStreamReader(i));
        }
        // USACO-style file input
        public Kattio(String problemName) throws IOException {
            super(problemName + ".out");
            r = new BufferedReader(new FileReader(problemName + ".in"));
        }
        // returns null if no more input
        public String next() {
            try {
                while (st == null || !st.hasMoreTokens())
                    st = new StringTokenizer(r.readLine());
                return st.nextToken();
            } catch (Exception e) { }
            return null;
        }
        public void skipLine() {
            try {
                st = new StringTokenizer(r.readLine());
            } catch (Exception e) { }
        }
        public int nextInt() { return Integer.parseInt(next()); }
        public double nextDouble() { return Double.parseDouble(next()); }
        public long nextLong() { return Long.parseLong(next()); }
    }
}
