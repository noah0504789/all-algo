import java.io.*;

public class Main {
    private static BufferedWriter bw;

    private static long[] sumRow, sumCol;
    private static int[][] board;
    private static long max, sum;
    private static int n, m, start, end;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        m = readInt();

        board = new int[n+1][m+1];
        sumRow = new long[n+1];
        sumCol = new long[m+1];

        max = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) board[i][j] = readInt();
        }

        for (int i = 1; i <= n; i++) {
            if (i == 1 || i == n) sumRow[i] = getRowSum(i);
            else sumRow[i] = getRowSum(i) * 2;

            sum += sumRow[i];
        }

        for (int i = 1; i <= m; i++) {
            if (i == 1 || i == m) sumCol[i] = getColSum(i);
            else sumCol[i] = getColSum(i) * 2;
        }

        max = sum;

        start = 1;
        end = n;

        for (int i = 2; i < end; i++) {
            max = Math.max(max, sum + sumRow[start] - sumRow[i]/2);
            max = Math.max(max, sum + sumRow[end] - sumRow[i]/2);
        }

        start = 1;
        end = m;

        for (int i = 2; i < end; i++) {
            max = Math.max(max, sum + sumCol[start] - sumCol[i]/2);
            max = Math.max(max, sum + sumCol[end] - sumCol[i]/2);
        }

        bw.write(max+"");
        bw.flush();
    }

    private static long getRowSum(int r) {
        long sum = board[r][1] + board[r][m];

        for (int i = 2; i < m; i++) sum += (board[r][i]*2);

        return sum;
    }

    private static long getColSum(int c) {
        long sum = board[1][c] + board[n][c];

        for (int i = 2; i < n; i++) sum += (board[i][c]*2);

        return sum;
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
