import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static PriorityQueue<Edge> pq;
    private static List<List<Edge>> edges;
    private static Stack<Integer> stack;
    private static BitSet visited;
    private static Edge curE;
    private static int[] minCosts, prev;
    private static int n, m, start, end;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        edges = new ArrayList<>();
        for (int i = 0; i <= n; i++) edges.add(new ArrayList<>());

        m = readInt();
        for (int i = 0; i < m; i++) edges.get(readInt()).add(new Edge(readInt(), readInt()));

        start = readInt();
        end = readInt();

        pq = new PriorityQueue<>();
        visited = new BitSet();
        minCosts = new int[n+1];
        prev = new int[n+1];

        dijkstra(start);

        bw.write(minCosts[end]+"\n");

        stack = new Stack<>();
        stack.push(end);

        while (prev[end] != 0) {
            stack.push(prev[end]);
            end = prev[end];
        }

        bw.write(stack.size()+"\n");

        while (!stack.isEmpty()) bw.write(stack.pop()+" ");

        bw.flush();
    }

    private static void dijkstra(int start) {
        Arrays.fill(minCosts, Integer.MAX_VALUE);
        visited.clear();

        minCosts[start] = 0;
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            curE = pq.poll();
            int stopover = curE.dest, toStopoverC = curE.cost;
            if (stopover == end) break;

            if (toStopoverC > minCosts[stopover]) continue;
            if (visited.get(stopover)) continue;
            visited.set(stopover);

            for (Edge neigh : edges.get(stopover)) {
                int dest = neigh.dest, toDestC = neigh.cost;
                int newD = toDestC + toStopoverC;

                if (newD >= minCosts[dest]) continue;

                prev[dest] = stopover;
                minCosts[dest] = newD;
                pq.offer(new Edge(dest, newD));
            }
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

    static class Edge implements Comparable<Edge> {
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
}
