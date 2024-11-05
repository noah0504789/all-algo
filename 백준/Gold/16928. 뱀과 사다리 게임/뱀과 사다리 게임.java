import java.io.*;
import java.util.*;

public class Main {
    private static Queue<Turn> queue;
    private static boolean[][] visited;
    private static int[] paddles, snakes;
    private static int n, m, start, prev;

    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();

        prev = 1;
        start = 1;

        paddles = new int[101];
        for (int i = 0; i < n; i++) paddles[readInt()] = readInt();

        snakes = new int[101];
        for (int i = 0; i < m; i++) snakes[readInt()] = readInt();

        queue = new ArrayDeque<>();
        visited = new boolean[101][101];

        System.out.print(bfs());
    }

    private static int bfs() {
        queue.offer(new Turn(start, 0));

        visited[start][prev] = true;

        while (!queue.isEmpty()) {
            Turn cur = queue.poll();
            int cl = cur.loc, cCnt = cur.cnt;

            if (cl == 100) return cCnt;

            for (int i = 6; i >= 1; i--) {
                int nl = cl + i;

                if (nl > 100) continue;

                if (paddles[nl] > 0) nl = paddles[nl];
                else if (snakes[nl] > 0) nl = snakes[nl];

                if (visited[nl][cl]) continue;
                visited[nl][cl] = true;

                queue.offer(new Turn(nl, cCnt+1));
            }
        }

        return -1;
    }

    static class Turn {
        final int loc, cnt;

        Turn(int loc, int cnt) {
            this.loc = loc;
            this.cnt = cnt;
        }
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
