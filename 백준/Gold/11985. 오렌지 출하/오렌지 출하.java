import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m, k;
    private static long INF = Long.MAX_VALUE;
    private static int[] orange;
    private static long[] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        k = readInt();
        
        orange = new int[n+1];
        for (int i = 1; i <= n; i++) orange[i] = readInt();
        
        dp = new long[n+1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        
        for (int i = 1; i<= n; i++) {
            long min = orange[i], max = orange[i];
            
            for (int s = 1; s <= m && i-s+1>=1; s++) {
                int j = i-s;
                long x = orange[j+1];
                
                max = Math.max(max, x);
                min = Math.min(min, x);
                
                long cost = point(s, max, min);
                dp[i] = Math.min(dp[i], dp[j] + cost);
            }
        }

        System.out.print(dp[n]);
    }
    
    private static long point(int s, long a, long b) {
        return k + s * (a-b);
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