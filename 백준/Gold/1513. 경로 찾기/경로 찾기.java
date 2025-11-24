import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int n, m, c_, p = 1_000_007, INF = Integer.MAX_VALUE;
    private static int[][] stations, dir = {
        {0, 1}, {1, 0}
    };
    private static long[][][][] dp;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        n = readInt();
        m = readInt();
        c_ = readInt();
        
        stations = new int[n+1][m+1];
        for (int i = 1; i <= c_; i++) stations[readInt()][readInt()] = i;

        dp = new long[n+1][m+1][c_+1][c_+1];
        int first = stations[1][1];
        if (first == 0) dp[1][1][0][0] = 1;
        else dp[1][1][1][first] = 1;
        
        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= m; c++) {
                if (r == 1 && c == 1) continue;
                
                int cur = stations[r][c];
                
                for (int k = 0; k <= c_; k++) {
                    for (int last = 0; last <= c_; last++) {
                        long up = dp[r-1][c][k][last];
                        long left = dp[r][c-1][k][last];
                        long v = (up + left) % p;
                        if (v == 0) continue;
                        
                        if (cur == 0) dp[r][c][k][last] = (dp[r][c][k][last] + v) % p;
                        else if (last < cur && k+1 <= c_) dp[r][c][k+1][cur] = (dp[r][c][k+1][cur] + v) % p;
                    }
                }
            }
        }
        
        for (int k = 0; k <= c_; k++) {
            long sum = 0;
            for (int last = 0; last <= c_; last++) sum = (sum + dp[n][m][k][last]) % p;
            sb.append(sum).append(" ");    
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