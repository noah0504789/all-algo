import java.io.*;
import java.util.*;

public class Main {
    private static final int MOD = 10_007;
    
    private static int[][] dp;
    private static int n, k;

    public static void main(String... args) throws IOException {
        n = readInt();
        k = readInt();
        
        dp = new int[n+1][k+1];

        System.out.print(comb(n, k));
    }
    
    public static int comb(int n, int r) {
        if (n == 0 || r == 0 || n == r) return 1;
        if (r == 1) return n;
        if (dp[n][r] > 0) return dp[n][r];
        
        return dp[n][r] = (comb(n-1, r) + comb(n-1, r-1)) % MOD;
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
