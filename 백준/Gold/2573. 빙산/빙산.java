import java.io.*;
import java.util.*;

public class Main {
    private static final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private static Queue<int[]> queue;
//    private static Set<Integer> visited;
    private static List<int[]> list;
    private static BitSet visited;
    private static int[][] board;
    private static int n, m, sr, sc, cr, cc, cnt, ans;

    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();

        board = new int[n][m];

        cnt = 0;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                board[r][c] = readInt();
                if (board[r][c] == 0) continue;

                cnt++;
                sr = r;
                sc = c;
            }
        }

//        visited = new HashSet<>();
        visited = new BitSet();
        queue = new ArrayDeque<>();
        list = new ArrayList<>();

        ans = 0;

        // 섬 갯수 1개인지 체크
        // - 토탈 갯수 == bfs 갯수
        while (cnt == bfs()) {
            melt();
            ans++;

            if (cnt == 0) {
                System.out.print(0);
                return;
            }
        }

        System.out.print(ans);
    }

    public static void melt() {
        list.clear();

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (board[r][c] == 0) continue;

                list.add(new int[]{r, c, nearCnt(r, c)});
            }
        }

        for (int[] arr : list) {
            int r = arr[0], c = arr[1], minusCnt = arr[2];

            board[r][c] -= minusCnt;

            if (board[r][c] > 0) {
                sr = r;
                sc = c;
            } else {
                board[r][c] = 0;
                cnt--;
            }
        }
    }

    public static int nearCnt(int r, int c) {
        int cnt = 0;

        for (int[] dir : DIRS) {
            int nr = r + dir[0], nc = c + dir[1];
            if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
            if (board[nr][nc] > 0) continue;

            cnt++;
        }

        return cnt;
    }

    public static int bfs() {
//        visited.clear();
//        visited.add(sr * m + sc);
        queue.offer(new int[]{sr, sc});
        visited.clear();
        visited.set(sr * m + sc);

        int cnt = 0;

        while (!queue.isEmpty()) {
            int[] point = queue.poll();

            cr = point[0];
            cc = point[1];

            cnt++;

            for (int[] dir : DIRS) {
                int nr = cr + dir[0], nc = cc + dir[1];

                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                if (board[nr][nc] == 0) continue;
                if (visited.get(nr * m + nc)) continue;
                visited.set(nr * m + nc);

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
