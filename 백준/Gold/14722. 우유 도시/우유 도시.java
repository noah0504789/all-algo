import java.io.*;
import java.util.*;

public class Main {
        
    private static int n;
    private static long ans;
    private static int[][] arr;
    private static long[][] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) arr[i][j] = readInt();
        }
        
        dp =  new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {                
                long best = 0L;
                if (i > 0) best =Math.max(best, dp[i-1][j]);
                if (j > 0) best =Math.max(best, dp[i][j-1]);
                
                if (arr[i][j] == best % 3) best++;
                 
                dp[i][j] = best;
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