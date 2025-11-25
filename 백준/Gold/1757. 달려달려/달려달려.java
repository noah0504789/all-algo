import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m, INF = Integer.MAX_VALUE;
    private static int[] dist;
    private static long[][] dp; // 거리가 i이고, m이 j일때, n까지 갈수 있는 최대 거리
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        dist = new int[n];
        for (int i = 0; i < n; i++) dist[i] = readInt();
        
        dp = new long[n+1][m+1];
        for (int i = 0; i <= n; i++) Arrays.fill(dp[i], -INF);

        System.out.print(dfs(0, 0));
    }
    
    private static long dfs(int i, int j) {
        if (i == n) return j == 0 ? 0 : -INF;
        if (dp[i][j] != -INF) return dp[i][j];
        
        long max = -INF;
        if (j == 0) max = Math.max(max, dfs(i+1, 0));
        else if (i+j <= n) max = Math.max(max, dfs(i+j, 0)); 
        
        if (j+1 <= m) max = Math.max(max, dist[i] + dfs(i+1, j+1));
        
        return dp[i][j] = max;
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