import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int n, m, k, x, s, d, INF = Integer.MAX_VALUE;
    private static Node[] adj;
    private static Queue<Integer> queue;
    private static BitSet visited;
    private static int[] dist;
    
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
            //adj[s] = new Node(d, adj[s]);
        }
        
        queue = new ArrayDeque<>();
        visited = new BitSet();
        dist = new int[n+1];
        
        bfs(x);
        
        for (int i = 1; i <= n; i++) {
            if (dist[i] == k) sb.append(i+" ");
        }
        
        System.out.print(sb.length() == 0 ? -1 : sb);
    }
    
    private static void bfs(int v) {
        Arrays.fill(dist, INF);
        dist[x] = 0;
        
        queue.offer(x);
        visited.set(x);
        
        while (!queue.isEmpty()) {
            int val = queue.poll();
            
            for (Node cur = adj[val]; cur != null; cur = cur.next) {
                if (dist[cur.v] != INF) continue;                
                if (visited.get(cur.v)) continue;

                dist[cur.v] = dist[val] + 1;
                visited.set(cur.v);
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