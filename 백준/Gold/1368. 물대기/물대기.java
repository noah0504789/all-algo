import java.io.*;
import java.util.*;

public class Main {
    private static final int INF = 100_001;
    private static PriorityQueue<Edge> pq;
    private static BitSet visited;
    private static int[][] adjMatrix;
    private static int[] minDists;
    private static int n;

    public static void main(String... args) throws IOException {
        n = readInt();

        adjMatrix = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) adjMatrix[0][i] = adjMatrix[i][0] = readInt();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) adjMatrix[i][j] = readInt();
        }

        pq = new PriorityQueue<>();
        visited = new BitSet();
        minDists = new int[n+1];
        Arrays.fill(minDists, INF);

        System.out.print(prim(0));
    }

    public static long prim(int start) {
        minDists[start] = 0;
        pq.offer(new Edge(start, 0));

        long sum = 0, cnt = 0;

        while (!pq.isEmpty()) {
            Edge curE = pq.poll();
            int stopover = curE.dest, toStopoverD = curE.weight;

            if (visited.get(stopover)) continue;
            visited.set(stopover);

            sum += toStopoverD;
            if (++cnt == n+1) break;

            for (int neigh = 0; neigh <= n; neigh++) {
                if (adjMatrix[stopover][neigh] == 0) continue;
                if (adjMatrix[stopover][neigh] >= minDists[neigh]) continue;

                pq.offer(new Edge(neigh, minDists[neigh] = adjMatrix[stopover][neigh]));
            }
        }

        return sum;
    }

    static class Edge implements Comparable<Edge> {
        final int dest, weight;

        public Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }

        @Override
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
