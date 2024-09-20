import java.io.*;
import java.util.*;

public class Main {
    private static final int INF = Integer.MAX_VALUE;
    private static List<List<Edge>> edges = new ArrayList<>();
    private static PriorityQueue<Edge> pq = new PriorityQueue<>();
    private static BitSet visited = new BitSet();
    private static int[] minDists;
    private static int n, e, s, d, dist, v1, v2;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        e = readInt();
        
        minDists = new int[n+1];
        
        for (int i = 0; i <= n; i++) edges.add(new ArrayList<>());
        
        for (int i = 0; i < e; i++) {
            s = readInt();
            d = readInt();
            dist = readInt();
            
            edges.get(s).add(new Edge(d, dist));
            edges.get(d).add(new Edge(s, dist));
        }
        
        v1 = readInt();
        v2 = readInt();
        
        long res1 = 0;
        res1 += dijkstra(1, v1);
        res1 += dijkstra(v1, v2);
        res1 += dijkstra(v2, n);
        
        long res2 = 0;
        res2 += dijkstra(1, v2);
        res2 += dijkstra(v2, v1);
        res2 += dijkstra(v1, n);        
                   
        System.out.print((res1 >= INF && res2 >= INF) ? -1 : Math.min(res1, res2));
    }
    
    private static int dijkstra(int start, int end) {
        Arrays.fill(minDists, INF);
        visited.clear();
        
        pq.offer(new Edge(start, 0));
        minDists[start] = 0;
        
        while (!pq.isEmpty()) {
            Edge curE = pq.poll();
            int stopover = curE.dest, toStopoverD = curE.dist;
            
            if (toStopoverD > minDists[stopover]) continue;
            if (visited.get(stopover)) continue;                
            visited.set(stopover);
            
            for (Edge neigh : edges.get(stopover)) {
                int dest = neigh.dest, toDestD = neigh.dist;
                int newD = toStopoverD + toDestD;

                if (newD >= minDists[dest]) continue;

                minDists[dest] = newD;
                pq.offer(new Edge(dest, minDists[dest]));
            }            
        }
        
        return minDists[end];
    }
    
    static class Edge implements Comparable<Edge> {
        final int dest, dist;
        
        Edge(int dest, int dist) {
            this.dest = dest;
            this.dist = dist;
        }
        
        public int compareTo(Edge o) {
            return Integer.compare(this.dist, o.dist);
        }
    }

    private static int readInt() throws IOException {
        int result = 0, c = System.in.read();

        while (c <= ' ') c = System.in.read();

        while (c >= '0' && c <= '9') {
            result *= 10;
            result += c - '0';
            c = System.in.read();
        }

        return result;
    }
}
