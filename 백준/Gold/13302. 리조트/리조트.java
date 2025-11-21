import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m, INF = Integer.MAX_VALUE;
    private static boolean[] closed;
    private static int[][] dp; // n일차,남은쿠폰수
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        closed = new boolean[n+1];
        for (int i = 0; i < m; i++) closed[readInt()] = true;
        
        dp = new int[n+1][n+1];
        for (int i = 0; i <= n; i++) Arrays.fill(dp[i], INF);

        System.out.print(dfs(1, 0));
    }
    
    private static int dfs(int d, int c) {        
        if (d > n) return 0;
        if (dp[d][c] != INF) return dp[d][c];
        
        int res = INF;
        if (closed[d]) {
            res = dfs(d+1, c);
        } else {
            res = Math.min(res, 10000+dfs(d+1, c));
            res = Math.min(res, 25000+dfs(d+3, c+1));
            res = Math.min(res, 37000+dfs(d+5, c+2));
            if (c>=3) res = Math.min(res, dfs(d+1, c-3));    
        }
        
        return dp[d][c] = res;
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