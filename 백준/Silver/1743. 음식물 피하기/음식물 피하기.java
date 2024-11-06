import java.io.*;
import java.util.*;

public class Main {
    private static final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static Queue<int[]> queue;
    private static BitSet visited;
    private static boolean[][] board;
    private static int n, m, k, ans;

    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        k = readInt();

        board = new boolean[n][m];

        for (int i = 0; i < k; i++) board[readInt()-1][readInt()-1] = true;

        visited = new BitSet();
        queue = new ArrayDeque<>();

        ans = 0;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (!board[r][c]) continue;
                if (visited.get(r*m+c)) continue;
                visited.set(r*m+c);

                ans = Math.max(ans, bfs(r, c));
            }
        }

        System.out.print(ans);
    }

    public static int bfs(int r, int c) {
        int cnt = 0;

        queue.offer(new int[]{r, c});

        while (!queue.isEmpty()) {
            int point[] = queue.poll();
            int cr = point[0], cc = point[1];

            cnt++;

            for (int[] dir : DIRS) {
                int nr = cr + dir[0], nc = cc + dir[1];

                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                if (!board[nr][nc]) continue;
                if (visited.get(nr*m+nc)) continue;

                visited.set(nr*m+nc);

                queue.offer(new int[]{nr, nc});
            }
        }

        return cnt;
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
