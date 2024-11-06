import java.io.*;
import java.util.*;

public class Main {
    private static final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    private static Queue<int[]> queue;
    private static int[][] board;
    private static int n, m;

    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();

        board = new int[n][m];
        queue = new ArrayDeque<>();

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (readInt() == 0) continue;

                queue.add(new int[]{r, c});
                board[r][c] = 1;
            }
        }

        System.out.print(bfs());
    }

    public static int bfs() {
        int res = 0;

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int cr = point[0], cc = point[1];

            for (int[] dir : DIRS) {
                int nr = cr + dir[0], nc = cc + dir[1];

                if (nr < 0 || nr >= n || nc < 0 || nc >= m || board[nr][nc] > 0) continue;

                board[nr][nc] = board[cr][cc] + 1;
                res = Math.max(res, board[nr][nc]);

                queue.offer(new int[]{nr, nc});
            }
        }

        return res - 1;
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
