import java.io.*;
import java.util.*;

public class Main {
    private static int[][] dp;
    private static int n, k;

    public static void main(String... args) throws IOException {
        n = readInt();
        k = readInt();
            
        dp = new int[n+1][k+1];

        System.out.print(comb(n, k));
    }

    public static int comb(int n, int k) {
        if (k == 0) return 1;
        if (n == 0) return k == 0 ? 1 : 0;
        if (n < k) return 0;
        
        if (dp[n][k] > 0) return dp[n][k];

        return dp[n][k] = comb(n-1, k) + comb(n-1, k-1);
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
