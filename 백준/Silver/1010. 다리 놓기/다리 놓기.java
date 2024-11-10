import java.io.*;
import java.util.*;

public class Main {
    private static StringBuilder sb;

    private static int[][] dp;
    private static int tc, n, r;

    public static void main(String... args) throws IOException {
        sb = new StringBuilder();

        tc = readInt();

        for (int i = 0; i < tc; i++) {
            r = readInt();
            n = readInt();

            dp = new int[n+1][r+1];

            sb.append(comb(n, r)).append("\n");
        }

        System.out.print(sb);
    }

    public static int comb(int n, int r) {
        if (r > n) return 0;
        if (n == 0 || r == 0 || n == r) return 1;
        if (dp[n][r] > 0) return dp[n][r];

        return dp[n][r] = comb(n-1, r) + comb(n-1, r-1);
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
