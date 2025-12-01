import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, r = 0, g= 1, b = 2, INF = Integer.MAX_VALUE;
    private static int[][] cost, dp, adj = {
        {1, 2}, {0, 2}, {0, 1}
    };
    
    public static void main(String... args) throws IOException {
        n = readInt();
        cost = new int[n+1][3];
        for (int i = 1; i <= n; i++) {
            cost[i][0] = readInt();
            cost[i][1] = readInt();
            cost[i][2] = readInt();
        }
        
        dp = new int[n+1][3];
        for (int i = 1; i <= n; i++) Arrays.fill(dp[i], INF);        

        System.out.print(Math.min(dfs(1, r), Math.min(dfs(1, g), dfs(1, b))));
    }
    
    private static int dfs(int i, int c) {
        if (i == n) return cost[i][c];
        if (dp[i][c] != INF) return dp[i][c];
        
        int min = INF;
        for (int o : adj[c]) min = Math.min(min, cost[i][c] + dfs(i+1, o));
        
        return dp[i][c] = min;
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