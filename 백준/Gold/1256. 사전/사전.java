import java.io.*;
import java.util.*;

public class Main {
    private static StringBuilder sb;
    private static long[][] dp;
    private static int n, m, k;

    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        n = readInt();
        m = readInt();
        k = readInt();
        
        dp = new long[n+m+1][n+m+1];

        dp[0][0] = 1;
        
        for (int i = 1; i <= n+m; i++) {
            dp[i][0] = dp[i][i] = 1;
            
            for (int j = 1; j < i; j++) {
                dp[i][j] = dp[i-1][j] + dp[i-1][j-1];
                
                if (dp[i][j] > 1_000_000_000) dp[i][j] = 1_000_000_001;
            }
        }
        
        if (k > dp[n+m][n]) {
            System.out.print(-1);
            System.exit(0);
        }
        
        while (n > 0 || m > 0) {
            if (k <= dp[n+m-1][m]) {
                sb.append('a');
                n--;
            } else {
                sb.append('z');                
                k -= dp[n+m-1][m];

                m--;
            }
        }
        
        System.out.print(sb);
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
}
