import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static final int[] coins = {1, 2, 5, 7};
    private static int[] dp;
    private static int n, coin;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();

        if (n == 0) {
            bw.write("0");
            bw.flush();
            return;
        }

        dp = new int[n+1];
        for (int i = 1; i <= n; i++) dp[i] = i;

        for (int i = 1; i < 4; i++) {
            coin = coins[i];
            for (int j = 1; j <= n; j++) {
                if (j>=coin) dp[j] = Math.min(dp[j], dp[j-coin] + 1);
            }
        }

        bw.write(dp[n]+"");
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
