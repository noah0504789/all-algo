import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static int[][] dp;
    private static int[] hp, joy;
    private static int n;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();

        hp = new int[n+1];
        for (int i = 1; i <= n; i++) hp[i] = readInt();

        joy = new int[n+1];
        for (int i = 1; i <= n; i++) joy[i] = readInt();

        dp = new int[n+1][100];
        for (int i = 1; i <= n; i++) {
            int ch = hp[i], ph = hp[i-1], cj = joy[i];

            for (int j = 1; j < 100; j++) {
                if (j >= ch) dp[i][j] = Math.max(Math.max(dp[i][j-1], dp[i-1][j]), dp[i-1][j-ch] + cj);
                else dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
            }
        }

        bw.write(dp[n][99]+"");

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
