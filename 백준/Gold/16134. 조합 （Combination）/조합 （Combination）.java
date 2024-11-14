import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static final int MOD = 1_000_000_007;
    private static int[][] dp;
    private static int n, r;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        r = readInt();

        dp = new int[n+1][r+1];

        bw.write(comb(n, r)+"");

        bw.flush();
    }

    private static int comb(int n, int r) {
        if (n == 0) return 0;
        if (r == 0 || n == r) return 1;
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
