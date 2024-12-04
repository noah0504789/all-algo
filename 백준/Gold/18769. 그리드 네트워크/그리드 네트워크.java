import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static PriorityQueue<Edge> pq;
    private static DisjointSet disjointSet;
    private static Edge curE;
    private static int tc, r, c, cur, next, eCnt, sum, size;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        tc = readInt();

        pq = new PriorityQueue<>();

        while (tc-- > 0) {
            pq.clear();

            r = readInt();
            c = readInt();

            size = r*c;

            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c-1; j++) {
                    cur = i * c + j;
                    next = cur + 1;
                    pq.offer(new Edge(cur, next, readInt()));
                }
            }

            for (int i = 0; i < r-1; i++) {
                for (int j = 0; j < c; j++) {
                    cur = i * c + j;
                    next = cur + c;
                    pq.offer(new Edge(cur, next, readInt()));
                }
            }

            disjointSet = new DisjointSet(size);

            sum = eCnt = 0;
            while (!pq.isEmpty()) {
                curE = pq.poll();

                if (!disjointSet.union(curE.src, curE.dest)) continue;

                sum += curE.dist;

                if (++eCnt == size-1) break;
            }

            bw.write(sum+"\n");
        }

        bw.flush();
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

    static class Edge implements Comparable<Edge> {
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

    private static class DisjointSet {
        int[] root, rank;
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
}
