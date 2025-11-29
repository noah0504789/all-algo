import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, i1, i2, m, d, INF = Integer.MAX_VALUE;
    private static long ans;
    private static int[] dest;
    private static long[][][] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        i1 = readInt();
        i2 = readInt();
        
        m = readInt();
        dest = new int[m];
        for (int i = 0; i < m; i++) dest[i] = readInt();
        
        dp = new long[m+1][n+1][n+1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) Arrays.fill(dp[i][j], INF);
        }        

        System.out.print(dfs(0, i1, i2));
    }
    
    private static long dfs(int i, int l, int r) {
        if (i == m) return 0;
        if (dp[i][l][r] != INF) return dp[i][l][r];
        
        int cur = dest[i];
        long min = INF;
        min = Math.min(min, Math.abs(cur-l) + dfs(i+1, cur, r));
        min = Math.min(min, Math.abs(cur-r) + dfs(i+1, l, cur));
        
        return dp[i][l][r] = min;
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