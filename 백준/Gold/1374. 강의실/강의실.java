import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static PriorityQueue<Schedule> pqTemp;
    private static PriorityQueue<Integer> pq;
    private static Schedule cur;
    private static int n;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();

        pqTemp = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            readInt();
            pqTemp.offer(new Schedule(readInt(), readInt()));
        }

        pq = new PriorityQueue<>();
        while (!pqTemp.isEmpty()) {
            cur = pqTemp.poll();

            if (!pq.isEmpty() && pq.peek() <= cur.start) pq.poll();

            pq.offer(cur.end);
        }

        bw.write(pq.size()+"");
        bw.flush();
    }

    static class Schedule implements Comparable<Schedule> {
        final int start, end;

        public Schedule(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Schedule o) {
            if (this.start == o.start) return this.end - o.end;

            return this.start-o.start;
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
