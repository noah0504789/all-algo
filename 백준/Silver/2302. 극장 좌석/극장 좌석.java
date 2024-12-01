import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static int[] dp;
    private static int n, m, prev, ans, vip;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        m = readInt();

        dp = new int[n+1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; i++) dp[i] = dp[i-1] + dp[i-2];

        ans = 1;
        prev = 0;

        for (int i = 0; i < m; i++) {
            vip = readInt();
            
            ans *= dp[vip-prev-1];
            prev = vip;
        }

        ans *= dp[n-prev];

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
