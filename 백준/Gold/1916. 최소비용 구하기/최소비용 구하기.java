import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static PriorityQueue<Edge> pq;
    private static List<List<Edge>> edges;
    private static BitSet visited;
    private static Edge curE;
    private static int[] minCosts;
    private static int n, m, start, end;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        m = readInt();

        edges = new ArrayList<>();
        for (int i = 0; i <= n; i++) edges.add(new ArrayList<>());
        for (int i = 0; i < m; i++) edges.get(readInt()).add(new Edge(readInt(), readInt()));

        start = readInt();
        end = readInt();

        pq = new PriorityQueue<>();
        visited = new BitSet();
        minCosts = new int[n+1];

        bw.write(dijkstra(start)+"");
        bw.flush();
    }

    private static int dijkstra(int start) {
        Arrays.fill(minCosts, Integer.MAX_VALUE);
        visited.clear();

        minCosts[start] = 0;
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            curE = pq.poll();
            int stopover = curE.dest, toStopOverC = curE.cost;
            if (stopover == end) break;

            int minStopOverC = minCosts[stopover];

            if (toStopOverC > minStopOverC) continue;
            if (visited.get(curE.dest)) continue;
            visited.set(curE.dest);

            for (Edge neigh : edges.get(curE.dest)) {
                int dest = neigh.dest, toDestC = neigh.cost;
                int newC = toDestC + toStopOverC;

                if (newC >= minCosts[dest]) continue;

                minCosts[dest] = newC;
                pq.offer(new Edge(dest, newC));
            }
        }

        return minCosts[end];
    }

    private static class Edge implements Comparable<Edge> {
        final int dest, cost;

        public Edge(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
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
