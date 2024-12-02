import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static PriorityQueue<Schedule> pqTemp;
    private static PriorityQueue<Integer> pq;
    private static Schedule cur;
    private static int n, ans;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        pqTemp = new PriorityQueue<>();

        n = readInt();
        for (int i = 0; i < n; i++) pqTemp.offer(new Schedule(readInt(), readInt()));

        pq = new PriorityQueue<>();

        while (!pqTemp.isEmpty()) {
            cur = pqTemp.poll();

            pq.offer(cur.reword);

            if (pq.size() > cur.deadline) pq.poll();
        }

        ans = 0;

        while (!pq.isEmpty()) ans += pq.poll();

        bw.write(ans+"");
        bw.flush();
    }

    static class Schedule implements Comparable<Schedule> {
        final int reword, deadline;

        public Schedule(int reword, int deadline) {
            this.reword = reword;
            this.deadline = deadline;
        }

        @Override
        public int compareTo(Schedule o) {
            if (this.deadline == o.deadline) return o.reword - this.reword;

            return this.deadline - o.deadline;
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
