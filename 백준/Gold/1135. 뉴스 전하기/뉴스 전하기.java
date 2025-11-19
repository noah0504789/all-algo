import java.io.*;
import java.util.*;

public class Main {
    
    private static int n;
    private static List<Integer>[] children;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        
        children = new ArrayList[n];
        for (int i = 0; i < n; i++) children[i] = new ArrayList<>();        
        readInt();
        for (int i = 1; i < n; i++) children[readInt()].add(i);
        
        System.out.print(dfs(0));
    }
    
    private static int dfs(int s) {
        if (children[s].isEmpty()) return 0;
        
        List<Integer> times = new ArrayList<>();
        for (int child : children[s]) times.add(dfs(child));
        
        times.sort(Comparator.reverseOrder());
        
        int res = 0;
        for (int i = 0; i < times.size(); i++) {
            int fin = times.get(i) + i + 1;
            res = Math.max(res, fin);
        }
        
        return res;
    }
    

    public static int readInt() throws IOException {
        int r = 0, c = System.in.read();
        boolean negative = false;       

        while (c <= ' ') c = System.in.read();
        if (c == '-') {
            negative = true;
            c = System.in.read();
        }
        while (c >= '0' && c <= '9') {
            r *= 10;
            r += c - '0';
            c = System.in.read();
        }

        return negative ? -r : r;
    }
}