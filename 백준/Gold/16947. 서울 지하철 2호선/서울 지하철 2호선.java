import java.io.*;
import java.util.*;

public class Main {
    private static StringBuilder sb;

    private static Queue<int[]> queue;
    private static Node[] nodes;
    private static BitSet visited, cycled;
    private static int n, src, dest;

    public static void main(String... args) throws IOException {
        sb = new StringBuilder();

        n = readInt();

        nodes = new Node[n+1];
        visited = new BitSet();
        cycled = new BitSet();

        for (int i = 0; i < n; i++) {
            src = readInt();
            dest = readInt();

            nodes[src] = new Node(dest, nodes[src]);
            nodes[dest] = new Node(src, nodes[dest]);
        }

        for (int i = 1; i <= n; i++) {
            if (dfs(-1, i, i)) break;

            visited.clear();
        }

        queue = new ArrayDeque<>();

        for (int i = 1; i <= n; i++) sb.append(cycled.get(i) ? 0 : bfs(i)).append(" ");

        System.out.print(sb);
    }

    private static boolean dfs(int prev, int cur, int start) {
        visited.set(cur);

        for (Node neighNode = nodes[cur]; neighNode != null; neighNode = neighNode.next) {
            int neigh = neighNode.loc;

            if (!visited.get(neigh)) {
                if (!dfs(cur, neigh, start)) continue;

                cycled.set(neigh);

                return true;
            }

            if (neigh != prev && neigh == start) {
                cycled.set(neigh);

                return true;
            }
        }

        return false;
    }

    private static int bfs(int start) {
        visited.clear();
        visited.set(start);

        queue.clear();
        queue.offer(new int[]{start, 0});

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int loc = point[0], dist = point[1];

            if (cycled.get(loc)) return dist;

            for (Node neigh = nodes[loc]; neigh != null; neigh = neigh.next) {
                int neighLoc = neigh.loc;

                if (visited.get(neighLoc)) continue;
                visited.set(neighLoc);

                queue.offer(new int[]{neighLoc, dist+1});
            }
        }

        return -1;
    }

    static class Node {
        final int loc;
        final Node next;

        Node(int loc, Node next) {
            this.loc = loc;
            this.next = next;
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
