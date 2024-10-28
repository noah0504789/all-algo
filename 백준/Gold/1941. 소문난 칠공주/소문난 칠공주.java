import java.io.*;
import java.util.*;

public class Main {        
    private static BufferedReader br;

    private static final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static Set<BitSet> set;
    private static BitSet visited, connected;
    private static Queue<Integer> queue;
    private static char[][] board;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        board = new char[5][5];
        for (int i = 0; i < 5; i++) board[i] = br.readLine().toCharArray();

        set = new HashSet<>();
        visited = new BitSet();
        connected = new BitSet();
        queue = new LinkedList<>();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                visited.set(i*5+j);
                dfs(i, j, 1, board[i][j] == 'S' ? 1 : 0);
                visited.clear(i*5+j);
            }
        }

        System.out.print(set.size());
    }

    private static void dfs(int r, int c, int depth, int dCnt) {
        if (depth == 7) {
            if (dCnt < 4) return;
            if (set.contains(visited)) return;
            if (!isConnected()) return;

            set.add((BitSet) visited.clone());

            return;
        }

        for (int i = r*5+c+1; i < 25; i++) {
            if (visited.get(i)) continue;

            visited.set(i);

            int nr = i / 5, nc = i % 5;
            dfs(nr, nc, depth+1, dCnt + (board[nr][nc] == 'S' ? 1 : 0));

            visited.clear(i);
        }
    }

    private static boolean isConnected() {
        queue.clear();
        connected.clear();

        int start = visited.nextSetBit(0);
        queue.offer(start);
        connected.set(start);

        while (!queue.isEmpty()) {
            start = queue.poll();
            int r = start / 5, c = start % 5;

            for (int[] dir : DIRS) {
                int nr = r + dir[0], nc = c + dir[1];
                if (nr < 0 || nr >= 5 || nc < 0 || nc >= 5) continue;

                int nextPos = nr * 5 + nc;
                if (!visited.get(nextPos)) continue;
                if (connected.get(nextPos)) continue;

                queue.offer(nextPos);
                connected.set(nextPos);
            }
        }

        return connected.cardinality() == 7;
    }
}