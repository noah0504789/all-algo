import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static final int INF = 1_000 * 1_000 + 1;
    private static int[][] colors, dp;
    private static int n, min;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();

        colors = new int[n+1][3];
        for (int i = 1; i <= n; i++) {
            colors[i][0] = readInt();
            colors[i][1] = readInt();
            colors[i][2] = readInt();
        }

        min = INF;

        for (int start = 0; start < 3; start++) {
            dp = new int[n+1][3];

            // 첫 집을 i로 칠하기
            for (int i = 0; i < 3; i++) dp[1][i] = (i == start) ? colors[1][i] : INF;

            // 2일 ~ n-1일까지 칠하기
            for (int i = 2; i <= n; i++) {
               dp[i][0] = colors[i][0] + Math.min(dp[i-1][1], dp[i-1][2]);
               dp[i][1] = colors[i][1] + Math.min(dp[i-1][0], dp[i-1][2]);
               dp[i][2] = colors[i][2] + Math.min(dp[i-1][0], dp[i-1][1]);
            }

            for (int end = 0; end < 3; end++) {
                if (start != end) min = Math.min(min, dp[n][end]);
            }
        }

        bw.write(min+"");
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
