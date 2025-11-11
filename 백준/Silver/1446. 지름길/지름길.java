import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, d, INF = Integer.MAX_VALUE/2;
    private static List<int[]>[] road;
    private static long[] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        d = readInt();
        
        road = new ArrayList[d+1];
        for (int i = 0; i < n; i++) {
            int s = readInt();
            int e = readInt();
            int v = readInt();
            
            if (e > d) continue;            
            if (road[e] == null) road[e] = new ArrayList<>();
            
            road[e].add(new int[]{s, v});
        }
                
        dp = new long[d+1];
        Arrays.fill(dp, INF);
        dp[0] = 0L;
        
        for (int e = 1; e <= d; e++) {
            dp[e] = Math.min(dp[e], dp[e-1]+1);
            if (road[e] == null) continue;
            
            for (int[] r : road[e]) {
                int s= r[0], v = r[1];
                dp[e] = Math.min(dp[e], dp[s]+v);
            }
        }

        System.out.print(dp[d]);
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