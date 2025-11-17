import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int n, m, s, d, c, INF = Integer.MAX_VALUE;
    private static int[][] arr, dp;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        n = readInt();
        m = readInt();
        dp = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dp[i], INF);
            dp[i][i] = 0;
        }
        
        //arr = new int[n+1][n+1];
        for (int i = 0; i < m; i++) {
            s = readInt();
            d = readInt();
            c = readInt();
            
            dp[s][d] = Math.min(dp[s][d], c);
        }
        
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                if (dp[i][k] == INF) continue;
                for (int j = 1; j <= n; j++) {
                    if (dp[k][j] == INF) continue;
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sb.append(dp[i][j] == INF ? 0 : dp[i][j]).append(" ");
            }
            sb.append("\n");
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