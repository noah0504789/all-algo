import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static int[] dp;
    private static int a, b, d, n;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        a = readInt();
        b = readInt();
        d = readInt();
        n = readInt();

        dp = new int[n+1];
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i-1];

            if (i >= a) dp[i] += dp[i-a];
            dp[i] %= 1000;

            if (i >= b) dp[i] -= dp[i-b];
            if (dp[i] < 0) dp[i] += 1000;
            dp[i] %= 1000;
        }

        if (n >= d) dp[n] -= dp[n-d];
        if (dp[n] < 0) dp[n] += 1000;

        dp[n] %= 1000;

        bw.write(dp[n] + "");
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
