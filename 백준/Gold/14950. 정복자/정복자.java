import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static DisjointSet disjointSet;
    private static PriorityQueue<Edge> pq;
    private static Edge curE;
    private static int n, m, t, sum, eCnt, ans;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        m = readInt();
        t = readInt();

        pq = new PriorityQueue<>();
        for (int i = 0; i < m; i++) pq.offer(new Edge(readInt(), readInt(), readInt()));

        disjointSet = new DisjointSet(n);

        sum = eCnt = 0;
        while (!pq.isEmpty()) {
            curE = pq.poll();

            if (!disjointSet.union(curE.src, curE.dest)) continue;

            sum += curE.weight;

            if (++eCnt == n-1) break;
        }

        ans = sum + (n-1)*(n-2)/2*t;

        bw.write(ans+"");
        bw.flush();
    }

    private static class Edge implements Comparable<Edge> {
        final int src, dest, weight;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    private static class DisjointSet {
        private int[] root, rank;

        public DisjointSet(int size) {
            root = new int[size+1];
            rank = new int[size+1];

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
