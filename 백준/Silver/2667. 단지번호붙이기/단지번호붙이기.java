import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br;
    private static StringBuilder sb;

    private static final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private static Queue<Integer> queue;
    private static List<Integer> list;

    private static BitSet[] adjMatrix, visited;
    private static String input;
    private static int n;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());

        queue = new ArrayDeque<>();
        list = new ArrayList<>();

        adjMatrix = new BitSet[n];
        visited = new BitSet[n];

        for (int i = 0; i < n; i++) {
            adjMatrix[i] = new BitSet();
            visited[i] = new BitSet();
            input = br.readLine();

            for (int j = 0; j < n; j++) {
                if (input.charAt(j) - '0' == 0) continue;

                adjMatrix[i].set(j);
            }
        }

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (!adjMatrix[r].get(c)) continue;
                if (visited[r].get(c)) continue;

                list.add(bfs(r, c));
            }
        }

        sb.append(list.size()).append("\n");

        Collections.sort(list);

        for (int cnt : list) sb.append(cnt).append("\n");

        System.out.print(sb.toString().trim());
    }

    private static int bfs(int r, int c) {
        int cnt = 1;
        queue.offer(r * n + c);
        visited[r].set(c);

        while (!queue.isEmpty()) {
            int point = queue.poll();
            int cr = point / n, cc = point % n;

            for (int[] dir : DIRS) {
                int nr = cr + dir[0], nc = cc + dir[1];

                if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                if (!adjMatrix[nr].get(nc)) continue;
                if (visited[nr].get(nc)) continue;

                cnt++;
                visited[nr].set(nc);
                queue.offer(nr * n + nc);
            }
        }

        return cnt;
    }
}

