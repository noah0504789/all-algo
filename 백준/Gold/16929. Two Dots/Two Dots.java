import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static final int[][] DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static BitSet visited;
    private static char[][] board;
    private static boolean isExist;
    private static int n, m, dr, dc;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n][m];

        for (int i = 0; i < n; i++) board[i] = br.readLine().toCharArray();

        visited = new BitSet();

        isExist = false;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                dr = r;
                dc = c;

                visited.clear();

                if (!dfs(r, c, 1)) continue;

                isExist = true;
                break;
            }

            if (isExist) break;
        }

        bw.write(isExist ? "Yes" : "No");
        bw.flush();
    }

    private static boolean dfs(int r, int c, int k) {
        visited.set(r*m+c);

        for (int[] dir : DIRS) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
            if (board[r][c] != board[nr][nc]) continue;

            if (!visited.get(nr*m+nc)) {
                if (dfs(nr, nc, k+1)) return true;
            } else if (nr == dr && nc == dc && k >= 4) {
                return true;
            }
        }

        return false;
    }
}
