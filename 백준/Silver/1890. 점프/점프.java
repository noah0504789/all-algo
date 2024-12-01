import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static long[][] dp;
    private static int[][] board;
    private static int n, jump;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();

        board = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) board[i][j] = readInt();
        }

        dp = new long[n+1][n+1];
        dp[1][1] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                jump = board[i][j];
                if (jump == 0) break;

                if (i+jump <= n) dp[i+jump][j] += dp[i][j];
                if (j+jump <= n) dp[i][j+jump] += dp[i][j];
            }
        }

        bw.write(dp[n][n]+"");
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
