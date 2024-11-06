import java.io.*;
import java.util.*;

public class Main {        
    private static BufferedReader br;
    private static StringTokenizer st;

    private static final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static Queue<int[]> queue;
    private static BitSet visited;
    private static char[][] board;
    private static int n, m, my, oppo;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[m][n];

        for (int i = 0; i < m; i++) board[i] = br.readLine().toCharArray();

        visited = new BitSet();
        queue = new ArrayDeque<>();

        my = oppo = 0;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (visited.get(r*n+c)) continue;
                visited.set(r*n+c);

                char cur = board[r][c];
                int power = bfs(r, c, cur);

                if (cur == 'W') my += power;
                else oppo += power;
            }
        }

        System.out.print(my + " " + oppo);
    }

    public static int bfs(int r, int c, char uniform) {
        queue.offer(new int[]{r, c});
        int cnt = 0;

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int cr = point[0], cc = point[1];

            cnt++;

            for (int[] dir : DIRS) {
                int nr = cr + dir[0], nc = cc + dir[1];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                if (board[nr][nc] != uniform) continue;
                if (visited.get(nr*n+nc)) continue;

                visited.set(nr*n+nc);

                queue.offer(new int[]{nr, nc});
            }
        }

        return cnt * cnt;
    }
}