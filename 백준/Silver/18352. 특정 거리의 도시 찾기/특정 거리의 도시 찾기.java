import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int n, m, k, x, s, d, INF = Integer.MAX_VALUE;
    private static int[] dist;
    private static Node[] adj;
    private static Queue<Integer> queue;
    private static BitSet visited;    
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        n = readInt();
        m = readInt();
        k = readInt();
        x = readInt();
        
        adj = new Node[n+1];
        for (int i = 0; i < m; i++) {
            s = readInt();
            d = readInt();
            
            adj[s] = new Node(d, adj[s]);
        }
        
        queue = new LinkedList<>();
        visited = new BitSet();
        dist = new int[n+1];
        
        bfs(x);
        
        for (int i = 1; i <= n; i++) {            
            if (dist[i] == k) sb.append(i+" ");
        }

        System.out.print(sb.length() == 0 ? -1 : sb);
    }
    
    private static void bfs(int x) {
        Arrays.fill(dist, INF);
        dist[x] = 0;
        queue.offer(x);
        visited.set(x);
        
        while (!queue.isEmpty()) {
            int v = queue.poll();
            
            for (Node cur = adj[v]; cur != null; cur = cur.next) {
                if (visited.get(cur.v) || dist[cur.v] != INF) continue;
                
                dist[cur.v] = dist[v] + 1;
                visited.get(cur.v);
                queue.offer(cur.v);
            }                
        }
    }
    
    static class Node {
        int v;
        Node next;
        Node(int v, Node next) {
            this.v=v;
            this.next=next;
        }
    }

    public static int readInt() throws IOException {
        int r = 0, c = System.in.read();
        boolean negative = false;       

        while (c <= ' ') c = System.in.read();
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