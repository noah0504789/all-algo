import java.io.*;
import java.util.*;

public class Main {
    private static BitSet visited;
    private static Node[] nodes;
    private static int n, ans, p, c, w;

    public static void main(String... args) throws IOException {
        n = readInt();

        nodes = new Node[n+1];
        visited = new BitSet();

        for (int i = 0; i < n-1; i++) {
            p = readInt();
            c = readInt();
            w = readInt();

            nodes[p] = new Node(c, nodes[p], w);
            nodes[c] = new Node(p, nodes[c], w);
        }

        ans = 0;

        for (int i = 1; i < n; i++) {
            visited.clear();
            visited.set(i);
            dfs(i, 0);
        }

        System.out.print(ans);
    }

    public static void dfs(int start, int sum) {
        for (Node neigh = nodes[start]; neigh != null; neigh = neigh.next) {
            int stopover = neigh.num, toStopoverD = neigh.dist;

            if (visited.get(stopover)) continue;
            visited.set(stopover);

            dfs(stopover, sum + toStopoverD);
        }

        ans = Math.max(ans, sum);
    }

    static class Node {
        final int num, dist;
        final Node next;

        public Node(int num, Node next, int dist) {
            this.num = num;
            this.next = next;
            this.dist = dist;
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
