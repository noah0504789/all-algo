import java.io.*;
import java.util.*;

public class Main {
    
    private static int n;
    private static int[][] arr, dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) arr[i][j] = readInt();
        }
        
        dp = new int[n][n];
        
        System.out.print(dfs(0, 0));
    }
    
    private static int dfs(int r, int c) {
        if (r == n-1) return arr[r][c];
        if (dp[r][c] != 0) return dp[r][c];
        
        int max = 0;
        max = Math.max(max, arr[r][c] + dfs(r+1, c));
        if (c <= r) max = Math.max(max, arr[r][c] + dfs(r+1, c+1));
        
        return dp[r][c] = max;
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