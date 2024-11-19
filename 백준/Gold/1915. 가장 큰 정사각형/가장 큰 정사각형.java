import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static int[][] board, dp;
    private static int n, m, max;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();        
        m = readInt();   
        
        board = new int[n+1][m+1];
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) board[i][j] = readBit();
        }
        
        max = 0;
        
        dp = new int[n+1][m+1];
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (board[i][j] == 0) continue;
                
                if (dp[i-1][j] == dp[i][j-1] && dp[i-1][j] == dp[i-1][j-1]) dp[i][j] = dp[i][j-1] + 1;
                else dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                
                max = Math.max(max, dp[i][j]);
            }
        }
        
        bw.write(max*max+"");

        bw.flush();
    }

    public static int readInt() throws IOException {
        int r = 0, c = System.in.read();

        while (c <= ' ') c = System.in.read();
        while (c >= '0' && c <= '9') {
            r *= 10;
            r += c - '0';
            c = System.in.read();
        }

        return r;
    }
    
    public static int readBit() throws IOException {
        int c = System.in.read();

        while (c <= ' ') c = System.in.read();

        return c - '0';
    }
}
