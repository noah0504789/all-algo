import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static long[][] dp;
    private static long[] acc;
    private static int[] nums;
    private static long target;
    private static int n;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();

        nums = new int[n+1];
        acc = new long[n+1];

        for (int i = 1; i <= n; i++) {
            acc[i] = readInt();
            acc[i] += acc[i-1];
        }

        target = acc[n] / 4;
        dp = new long[n+1][4];

        bw.write(solve()+"");
        bw.flush();
    }

    private static long solve() {
        if (target % 4 != 0) return 0;

        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;

            for (int j = 1; j <= 3; j++) {
                dp[i][j] = dp[i-1][j];

                if (acc[i] != target * j) continue;

                dp[i][j] += dp[i-1][j-1];
            }
        }

        return dp[n-1][3];
    }

    public static int readInt() throws IOException {
        int r = 0, c = System.in.read();

        while (c <= ' ') c = System.in.read();
        boolean negative = false;
        if (c == '-') {
            negative = true;
            c = System.in.read();
        }
        while (c >= '0' && c <= '9') {
            r *= 10;
            r += c - '0';
            c = System.in.read();
        }

        return negative ? -r : r;
    }
}
