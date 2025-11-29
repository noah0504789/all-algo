import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, INF = Integer.MAX_VALUE;
    private static int[][] matrix, dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        
        matrix = new int[n][2];
        for (int i = 0; i < n; i++) {
            matrix[i][0] = readInt();
            matrix[i][1] = readInt();
        }
        
        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], INF);
            dp[i][i] = 0;
        }
        
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i+len-1;
                
                int min = INF;
                
                for (int k = i; k < j; k++) min = Math.min(min, dp[i][k] + dp[k+1][j] + matrix[i][0] * matrix[k][1] * matrix[j][1]);
                
                dp[i][j] = min;
            }
        }

        System.out.print(dp[0][n-1]);
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