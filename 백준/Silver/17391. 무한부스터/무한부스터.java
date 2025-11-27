import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m, INF = Integer.MAX_VALUE;
    private static int[][] arr, dir = {
        {0,1},{1,0}
    };
    private static int[][] dp; //i, j점에 d방향으로 왔을때 최소 칸
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        arr = new int[n][m];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) arr[r][c] = readInt();
        }
        
        dp = new int[n][m];
        for (int r = 0; r < n; r++) Arrays.fill(dp[r], INF);
        dp[0][0] = 0;
        
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {                
                for (int k = 1; k <= arr[r][c]; k++) {                     
                    if (r+k<n) dp[r+k][c] = Math.min(dp[r+k][c], dp[r][c] + 1);
                    if (c+k<m) dp[r][c+k] = Math.min(dp[r][c+k], dp[r][c] + 1);                    
                }
            }
        }

        System.out.print(dp[n-1][m-1]);
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