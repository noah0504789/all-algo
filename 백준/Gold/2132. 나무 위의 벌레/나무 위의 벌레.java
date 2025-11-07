import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int n, s, e;
    private static int[] fruit;
    private static long[] dist;
    private static List<Integer>[] adj;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();

        n = readInt();
        
        fruit = new int[n];
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            fruit[i] = readInt();
            adj[i] = new ArrayList<>();
        }        
        
        for (int i = 0; i < n-1; i++) {
            s = readInt()-1;
            e = readInt()-1;
            
            adj[s].add(e);
            adj[e].add(s);
        }
        
        dist = new long[n];
        
        s = dfs(0);
        e = dfs(s);
        
        System.out.print(dist[e] + " " + (Math.min(s, e)+1));
    }
    
    private static int dfs(int idx) {
        Arrays.fill(dist, 0);
        dist[idx] = fruit[idx];
        dfs(idx, -1);
        
        int res = 0;
        long mx = dist[0];
        
        for (int i = 1; i < n; i++) {
            if (dist[i] > mx || (dist[i] == mx && i < res)) {
                mx = dist[i];
                res = i;
            }
        }
        
        return res;
    }
    
    private static void dfs(int cur, int prev) {        
        for (int nxt : adj[cur]) {
            if (nxt == prev) continue;
            dist[nxt] = dist[cur] + fruit[nxt];
            dfs(nxt, cur);
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