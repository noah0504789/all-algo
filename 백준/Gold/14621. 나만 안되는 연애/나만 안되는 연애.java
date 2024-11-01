import java.io.*;
import java.util.*;

public class Main {        
    private static BufferedReader br;
    private static StringTokenizer st;

    private static DisjointSet disjointSet;
    private static Edge[] edges;
    private static char[] university;
    private static long sum;
    private static int n, m, idx, u, v, d, cnt;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        university = new char[n+1];

        st = new StringTokenizer(br.readLine());

        idx = 1;
        while (st.hasMoreTokens()) university[idx++] = st.nextToken().charAt(0);

        edges = new Edge[m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(u, v, d);
        }

        Arrays.sort(edges);

        disjointSet = new DisjointSet(n);
        cnt = 0;
        sum = 0;

        for (Edge edge : edges) {
            if (university[edge.src] == university[edge.dest]) continue;
            if (!disjointSet.union(edge.src, edge.dest)) continue;

            sum += edge.weight;
            if (++cnt == n-1) break;
        }

        System.out.print(cnt == n-1 ? sum : -1);
    }

    static class DisjointSet {
        final int size;
        final int[] root, rank;

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
            return root[x] == x ? x : (root[x] = find(root[x]));
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

    static class Edge implements Comparable<Edge> {
        final int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}