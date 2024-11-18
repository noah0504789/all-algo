import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;
    private static StringBuilder sb;

    private static String cur;
    private static int[][] invests, dp, path;
    private static int n, m, company;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        n = readInt(); // 총 투자금액
        m = readInt(); // 회사 수

        invests = new int[m+1][n+1];

        for (int i = 1; i <= n; i++) {
            company = readInt();
            for (int j = 1; j <= m; j++) invests[j][company] = readInt();
        }

        dp = new int[m+1][n+1];
        path = new int[m+1][n+1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 0; k <= j; k++) {
                    int val = dp[i-1][j-k] + invests[i][k];
                    if (dp[i][j] >= val) continue;

                    dp[i][j] = val;
                    path[i][j] = k;
                }
            }
        }

        bw.write(dp[m][n] + "\n");

        for (int i = m; i > 0; i--) {
            sb.insert(0, path[i][n] + " ");
            n -= path[i][n];
        }

        bw.write(sb.toString());

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