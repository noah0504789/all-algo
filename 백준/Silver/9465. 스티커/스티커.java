import java.io.*;
import java.util.*;

public class Main {
        
    private static StringBuilder sb = new StringBuilder();
    private static int tc, n;
    private static int[][] arr, dp;
    
    public static void main(String... args) throws IOException {
        tc = readInt();
        while(tc-- > 0) {
            n = readInt();
            arr = new int[2][n+1];
            for (int i = 1; i <= n; i++) arr[0][i] = readInt();            
            for (int i = 1; i <= n; i++) arr[1][i] = readInt();
            
            dp = new int[3][n+1];
            for (int i = 1; i <= n; i++) {
                dp[0][i] = Math.max(dp[0][i-1], Math.max(dp[1][i-1], dp[2][i-1]));
                dp[1][i] = Math.max(dp[0][i-1], dp[2][i-1]) + arr[0][i];
                dp[2][i] = Math.max(dp[0][i-1], dp[1][i-1]) + arr[1][i];
            }
            
            sb.append(Math.max(dp[0][n], Math.max(dp[1][n], dp[2][n]))).append("\n");
        }
        
        System.out.print(sb);
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