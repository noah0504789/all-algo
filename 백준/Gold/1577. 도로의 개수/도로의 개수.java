import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m, k, r1, c1, r2, c2, d;
    private static boolean[][] block_right, block_up;
    private static long[][] dp; // r, c, d(도착)
    private static int[][] dir = {
        {0, 1}, {1, 0} // r, u
    };
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        k = readInt();
                
        block_right = new boolean[n+1][m+1];
        block_up = new boolean[n+1][m+1];
        for (int i = 0; i < k; i++) {
            r1 = readInt();
            c1 = readInt();
            r2 = readInt();
            c2 = readInt();
            
            if (r1 == r2) block_right[r2][Math.max(c1, c2)] = true;
            else block_up[Math.max(r1, r2)][c1] = true;
        }
        
        dp = new long[n+1][m+1];
        dp[0][0] = 1;        
        for (int r = 0; r <= n; r++) {
            for (int c = 0; c <= m; c++) {
                if (r == 0 && c == 0) continue;
                
                if (c-1>=0 && !block_right[r][c]) dp[r][c] += dp[r][c-1];                
                if (r-1>=0 && !block_up[r][c]) dp[r][c] += dp[r-1][c];
            }
        }
        
        System.out.print(dp[n][m]);
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