import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static int[][] dp, back;
    private static int[] a, b;
    private static int n, m, ans, sa, sb;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        a = new int[n];
        for (int i = 0; i < n; i++) a[i] = readInt();

        m = readInt();
        b = new int[m];
        for (int i = 0; i < m; i++) b[i] = readInt();

        dp = new int[n][m];
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], -1);

        back = new int[n][m];

        ans = 0;
        sa = sb = -1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i] != b[j]) continue;

                int temp = lcis(i, j);
                if (ans >= temp) break;

                ans = temp;
                sa = i;
                sb = j;

                break;
            }
        }

        bw.write(ans+"\n");

        if (sa != -1 && sb != -1) trace(sa, sb);

        bw.flush();
    }

    private static int lcis(int aPos, int bPos) {
        if (aPos == n || bPos == m) return 0;
        if (dp[aPos][bPos] != -1) return dp[aPos][bPos];

        dp[aPos][bPos] = 1;
        back[aPos][bPos] = n * 1000 + m;

        for (int i = aPos+1; i < n; i++) {
            if (a[aPos] >= a[i]) continue;

            for (int j = bPos+1; j < m; j++) {
                if (a[i] != b[j]) continue;

                int temp = lcis(i, j) + 1;
                if (dp[aPos][bPos] >= temp) break;

                dp[aPos][bPos] = temp;
                back[aPos][bPos] = i * 1000 + j;
                
                break;
            }
        }

        return dp[aPos][bPos];
    }

    private static void trace(int aPos, int bPos) throws IOException {
        if (aPos == n || bPos == m) return;

        bw.write(a[aPos] + " ");

        int next = back[aPos][bPos];

        trace(next / 1000, next % 1000);
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
