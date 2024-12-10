import java.io.*;
import java.util.*;

public class Main {        
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static Queue<Memo> queue;
    private static int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static char[][] board;
    private static boolean[][][] visited;
    private static String input;
    private static int n, m, sr, sc, dr, dc;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visited = new boolean[n][m][64];

        board = new char[n][m];
        for (int r = 0; r < n; r++) {
            input = br.readLine();
            for (int c = 0; c < m; c++) {
                board[r][c] = input.charAt(c);

                if (board[r][c] == '0') {
                    sr = r;
                    sc = c;
                } else if (board[r][c] == '1') {
                    dr = r;
                    dc = c;
                }
            }
        }

        queue = new ArrayDeque<>();

        bw.write(bfs(sr, sc)+"");
        bw.flush();
    }

    private static int bfs(int r, int c) {
        queue.offer(new Memo(r, c, 0, 0));
        visited[r][c][0] = true;
        board[r][c] = '.';

        while (!queue.isEmpty()) {
            Memo cur = queue.poll();

            for (int[] dir : DIRS) {
                int nr = cur.r + dir[0], nc = cur.c + dir[1];
                int keys = cur.keys;

                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                if (board[nr][nc] == '#') continue;
                if (visited[nr][nc][keys]) continue;

                if (board[nr][nc] == '1') return cur.turn+1;

                if ((board[nr][nc] >= 'A' && board[nr][nc] <= 'F' && (keys & (1<<(board[nr][nc] - 'A'))) != 0) || board[nr][nc] == '.') {
                    visited[nr][nc][keys] = true;
                    queue.offer(new Memo(nr, nc, keys, cur.turn+1));
                } else if (board[nr][nc] >= 'a' && board[nr][nc] <= 'f') {
                    keys |= 1<<(board[nr][nc]-'a');

                    visited[nr][nc][keys] = true;
                    queue.offer(new Memo(nr, nc, keys, cur.turn+1));
                }
            }
        }

        return -1;
    }

    static class Memo {
        final int r, c, keys, turn;

        public Memo(int r, int c, int keys, int turn) {
            this.r = r;
            this.c = c;
            this.keys = keys;
            this.turn = turn;
        }
    }
}