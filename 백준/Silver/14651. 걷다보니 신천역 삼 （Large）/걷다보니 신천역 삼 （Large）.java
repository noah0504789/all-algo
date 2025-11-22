import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, p = 1_000_000_009;
    private static long[][] dp; // n자리, 자리수 합(0, 1, 2) 나머지 3
    
    public static void main(String... args) throws IOException {
        n = readInt();
        
        if (n == 1) {
            System.out.print(0);
            return;
        }
        
        dp = new long[n+1][3];
        dp[1][0] = 0;
        dp[1][1] = dp[1][2] = 1;
        for (int i = 2; i <= n; i++) {
            long sum = (dp[i-1][0] + dp[i-1][2] + dp[i-1][1]) % p;
            dp[i][0] = dp[i][1] = dp[i][2] = sum;
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