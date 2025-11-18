import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m, max, INF = Integer.MAX_VALUE;
    private static long[] dp, left, right;
    private static int[][] arr;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) arr[i][j] = readInt();
        }
        
        dp = new long[m];
        dp[0] = arr[0][0];
        for (int i = 1; i < m; i++) dp[i] = dp[i-1]+arr[0][i];
        
        for (int i = 1; i < n; i++) {
            left = new long[m];
            right = new long[m];
            
            left[0] = arr[i][0] + dp[0];
            for (int j = 1; j < m; j++) left[j] = Math.max(left[j-1], dp[j]) + arr[i][j];
            
            right[m-1] = arr[i][m-1] + dp[m-1];
            for (int j = m-2; j >= 0; j--) right[j] = Math.max(right[j+1], dp[j]) + arr[i][j];
            
            for (int j = 0; j < m; j++) dp[j] = Math.max(left[j], right[j]);
        }

        System.out.print(dp[m-1]);
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