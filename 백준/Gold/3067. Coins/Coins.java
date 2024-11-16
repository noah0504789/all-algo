import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static int[] dp, coins;
    private static int tc, n, m;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        tc = readInt();

        for (int i = 0; i < tc; i++) {
            n = readInt();

            coins = new int[n];
            for (int j = 0; j < n; j++) coins[j] = readInt();

            m = readInt();

            dp = new int[m+1];
            dp[0] = 1;
            for (int coin : coins) {
                for (int k = coin; k <= m; k++) {
                    dp[k] += dp[k - coin];
                }
            }

            bw.write(dp[m]+"");
            bw.write("\n");
        }

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
