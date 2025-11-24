import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int n, tc, INF = Integer.MAX_VALUE;
    private static int[][] arr;
    private static long[][] dp;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        while((n = readInt()) != 0) {
            arr = new int[n+1][5];
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= 3; j++) arr[i][j] = readInt();                
            }
            
            dp = new long[n+1][5];
            //for (int i = 0; i <= n; i++) Arrays.fill(dp[i], INF);
            dp[1][1] = INF;
            dp[1][2] = arr[1][2];
            dp[1][3] = arr[1][2] + arr[1][3];
            
            for (int i = 2; i <= n; i++) {
                for (int j = 1; j <= 3; j++) {
                    long min = INF;
                    min = Math.min(min, dp[i-1][j]);
                    if (j-1>= 1) {
                        min = Math.min(min, dp[i][j-1]);
                        min = Math.min(min, dp[i-1][j-1]);
                    }                        
                    
                    if (j+1<=3) min = Math.min(min, dp[i-1][j+1]);
                    
                    dp[i][j] = min + arr[i][j];
                }
            }
            
            sb.append((++tc) + ". " + dp[n][2]).append("\n");
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