import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static final int[][] DIRS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static int[][] board, temp;
    private static int n, m, t, up, down, ans, amt, cnt, share, nr, nc;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        m = readInt();
        t = readInt();

        up = down = -1;

        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = readInt();

                if (board[i][j] == -1) {
                    if (up == -1) up = i;
                    else down = i;
                }
            }
        }

        temp = new int[n][m];

        while (t-- > 0) {
            spread();

            cleanUp();
            cleanDown();
        }

        ans = 2;
        for (int i = 0; i < n; i++) ans += Arrays.stream(board[i]).sum();

        bw.write(ans+"");
        bw.flush();
    }

    private static void spread() {
//        temp = new int[n][m];
//        temp[up][0] = temp[down][0] = -1;

        for (int i = 0; i < n; i++) System.arraycopy(board[i], 0, temp[i], 0, m);

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (temp[r][c] <= 0) continue;

                amt = temp[r][c];
                share = amt / 5;

                for (int[] dir : DIRS) {
                    nr = r + dir[0];
                    nc = c + dir[1];

                    if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                    if (board[nr][nc] == -1) continue;

                    board[r][c] -= share;
                    board[nr][nc] += share;
                }
            }
        }
    }

    private static void cleanUp() {
        for (int i = up-1; i > 0; i--) board[i][0] = board[i-1][0];

        System.arraycopy(board[0], 1, board[0], 0, m-1);

        for (int i = 0; i < up; i++) board[i][m-1] = board[i+1][m-1];

        System.arraycopy(board[up], 0, board[up], 1, m-1);

        board[up][1] = 0;
        board[up][0] = -1;
    }

    private static void cleanDown() {
        for (int i = down; i < n-1; i++) board[i][0] = board[i+1][0];

        System.arraycopy(board[n-1], 1, board[n-1], 0, m-1);

        for (int i = n-1; i > down; i--) board[i][m-1] = board[i-1][m-1];

        System.arraycopy(board[down], 0, board[down], 1, m-1);

        board[down][1] = 0;
        board[down][0] = -1;
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
