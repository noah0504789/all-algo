import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m, k, a, b;
    private static int[][] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        k = readInt();
        if (k == 0) {
            dp = new int[n+1][m+1];        
            for (int r = 1; r <= n; r++) {
                for (int c = 1; c <= m; c++) {
                    if (r == 1 && c == 1) {dp[r][c] = 1; continue;}
                    dp[r][c] = dp[r-1][c] + dp[r][c-1];
                }
            }
            
            System.out.print(dp[n][m]);
            return;  
        } 
        
        dp = new int[n+1][m+1];        
        for (int r = 1; r <= (k/m)+1; r++) {
            for (int c = 1; c <= k%m; c++) {
                if (r == 1 && c == 1) {dp[r][c] = 1; continue;}
                dp[r][c] = dp[r-1][c] + dp[r][c-1];
            }
        }
        
        a = dp[(k/m)+1][k%m];
        
        dp = new int[n+1][m+1];
        for (int r = (k/m)+1; r <= n; r++) {
            for (int c = k%m; c <= m; c++) {
                if (r == (k/m)+1 && c == k%m) {dp[r][c] = 1; continue;}
                dp[r][c] = dp[r-1][c] + dp[r][c-1];
            }
        }
        
        System.out.print(a * dp[n][m]);
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