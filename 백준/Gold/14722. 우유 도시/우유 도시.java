import java.io.*;
import java.util.*;

public class Main {
    
    private static int n;
    private static int[][] arr;
    private static long[][] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        arr = new int[n][n];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) arr[r][c] = readInt();
        }
                
        dp = new long[n][n];
        //dp[0][0] = 1;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                //if (r == 0 && c == 0) continue;
                
                long u = r-1>=0 ? dp[r-1][c] : 0;
                long l = c-1>=0 ? dp[r][c-1] : 0;                
                long max = Math.max(u, l);
                if (max % 3 == arr[r][c]) max++;
                
                dp[r][c] = max;
            }
        }

        System.out.print(dp[n-1][n-1]);
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