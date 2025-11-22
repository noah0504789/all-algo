import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m, p = 1_000_000_007;
    private static int[] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();

        dp = new int[n+1];      
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i-1] + (i-m>=0 ? dp[i-m] : 0);
            dp[i] %= p;
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