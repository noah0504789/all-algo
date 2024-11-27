import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static int[][][] cnts;
    private static int[] cur, left, up, prev;
    private static int n, q, r2, c2, r1, c1, cnt, ans;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();

        cnts = new int[n+1][n+1][11];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= 10; k++) cnts[i][j][k] = cnts[i-1][j][k] + cnts[i][j-1][k] - cnts[i-1][j-1][k];

                cnts[i][j][readInt()]++;
            }
        }

        q = readInt();

        for (int i = 0; i < q; i++) {
            r1 = readInt();
            c1 = readInt();
            r2 = readInt();
            c2 = readInt();

            cur = cnts[r2][c2];
            left = cnts[r2][c1-1];
            up = cnts[r1-1][c2];
            prev = cnts[r1-1][c1-1];

            ans = 0;
            for (int num = 1; num <= 10; num++) {
                cnt = cur[num];
                cnt -= left[num];
                cnt -= up[num];
                cnt += prev[num];

                if (cnt > 0) ans++;
            }

            bw.write(ans+"\n");
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
