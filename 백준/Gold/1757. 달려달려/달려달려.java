import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m, INF = Integer.MAX_VALUE;
    private static int[] speed;
    private static long[][] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        
        speed = new int[n];
        for (int i = 0; i < n; i++) speed[i] = readInt();
        
        dp = new long[n+1][m+1];
        for (int i = 0; i <= n; i++) Arrays.fill(dp[i], -INF);        

        System.out.print(dfs(0, 0));
    }
    
    private static long dfs(int i, int t) {
        if (i == n) return t == 0 ? 0 : -INF;
        if (dp[i][t] != -INF) return dp[i][t];
        
        long max = -INF;
        if (t == 0) max = Math.max(max, dfs(i+1, 0));
        else if (i+t <= n) max = Math.max(max, dfs(i+t, 0));
        
        if (t+1 <= m) max = Math.max(max, speed[i] + dfs(i+1, t+1));
        
        return dp[i][t] = max;
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