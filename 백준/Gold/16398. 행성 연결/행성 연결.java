import java.io.*;
import java.util.*;

public class Main {
    private static final int INF = Integer.MAX_VALUE;
    
    private static PriorityQueue<Edge> pq;
    private static BitSet mstV;
    private static int[][] adjMatrix;
    private static int[] minDists;
    private static int n;

    public static void main(String... args) throws IOException {
        n = readInt();
        
        adjMatrix = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) adjMatrix[i][j] = readInt();
        }
        
        minDists = new int[n];
        Arrays.fill(minDists, INF);
        mstV = new BitSet(n); 
        
        pq = new PriorityQueue<>();

        System.out.print(prim(0));
    }
    
    public static long prim(int start) {
        pq.clear();
        mstV.clear();
        
        minDists[start] = 0;
        pq.offer(new Edge(start, 0));
        
        long sum = 0, cnt = 0;
        
        while (!pq.isEmpty()) {
            Edge curE = pq.poll();
            int stopover = curE.dest, toStopoverD = curE.weight;
            
            if (mstV.get(stopover)) continue;
            mstV.set(stopover);
            
            sum += toStopoverD;
            if (++cnt == n) break;
            
            for (int neigh = 0; neigh < n; neigh++) {
                if (adjMatrix[stopover][neigh] == 0) continue;
                if (adjMatrix[stopover][neigh] >= minDists[neigh]) continue;
                
                minDists[neigh] = adjMatrix[stopover][neigh];
                pq.offer(new Edge(neigh, minDists[neigh]));
            }
        }
        
        return sum;        
    }
    
    static class Edge implements Comparable<Edge> {
        final int dest, weight;
        
        Edge(int dest, int weight) {
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
        while (c >= '0' && c <= '9') {
            r *= 10;
            r += c - '0';
            c = System.in.read();
        }

        return r;
    }
}
