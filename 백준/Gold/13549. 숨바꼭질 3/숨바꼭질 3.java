import java.io.*;
import java.util.*;

public class Main {
    private static Queue<Turn> queue;
    private static Turn cur;
    private static boolean[] visited;
    private static int n, k, curL, curT, ans;

    public static void main(String... args) throws IOException {
        n = readInt();
        k = readInt();

        queue = new ArrayDeque<>();

        visited = new boolean[100_001];

        ans = 100_000;

        bfs();

        System.out.print(ans);
    }

    public static void bfs() {
        queue.offer(new Turn(n, 0));

        while (!queue.isEmpty()) {
            cur = queue.poll();

            curL = cur.loc;
            curT = cur.turn;

            if (curL == k) ans = Math.min(ans, curT);
            if (curL > 100_000 || curL < 0) continue;

            if (visited[curL]) continue;
            visited[curL] = true;

            queue.offer(new Turn(curL*2, curT));
            queue.offer(new Turn(curL-1, curT+1));
            queue.offer(new Turn(curL+1, curT+1));
        }
    }

    static class Turn {
        int loc, turn;

        Turn(int loc, int turn) {
            this.loc = loc;
            this.turn = turn;
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
