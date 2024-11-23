import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static long[][] prefixSum;
    private static int[][] board;
    private static long ans, left, right, prev;
    private static int n, r2, c2;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();

        board = new int[n+1][n+1];
        prefixSum = new long[n+1][n+1];

        ans = -1000;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                board[i][j] = readInt();

                ans = Math.max(ans, board[i][j]);

                left = prefixSum[i-1][j];
                right = prefixSum[i][j-1];
                prev = prefixSum[i-1][j-1];

                prefixSum[i][j] = left + right - prev + board[i][j];
            }
        }

        for (int k = 2; k <= n; k++) {
            for (int r1 = 1; r1 <= n; r1++) {
                r2 = r1 + k;

                if (r2 > n) break;
                for (int c1 = 1; c1 <= n; c1++) {
                    c2 = c1 + k;

                    if (c2 > n) break;

                    ans = Math.max(ans, prefixSum[r2][c2] - prefixSum[r1-1][c2] - prefixSum[r2][c1-1] + prefixSum[r1-1][c1-1]);
                }
            }
        }

        bw.write(ans+"");

        bw.flush();
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
