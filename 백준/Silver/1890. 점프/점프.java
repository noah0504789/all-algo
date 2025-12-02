import java.io.*;
import java.util.*;

public class Main {
    
    private static int n;
    private static int[][] arr;
    private static long[][] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        arr = new int[n+1][n+1];
        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= n; c++) arr[r][c] = readInt();
        }
        
        dp = new long[n+1][n+1];       
        for (int i = 0; i <= n; i++) Arrays.fill(dp[i], -1);
        
        System.out.print(dfs(1, 1));
    }
    
    private static long dfs(int r, int c) {
        if (r == n && c == n) return 1;
        if (dp[r][c] != -1) return dp[r][c];
        
        int v = arr[r][c];
        
        return dp[r][c] = v > 0 ? (r+v<=n ? dfs(r+v, c) : 0) + (c+v<=n ? dfs(r, c+v) : 0) : 0;
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