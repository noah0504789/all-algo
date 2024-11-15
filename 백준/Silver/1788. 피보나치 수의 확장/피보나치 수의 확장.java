import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static final int MOD = 1_000_000_000;
    private static int[] dp;
    private static int n, nAbs, sign, abs;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        nAbs = Math.abs(n);

        if (n == 0) {
            sign = abs = 0;
        } else {
            dp = new int[nAbs+1];
            abs = fibDP(nAbs);
            sign = 1;

            if (n < 0 && nAbs % 2 == 0) sign = -1;
        }

        bw.write(sign+"");
        bw.write("\n");
        bw.write(Math.abs(abs)+"");

        bw.flush();
    }

    public static int fibDP(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        if (dp[n] > 0) return dp[n];

        return dp[n] = (fibDP(n-1) + fibDP(n-2)) % MOD;
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
