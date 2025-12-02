import java.io.*;
import java.util.*;

public class Main {
        
    private static int n;
    private static long[][] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        
        dp = new long[n][2];
        dp[0][1] = 1;
        // 1
        // 10
        // 100, 101
        
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i-1][0] + dp[i-1][1];
            dp[i][1] = dp[i-1][0];
        }
        
        System.out.print(dp[n-1][0] + dp[n-1][1]);
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