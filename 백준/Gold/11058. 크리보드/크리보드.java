import java.io.*;
import java.util.*;

public class Main {
    
    private static int n;
    private static long[] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        
        dp = new long[n+1];
        
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i-1]+1;
            for (int j = 3; j <= i; j++) dp[i] = Math.max(dp[i], dp[i-j] * (j-1));
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