import java.io.*;
import java.util.*;

public class Main {
    private static final int[][] DIRS = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    private static Queue<int[]> queue;
    private static boolean[][] board;
    private static boolean[][][] visited;
    private static int[] point;
    private static int n, m, cc, cr, broken, cd;

    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        
        if (n == 1 && m == 1) {
            System.out.print(1);
            return;
        }

        visited = new boolean[n][m][2];
        board = new boolean[n][m];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (!readBit()) continue;

                board[r][c] = true;
            }
        }

        queue = new ArrayDeque<>();

        System.out.print(bfs());
    }

    public static int bfs() {
        queue.offer(new int[]{0, 0, 0, 1});
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            point = queue.poll();
            cr = point[0];
            cc = point[1];
            broken = point[2];
            cd = point[3];

            if (cr == n-1 && cc == m-1) return cd;

            for (int[] dir : DIRS) {
                int nr = cr + dir[0], nc = cc + dir[1];
                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;

                if (!board[nr][nc]) {
                    if (visited[nr][nc][broken]) continue;

                    visited[nr][nc][broken] = true;
                    queue.offer(new int[]{nr, nc, broken, cd+1});
                } else if (broken == 0 && !visited[nr][nc][1]) {
                    visited[nr][nc][1] = true;
                    queue.offer(new int[]{nr, nc, 1, cd+1});
                }
            }
        }

        return -1;
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

    public static boolean readBit() throws IOException {
        int c = System.in.read();

        while (c <= ' ') c = System.in.read();

        return c == '1' ? true : false;
    }
}
