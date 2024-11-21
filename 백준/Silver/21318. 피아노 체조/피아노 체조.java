import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static int[] level, dp;
    private static int n, q, src, dest;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();

        level = new int[n];

        for (int i = 0; i < n; i++) level[i] = readInt();

        dp = new int[n];
        dp[0] = 0;

        for (int i = 1; i < n; i++) dp[i] = dp[i-1] + (level[i] < level[i-1] ? 1 : 0);

        q = readInt();

        for (int i = 0; i < q; i++) {
            src = readInt() - 1;
            dest = readInt() - 1;

            bw.write((dp[dest] - dp[src]) +"\n");
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
