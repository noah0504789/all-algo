import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, p = 15_746;
    private static long[] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        dp = new long[Math.max(2, n)+1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) dp[i] = (dp[i-1] + dp[i-2]) % p;
        
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