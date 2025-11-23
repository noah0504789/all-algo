import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m, ans, INF = 1_000_000_000;
    private static boolean[] unavailable;
    private static int[][] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        unavailable = new boolean[n+1];
        for (int i = 0; i < m; i++) unavailable[readInt()] = true;
        
        if (unavailable[2]) {
            System.out.print(-1);
            return;
        }
        
        dp = new int[n+1][151];
        for (int i = 1; i <= n; i++) Arrays.fill(dp[i], INF);        
        
        ans = dfs(2, 1);
        
        System.out.print(ans == INF ? -1 : ans + 1);
    }
    
    private static int dfs(int i, int s) {
        if (i == n) return 0;
        if (s <= 0 || s > 150) return INF;
        if (dp[i][s] != INF) return dp[i][s];        
        
        int min = INF;
        for (int ns = s-1; ns <= s+1; ns++) {
            if (ns <= 0 || ns > 150) continue;
            int ni = i + ns;
            if (ni > n) continue;
            if (unavailable[ni]) continue;
                        
            min = Math.min(min, 1+dfs(ni, ns));
        }
        
        return dp[i][s] = min;
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