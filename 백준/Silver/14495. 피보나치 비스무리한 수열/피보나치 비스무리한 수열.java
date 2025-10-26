import java.io.*;
import java.util.*;

public class Main {
    
    private static int n;
    private static long[] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        dp = new long[n+1];
        
        System.out.print(p(n));
    }
    
    private static long p(int n) {
        if (n == 1 || n == 2 || n == 3) return 1;
        if (dp[n] > 0) return dp[n];
        
        return dp[n] = p(n-1) + p(n-3);
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