import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int m, n;
    private static int[][] arr;
    private static long[] rowBest, dp;
    private static long ans;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        while ((m = readInt()) != 0 && (n = readInt()) != 0) {
            arr = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) arr[i][j] = readInt();
            }
            
            rowBest = new long[m];
            for (int i = 0; i < m; i++) rowBest[i] = maxNonAdj(arr[i]);
            
            if (m == 1) {
                ans = rowBest[0];
            } else {
                dp = new long[m];
                dp[0] = rowBest[0];
                dp[1] = Math.max(rowBest[0], rowBest[1]);
                for (int i = 2; i < m; i++) dp[i] = Math.max(dp[i-1], dp[i-2] + rowBest[i]);
                
                ans = dp[m-1];
            }
            
            sb.append(ans+"\n");
        }

        System.out.print(sb);
    }
    
    private static long maxNonAdj(int[] arr) {
        int n = arr.length;
        if (n==1) return arr[0];
        
        long[] dp = new long[n];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);
        for (int i = 2; i < n; i++) dp[i] = Math.max(dp[i-1], dp[i-2] + arr[i]);
        
        return dp[n-1];
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