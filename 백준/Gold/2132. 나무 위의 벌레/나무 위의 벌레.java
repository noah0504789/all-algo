import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static List<Edge>[] tree;
    private static int[] dists;
    private static int n, src, dest, ans, ansNum, start;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();

        dists = new int[n+1];
        tree = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            dists[i] = readInt();
            tree[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < n-1; i++) {
            src = readInt();
            dest = readInt();

            tree[src].add(new Edge(dest, dists[dest]));
            tree[dest].add(new Edge(src, dists[src]));
        }

        ans = 0;        
        ansNum = n+1;
        start = 1;
        dfs(start, -1, dists[start]);
        
        ans = 0;
        start = ansNum;
        //ansNum = n+1;
        dfs(start, -1, dists[start]);

        bw.write(ans + " " + Math.min(start, ansNum));

        bw.flush();
    }

    public static void dfs(int cur, int prev, int sum) {
        if (ans < sum) {
            ans = sum;
            ansNum = cur;
        } else if (ans == sum) {
            ansNum = Math.min(ansNum, cur);
        }
        
        for (Edge edge : tree[cur]) {
            if (edge.dest == prev) continue;

            dfs(edge.dest, cur, sum + edge.dist);
        }
    }

    static class Edge {
        final int dest, dist;

        public Edge(int dest, int dist) {
            this.dest = dest;
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
