import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static int[][] dp;
    private static int[] train, prefixSum;
    private static int n, cap;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        train = new int[n+1];
        prefixSum = new int[n+1];

        for (int i = 1; i <= n; i++) {
            train[i] = prefixSum[i] = readInt();
            prefixSum[i] += prefixSum[i-1];
        }

        cap = readInt();

        dp = new int[4][n+1];

        for (int i = 1; i <= 3; i++) {
            for (int j = i * cap; j <= n; j++) dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-cap]+prefixSum[j]-prefixSum[j-cap]);
        }
        
        bw.write(dp[3][n]+"");
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
