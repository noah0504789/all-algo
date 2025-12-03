import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, d, INF = Integer.MAX_VALUE;
    private static int[] dp;
    private static int[][] roads;
    private static List<Integer>[] adj;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        d = readInt();
        
        roads = new int[d+1][d+1];
        adj = new ArrayList[d+1];
        for (int i = 0; i < n; i++) {
            int s = readInt();
            int e = readInt();
            int dist = readInt();
            if (s > d || e > d) continue;
                
            roads[s][e] = roads[s][e] == 0 ? dist : Math.min(roads[s][e], dist);
            if (adj[e] == null) adj[e] = new ArrayList<>();
            adj[e].add(s);
        }
        
        dp = new int[d+1];
        for (int i = 0; i <= d; i++) {
            dp[i] = i == 0 ? 0 : dp[i-1] + 1;
            
            if (adj[i] == null) continue;
            for (int prev : adj[i]) {
                dp[i] = Math.min(dp[i], dp[prev] + roads[prev][i]);
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