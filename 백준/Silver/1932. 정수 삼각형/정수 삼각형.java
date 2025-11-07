import java.io.*;
import java.util.*;

public class Main {
    
    private static int n;
    private static int[][] arr, dp;
    private static long ans;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        arr = new int[n][n];
        dp = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j<=i; j++) arr[i][j] = readInt();
        }
        
        dp[0][0] = arr[0][0];
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j<=i; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i-1][j] + arr[i][j];
                    continue;
                }
                
                dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + arr[i][j];
            }
        }
        
        for (int i = 0; i < n; i++) ans = Math.max(ans, dp[n-1][i]);

        System.out.print(ans);
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