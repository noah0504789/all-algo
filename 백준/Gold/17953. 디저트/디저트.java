import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m;
    private static long max;
    private static int[][] arr;
    private static long[][] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        
        arr = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) arr[i][j] = readInt();
        }
        
        dp = new long[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < m; k++) {
                    dp[i][j] = Math.max(dp[i][j], (i-1>=0 ? dp[i-1][k] : 0) + ((j==k && m>0 && i>0) ? arr[j][i]/2 : arr[j][i]));
                }                
            }
        }
        
        for (int i = 0; i < m; i++) max = Math.max(max, dp[n-1][i]);
        
        System.out.print(max);
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