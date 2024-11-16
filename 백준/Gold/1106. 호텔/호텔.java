import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static int[] dp;
    private static int c, n, cost, customer, ans;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        c = readInt();
        n = readInt();

        dp = new int[c+101];
        Arrays.fill(dp, 987654321);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            cost = readInt();
            customer = readInt();

            for (int j = customer; j <= c + 100; j++) dp[j] = Math.min(dp[j], dp[j-customer]+cost);
        }

        ans = Integer.MAX_VALUE;

        for (int i = c; i <= c + 100; i++) ans = Math.min(ans, dp[i]);

        bw.write(ans+"");
        bw.write("\n");

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
