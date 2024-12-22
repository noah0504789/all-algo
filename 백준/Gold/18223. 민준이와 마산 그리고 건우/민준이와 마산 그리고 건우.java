import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static final int INF = Integer.MAX_VALUE;
    private static final int start = 1;
    
    private static PriorityQueue<Edge> pq;
    private static List<List<Edge>> edges;
    private static BitSet visited;
    private static int[] minDists;
    private static int v, e, p, end;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        v = end = readInt();
        e = readInt();
        p = readInt();

        edges = new ArrayList<>();
        for (int i = 0; i <= v; i++) edges.add(new ArrayList<>());
        for (int i = 0; i < e; i++) {
            int src = readInt();
            int dest = readInt();
            int dist = readInt();
            edges.get(src).add(new Edge(dest, dist));
            edges.get(dest).add(new Edge(src, dist));
        }

        pq = new PriorityQueue<>();
        visited = new BitSet();
        minDists = new int[v+1];

        if (dijkstra(start, end) == dijkstra(start, p) + dijkstra(p, end)) {
            bw.write("SAVE HIM");
        } else {
            bw.write("GOOD BYE");
        }

        bw.flush();
    }

    private static long dijkstra(int start, int end) {
        pq.clear();
        Arrays.fill(minDists, INF);
        visited.clear();

        pq.offer(new Edge(start, 0));
        minDists[start] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            int stopover = cur.dest, toStopoverD = cur.dist;

            if (stopover == end) break;

            if (toStopoverD > minDists[stopover]) continue;
            if (visited.get(stopover)) continue;
            visited.set(stopover);

            for (Edge neigh : edges.get(stopover)) {
                int dest = neigh.dest, toDestD = neigh.dist;
                int newD = toDestD + toStopoverD;

                if (newD >= minDists[dest]) continue;

                minDists[dest] = newD;
                pq.offer(new Edge(dest, newD));
            }
        }

        return minDists[end];
    }

    static class Edge implements Comparable<Edge> {
        final int dest, dist;

        public Edge(int dest, int dist) {
            this.dest = dest;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.dist, o.dist);
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
