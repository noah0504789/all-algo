import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static int[][] board, dp;
    private static int n, m;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        m = readInt();

        board = new int[n+1][m+1];
        dp = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                board[i][j] = readInt();
                dp[i][j] = -1;
            }
        }


        bw.write(dfs(1, 1)+"");
        bw.flush();
    }

    private static int dfs(int r, int c) {
        if (r == n && c == m) return 1;
        if (dp[r][c] > -1) return dp[r][c];

        dp[r][c] = 0;
        for (int[] dir : DIRS) {
            int nr = r + dir[0], nc = c + dir[1];

            if (nr < 1 || nr > n || nc < 1 || nc > m) continue;
            if (board[r][c] > board[nr][nc]) dp[r][c] += dfs(nr, nc);
        }

        return dp[r][c];
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
