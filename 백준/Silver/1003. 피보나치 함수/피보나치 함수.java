import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;
    private static int[][] dp;
    private static int tc, n;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        tc = readInt();

        dp = new int[41][2];

        for (int i = 0; i < tc; i++) {
            n = readInt();

            fibDP(n);

            bw.write(dp[n][0] + " " + dp[n][1]);
            bw.write("\n");
        }

        bw.flush();
    }

    private static int[] fibDP(int n) {
        if (n == 0) {
            dp[0][0] = 1;
            return dp[0];
        }

        if (n == 1) {
            dp[1][1] = 1;
            return dp[1];
        }

        if (dp[n][0] > 0 && dp[n][1] > 0) return dp[n];
        
        int[] one = fibDP(n-1), two = fibDP(n-2);

        dp[n][0] = one[0] + two[0];
        dp[n][1] = one[1] + two[1];

        return dp[n];
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
