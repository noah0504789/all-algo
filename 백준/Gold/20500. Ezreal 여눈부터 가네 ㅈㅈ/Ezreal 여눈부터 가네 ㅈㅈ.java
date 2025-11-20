import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, p = 1_000_000_007;
    private static long[][] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        dp = new long[n+1][3];
        dp[1][2] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i][0] = (dp[i-1][1] + dp[i-1][2]) % p;
            dp[i][1] = (dp[i-1][2] + dp[i-1][0]) % p;
            dp[i][2] = (dp[i-1][0] + dp[i-1][1]) % p;
        }

        System.out.print(dp[n][0]);
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