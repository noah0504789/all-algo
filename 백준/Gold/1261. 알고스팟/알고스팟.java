import java.io.*;
import java.util.*;

public class Main {        
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static PriorityQueue<Point> pq;
    private static BitSet visited;
    private static Point cur;
    private static String input;
    private static int[][] board;
    private static int m, n, nr, nc;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];

        for (int r = 1; r <= n; r++) {
            input = br.readLine();
            for (int c = 1; c <= m; c++) board[r][c] = Integer.parseInt(String.valueOf(input.charAt(c-1)));
        }

        pq = new PriorityQueue<>();
        visited = new BitSet();

        bw.write(bfs(1, 1)+"");
        bw.flush();
    }

    private static int bfs(int r, int c) {
        pq.offer(new Point(r, c, 0));
        visited.set(r*m+c);

        while (!pq.isEmpty()) {
            cur = pq.poll();

            if (cur.isArrived()) return cur.cnt;

            for (int[] dir : DIRS) {
                nr = cur.r + dir[0];
                nc = cur.c + dir[1];

                if (nr < 1 || nr > n || nc < 1 || nc > m) continue;
                if (visited.get(nr * m + nc)) continue;
                visited.set(nr * m + nc);

                pq.offer(new Point(nr, nc, cur.cnt + board[nr][nc]));
            }
        }

        return -1;
    }

    static class Point implements Comparable<Point> {
        final int r, c, cnt;

        public Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }

        public boolean isArrived() {
            return r == n && c == m;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.cnt, o.cnt);
        }
    }
}