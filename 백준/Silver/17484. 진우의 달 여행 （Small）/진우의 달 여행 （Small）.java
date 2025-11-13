import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m, INF = Integer.MAX_VALUE;
    private static int[][] arr;
    private static int[][][] dp; // 0-위, 1-왼쪽대각, 2-오른쪽대각
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        
        arr = new int[n][m];        
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) arr[r][c] = readInt();
        }
        
        dp = new int[n][m][3];
        for (int i = 0; i < n; i++) 
            for (int j = 0; j < m; j++) Arrays.fill(dp[i][j], INF);
        
        for (int c = m-1; c >= 0; c--) dp[n-1][c][0] = dp[n-1][c][1] = dp[n-1][c][2] = arr[n-1][c];
            
        for (int r = n-2; r >= 0; r--) {
            for (int c = m-1; c >= 0; c--) {
                dp[r][c][0] = Math.min(dp[r][c][0], Math.min(dp[r+1][c][1], dp[r+1][c][2]) + arr[r][c]);
                if (c-1>=0) {
                    dp[r][c][1] = Math.min(dp[r][c][1], Math.min(dp[r+1][c-1][0], dp[r+1][c-1][2]) + arr[r][c]);
                }
                
                if (c+1<m) {
                    dp[r][c][2] = Math.min(dp[r][c][2], Math.min(dp[r+1][c+1][0], dp[r+1][c+1][1]) + arr[r][c]);
                }
            }
        }
        
        int ans = INF;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 3; j++) ans = Math.min(ans, dp[0][i][j]);            
        }

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