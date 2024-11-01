import java.io.*;
import java.util.*;

public class Main {
    private static DisjointSet disjointSet;
    private static List<Edge> edges;
    private static Point[] points;
    private static double sum;
    private static int n, m, cnt;

    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();

        points = new Point[n+1];
        for (int i = 1; i <= n; i++) points[i] = new Point(i, readInt(), readInt());

        edges = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            for (int j = i+1; j <= n; j++) edges.add(new Edge(points[i].idx, points[j].idx, dist(points[i], points[j])));
        }

        Collections.sort(edges);

        cnt = 0;
        disjointSet = new DisjointSet(n);
        for (int i = 0; i < m; i++) {
            int src = readInt(), dest = readInt();
            if (!disjointSet.union(src, dest)) continue;
            
            cnt++;
        }

        sum = 0;        
        for (Edge edge : edges) {
            if (!disjointSet.union(edge.src, edge.dest)) continue;

            sum += edge.weight;
            if (++cnt == n-1) break;
        }

        System.out.printf("%.2f", sum);
    }

    public static double dist(Point src, Point dest) {
        return Math.sqrt(Math.pow(dest.x - src.x, 2) + Math.pow(dest.y - src.y, 2));
    }

    static class Point {
        final int idx, x, y;

        Point(int idx, int x, int y) {
            this.idx = idx;
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge> {
        final int src, dest;
        final double weight;

        Edge(int src, int dest, double weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        public int compareTo(Edge o) {
            return Double.compare(this.weight, o.weight);
        }
    }

    static class DisjointSet {
        int size;
        int[] root, rank;

        DisjointSet(int size) {
            this.size = size;
            this.root = new int[size+1];
            this.rank = new int[size+1];

            makeSet();
        }

        private void makeSet() {
            for (int i = 1; i <= size; i++) {
                root[i] = i;
                rank[i] = 1;
            }
        }

        private int find(int x) {
            if (root[x] == x) return x;

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
