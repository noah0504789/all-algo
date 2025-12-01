import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, ans, p = 1_000_000_000;
    private static int[][] dp, adj = {
        {1}, {0, 2}, {1, 3}, {2, 4}, {3, 5}, {4, 6}, {5, 7}, {6, 8}, {7, 9}, {8}
    };
    
    public static void main(String... args) throws IOException {
        n = readInt();
        
        dp = new int[n+1][10];
        Arrays.fill(dp[1], 1);
        dp[1][0] = 0;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int prev : adj[j]) dp[i][j] = (dp[i][j] + dp[i-1][prev]) % p;
            }
        }
        
        for (int i = 0; i <= 9; i++) ans = (ans + dp[n][i]) % p;
        
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