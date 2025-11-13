import java.io.*;
import java.util.*;

public class Main {
    
    private static int n;
    private static int[][] arr;
    private static int[][][] dp; // dr, dc, dir(0-가로,1-세로,2-대각선)
    
    public static void main(String... args) throws IOException {            
        n = readInt();
        
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) arr[i][j] = readInt();
        }
        
        dp = new int[n][n][3];
        dp[0][1][0] = 1;
        
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (arr[r][c] == 1) continue;
                    
                if (c-1>=0 && arr[r][c-1] == 0) {
                    dp[r][c][0] += dp[r][c-1][0];
                    dp[r][c][0] += dp[r][c-1][2];
                } 
                
                if (r-1>=0 && arr[r-1][c] == 0) {
                    dp[r][c][1] += dp[r-1][c][1];
                    dp[r][c][1] += dp[r-1][c][2];
                } 
                
                if (r-1>=0 && c-1>=0 && arr[r][c-1] == 0 && arr[r-1][c] == 0) {
                    dp[r][c][2] += dp[r-1][c-1][0];
                    dp[r][c][2] += dp[r-1][c-1][1];
                    dp[r][c][2] += dp[r-1][c-1][2];
                }
            }
        }
        
        int ans = 0;
        for (int i = 0; i < 3; i++) ans += dp[n-1][n-1][i];

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