import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static long[][] prefixSum;
    private static int[][] board;
    private static long all, left, right, prev, max, sum, last;
    private static int n, m;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        m = readInt();

        board = new int[n+1][m+1];
        prefixSum = new long[n+1][m+1];

        max = Integer.MIN_VALUE;

        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= m; c++) {
                board[r][c] = readInt();

                left = prefixSum[r-1][c];
                right = prefixSum[r][c-1];
                prev = prefixSum[r-1][c-1];

                prefixSum[r][c] = left+right-prev+board[r][c];
            }
        }

        for (int c1 = 1; c1 <= m; c1++) {
            for (int c2 = c1; c2 <= m; c2++) {
                last = 0;
                for (int r = 1; r <= n; r++) {
                    all = prefixSum[r][c2];
                    left = prefixSum[r-1][c2];
                    right = prefixSum[r][c1-1];
                    prev = prefixSum[r-1][c1-1];

                    sum = all-left-right+prev;

                    last = Math.max(sum, sum+last);
                    max = Math.max(max, last);
                }
            }
        }

        bw.write(max +"");

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
