import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static PriorityQueue<Edge> pq;
    private static Edge cur;
    private static DisjointSet disjointSet;
    private static int n, m, s, e, ans;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        m = readInt();

        s = readInt();
        e = readInt();

        ans = Integer.MAX_VALUE;

        disjointSet = new DisjointSet(n);
        pq = new PriorityQueue<>();
        
        for (int i = 0; i < m; i++) pq.offer(new Edge(readInt(), readInt(), readInt()));

        while (!pq.isEmpty()) {
            cur = pq.poll();

            if (!disjointSet.union(cur.src, cur.dest)) continue;

            ans = Math.min(ans, cur.dist);

            if (disjointSet.find(s) == disjointSet.find(e)) {
                bw.write(ans+"");
                bw.flush();
                return;
            }
        }

        bw.write("0");
        bw.flush();
    }

    private static class DisjointSet {
        int[] root, rank;

        public DisjointSet(int size) {
            this.root = new int[size+1];
            this.rank = new int[size+1];

            makeSet();
        }

        private void makeSet() {
            for (int i = 1; i < root.length; i++) {
                root[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int x) {
            if (x == root[x]) return x;

            return root[x] = find(root[x]);
        }

        public boolean union(int x, int y) {
            int rootX = find(x), rootY = find(y);

            if (rootX == rootY) return false;

            if (root[rootX] < root[rootY]) root[rootX] = root[rootY];
            else root[rootY] = root[rootX];

            if (rank[rootX] == rank[rootY]) rank[rootY]++;

            return true;
        }
    }

    private static class Edge implements Comparable<Edge> {
        final int src, dest, dist;

        public Edge(int src, int dest, int dist) {
            this.src = src;
            this.dest = dest;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(o.dist, this.dist);
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
