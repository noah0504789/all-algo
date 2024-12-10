import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static BitSet visited;
    private static Queue<int[]> queue;
    private static int[][] board, dists, islands;
    private static int n, label;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();

        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) board[i][j] = readInt();
        }

        queue = new ArrayDeque<>();
        visited = new BitSet();
        islands = new int[n][n];
        label = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) continue;
                if (visited.get(i*n+j)) continue;

                bfs(i, j);

                label++;
            }
        }

        bw.write(multisourceBFS()+"");
        bw.flush();
    }

    private static void bfs(int r, int c) {
        queue.offer(new int[]{r, c});
        visited.set(r*n+c);
        islands[r][c] = label;

        while (!queue.isEmpty()) {
            int[] point = queue.poll();

            for (int[] dir : DIRS) {
                int nr = point[0] + dir[0], nc = point[1] + dir[1];

                if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                if (board[nr][nc] == 0) continue;
                if (visited.get(nr*n+nc)) continue;

                visited.set(nr*n+nc);
                islands[nr][nc] = label;
                queue.offer(new int[]{nr, nc});
            }
        }
    }

    private static int multisourceBFS() {
        queue.clear();
        dists = new int[n][n];

        for (int i = 0; i < n; i++) Arrays.fill(dists[i], -1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) continue;

                for (int[] dir : DIRS) {
                    int nr = i + dir[0], nc = j + dir[1];
                    if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                    if (board[nr][nc] == 1) continue;

                    queue.offer(new int[]{i, j});
                    dists[i][j] = 0;
                    break;
                }
            }
        }

        int minDist = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int r = point[0], c = point[1];

            for (int[] dir : DIRS) {
                int nr = r + dir[0], nc = c + dir[1];
                if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;

                if (dists[nr][nc] == -1) {
                    queue.offer(new int[]{nr, nc});
                    dists[nr][nc] = dists[r][c] + 1;
                    islands[nr][nc] = islands[r][c];
                } else if (islands[nr][nc] != islands[r][c]) {
                    minDist = Math.min(minDist, dists[nr][nc] + dists[r][c]);
                }
            }
        }

        return minDist == Integer.MAX_VALUE ? 0 : minDist;
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
