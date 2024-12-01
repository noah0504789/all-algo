import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static final int MOD = 9901;
    private static int[][] dp;
    private static int n, ans;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();

        dp = new int[n+1][3];
        dp[1][0] = dp[1][1] = dp[1][2] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % MOD;
            dp[i][1] = (dp[i-1][0] + dp[i-1][2]) % MOD;
            dp[i][2] = (dp[i-1][0] + dp[i-1][1]) % MOD;
        }

        ans = (dp[n][0] + dp[n][1] + dp[n][2]) % MOD;

        bw.write(ans+"");
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
