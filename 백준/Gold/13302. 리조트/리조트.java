import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m, INF = Integer.MAX_VALUE;
    // 날짜,남은쿠폰수
    private static int[][] dp;
    private static boolean[] x;    
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        
        x = new boolean[n+1];
        for (int i = 0; i < m; i++) x[readInt()] = true;
        
        dp = new int[n+1][n+1];
        for (int i = 0; i <= n; i++) Arrays.fill(dp[i], INF);
        
        System.out.print(solve(1, 0));
    }
    
    private static int solve(int d, int c) {
        if (d > n) return 0;
        if (dp[d][c] != INF) return dp[d][c];
        
        int res = INF;
        
        if (x[d]) {res = solve(d+1, c);}
        else {
            res = Math.min(res, 10_000 + solve(d+1, c));
            res = Math.min(res, 25_000 + solve(d+3, c+1));
            res = Math.min(res, 37_000 + solve(d+5, c+2));
            if (c>=3) res = Math.min(res, solve(d+1, c-3));
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