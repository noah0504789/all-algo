import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m, len, ans;
    private static int[] arr;
    private static List<Integer> runs;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = readInt();
        
        runs = new ArrayList<>();
        len = 1;
        for (int i = 1; i < n; i++) {
            if (arr[i] != arr[i-1]) {
                len++;
            } else {
                runs.add(len);
                len = 1;
            }
        }
        runs.add(len);
        
        m = runs.size();
        ans = 0;
        for (int i = 0; i < m; i++) ans = Math.max(ans, runs.get(i));
        for (int i = 0; i+1 < m; i++) ans = Math.max(ans, runs.get(i) + runs.get(i+1));
        for (int i = 0; i+2 < m; i++) ans = Math.max(ans, runs.get(i) + runs.get(i+1) + runs.get(i+2));
        

        System.out.print(ans);
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