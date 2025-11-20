import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int n, m, INF = Integer.MAX_VALUE;
    private static long ans;
    private static int[][] arr;
    private static long[][][] dp; // n, m, 방향(0-왼쪽대각, 1-위, 2-오른대각)
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        
        arr = new int[n][m];
        dp = new long[n][m][3];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) Arrays.fill(dp[i][j], INF);
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = readInt();
                if (i == 0) dp[i][j][0] = dp[i][j][1] = dp[i][j][2] = arr[i][j];
            }
        }        
        
        for (int r = 1; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (r-1>=0 && c-1>=0) dp[r][c][0] = Math.min(dp[r][c][0], Math.min(dp[r-1][c-1][1], dp[r-1][c-1][2]) + arr[r][c]);
                if (r-1>=0) dp[r][c][1] = Math.min(dp[r][c][1], Math.min(dp[r-1][c][0], dp[r-1][c][2]) + arr[r][c]);
                if (r-1>=0 && c+1<m) dp[r][c][2] = Math.min(dp[r][c][2], Math.min(dp[r-1][c+1][0], dp[r-1][c+1][1]) + arr[r][c]);
            }
        }
        
        ans = INF;
        for (int i = 0; i < m; i++) {
            ans = Math.min(ans, dp[n-1][i][0]);
            ans = Math.min(ans, dp[n-1][i][1]);
            ans = Math.min(ans, dp[n-1][i][2]);
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