import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static PriorityQueue<Request> pq;
    private static Request cur;
    private static BitSet visited;
    private static int tc, n, m, a, b, ans;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        tc = readInt();
        visited = new BitSet();
        pq = new PriorityQueue<>();
        while (tc-- > 0) {
            ans = 0;
            visited.clear();
            pq.clear();

            n = readInt();
            m = readInt();

            for (int i = 0; i < m; i++) {
                a = readInt();
                b = readInt();

                pq.offer(new Request(a, b));
            }

            while (!pq.isEmpty()) {
                cur = pq.poll();

                for (int i = cur.start; i <= cur.end; i++) {
                    if (visited.get(i)) continue;

                    visited.set(i);
                    ans++;
                    break;
                }
            }

            bw.write(ans+"\n");
        }

        bw.flush();
    }

    static class Request implements Comparable<Request> {
        final int start, end;

        public Request(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Request o) {
            if (this.end == o.end) return this.start - o.start;

            return this.end - o.end;
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
