import java.io.*;

public class Main {
    private static BufferedWriter bw;

    private static long[][] dp;
    private static int[][] jumps;
    private static int n, k;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();

        jumps = new int[3][30];
        for (int i = 1; i < n; i++) {
            jumps[1][i] = readInt();
            jumps[2][i] = readInt();
        }

        k = readInt();

        dp = new long[2][30];
        for (int i = 2; i <= n; i++) dp[0][i] = dp[1][i] = Integer.MAX_VALUE;

        dp[0][2] = jumps[1][1];
        dp[0][3] = Math.min(jumps[2][1], jumps[1][1] + jumps[1][2]);

        for (int i = 4; i <= n; i++) {
            dp[0][i] = Math.min(dp[0][i], Math.min(dp[0][i-1]+jumps[1][i-1], dp[0][i-2]+jumps[2][i-2]));
            dp[1][i] = Math.min(dp[1][i], dp[0][i-3]+k);
            dp[1][i] = Math.min(dp[1][i], Math.min(dp[1][i-1]+jumps[1][i-1], dp[1][i-2]+jumps[2][i-2]));
        }

        bw.write(Math.min(dp[0][n], dp[1][n])+"");
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
