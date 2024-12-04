import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static PriorityQueue<Edge> pq;
    private static DisjointSet disjointSet;
    private static Edge cur;
    private static int m, n, src, dest, dist, total, sum, eCnt;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            m = readInt();
            n = readInt();

            if (m == 0 && n == 0) break;

            total = 0;

            pq = new PriorityQueue<>();
            disjointSet = new DisjointSet(m);

            for (int i = 0; i < n; i++) {
                src = readInt()+1;
                dest = readInt()+1;
                dist = readInt();

                total += dist;

                pq.offer(new Edge(src, dest, dist));
            }

            sum = eCnt = 0;

            while (true) {
                cur = pq.poll();

                if (!disjointSet.union(cur.src, cur.dest)) continue;

                sum += cur.dist;
                if (++eCnt == m-1) break;
            }

            bw.write((total-sum)+"\n");
        }

        bw.flush();
    }

    static class DisjointSet {
        private int[] root, rank;

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

            if (rank[rootX] > rank[rootY]) root[rootY] = root[rootX];
            else root[rootX] = root[rootY];

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
            return Integer.compare(this.dist, o.dist);
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