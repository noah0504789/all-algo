import java.io.*;
import java.util.*;

public class Main {
    private static UnionFind unionFind;
    private static Edge[] edges;
    private static int v, e, a, b, c, sum, cnt;

    public static void main(String... args) throws IOException {
        v = readInt();
        e = readInt();
        
        unionFind = new UnionFind(v);
        edges = new Edge[e];
        
        for (int i = 0; i < e; i++) {
            a = readInt();
            b = readInt();
            c = readInt();
            
            edges[i] = new Edge(a, b, c);
        }
        
        Arrays.sort(edges);
        
        sum = 0;
        cnt = 0;
        
        for (Edge edge : edges) {
            if (!unionFind.union(edge.src, edge.dest)) continue;
            
            sum += edge.weight;
            
            if (++cnt == v - 1) break;
        }
        
        System.out.print(sum);
    }
    
    static class UnionFind {
        final int size;
        int[] root, rank;
        
        UnionFind(int size) {
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
        
        public int find(int x) {
            if (root[x] == x) return x;
            
            return root[x] = find(root[x]);
        }
        
        public boolean union(int x, int y) {
            int rootX = find(root[x]), rootY = find(root[y]);
            
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

    public static int readInt() throws IOException {
        int r = 0, c = System.in.read();

        while (c <= ' ') c = System.in.read();
        
        boolean negative = false;
        if (c == '-') {
            negative = true;
            c = System.in.read();
        }
        
        while (c >= '0' && c <= '9') {
            r *= 10;
            r += c - '0';
            c = System.in.read();
        }
        
        return negative ? -r : r;
    }
}
