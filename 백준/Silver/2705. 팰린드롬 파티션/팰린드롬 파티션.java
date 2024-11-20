import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static int[] dp;
    private static int tc, n;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        tc = readInt();

        dp = new int[1001];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 0; i < tc; i++) {
            n = readInt();

            bw.write(count(n)+"");
            bw.write("\n");
        }

        bw.flush();
    }

    public static int count(int cur) {
        if (dp[cur] > 0) return dp[cur];

        return dp[cur] = count(cur-1) + (cur % 2 == 0 ? count(cur/2) : 0);
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
