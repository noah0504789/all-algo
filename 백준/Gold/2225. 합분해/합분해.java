import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static final int MOD = 1_000_000_000;
    private static long[][] dp;
    private static int n, k;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        k = readInt();

        dp = new long[k+1][n+1];

        Arrays.fill(dp[1], 1);
        for (int i = 1; i <= k; i++) dp[i][0] = 1;

        for (int i = 2; i <= k; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % MOD;
            }
        }

        bw.write(dp[k][n]+"");
        bw.flush();
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
