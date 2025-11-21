import java.io.*;
import java.util.*;

public class Main {
    
    private static int n;
    private static int[] left, right;
    private static long[][] dp; // l, r
    
    public static void main(String... args) throws IOException {
        n = readInt();
        
        left = new int[n+1];
        for (int i = 1; i <= n; i++) left[i] = readInt();
        
        right = new int[n+1];
        for (int i = 1; i <= n; i++) right[i] = readInt();
        
        dp = new long[n+1][n+1];
        for (int i = 0; i <= n; i++) Arrays.fill(dp[i], -1);

        System.out.print(dfs(1, 1));
    }
    
    private static long dfs(int l, int r) {
        if (l > n || r > n) return 0;
        if (dp[l][r] != -1) return dp[l][r];
        
        long max = 0;
        max = Math.max(max, dfs(l+1, r));
        max = Math.max(max, dfs(l+1, r+1));
        if (left[l] > right[r]) max = Math.max(max, right[r] + dfs(l, r+1));
        
        return dp[l][r] = max;
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