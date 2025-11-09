import java.io.*;
import java.util.*;

public class Main {
    
    private static int n;
    private static int[][] arr;
    private static long[][] dp;
    private static long INF = Long.MAX_VALUE;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) arr[i][j] = readInt();
        }
        dp = new long[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], INF);
        dp[n-1][n-1] = 0;
        for (int i = n-1; i>=0; i--) {
            for (int j = n-1; j>=0; j--) {
                if (i == n-1 && j == n-1) continue;
                
                long best = INF;
                
                if (j+1<n) best = Math.min(best, Math.max(0, arr[i][j+1] - arr[i][j]+1) + dp[i][j+1]);                
                if (i+1<n) best = Math.min(best, Math.max(0, arr[i+1][j] - arr[i][j]+1) + dp[i+1][j]);
                
                dp[i][j] = best;
            }
        }       

        System.out.print(dp[0][0]);
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