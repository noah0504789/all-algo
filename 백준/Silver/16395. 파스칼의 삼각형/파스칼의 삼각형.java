import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, k;
    private static long[][] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        k = readInt();
        
        dp = new long[n][k];
        dp[0][0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i][0] = 1;
            for (int j = 1; j < k; j++) dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
        }

        System.out.print(dp[n-1][k-1]);
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