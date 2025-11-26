import java.io.*;
import java.util.*;

public class Main {
    
    private static int n;
    private static long[] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        
        dp = new long[21];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        dp[4] = 7;
        for (int i = 5; i <= n; i++) {
            dp[i] = dp[i-1] * 2;
            if (i % 2 == 0) dp[i] -= (dp[i-4] + dp[i-5]);
        }

        System.out.print(dp[n]);
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