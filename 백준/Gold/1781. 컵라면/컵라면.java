import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static PriorityQueue<Task> pqTemp;
    private static PriorityQueue<Integer> pq;
    private static Task cur;
    private static int n, ans;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        pqTemp = new PriorityQueue<>();
        pq = new PriorityQueue<>();

        n = readInt();
        for (int i = 0; i < n; i++) pqTemp.offer(new Task(readInt(), readInt()));

        ans = 0;

        while (!pqTemp.isEmpty()) {
            cur = pqTemp.poll();

            pq.offer(cur.reword);

            if (cur.deadline < pq.size()) pq.poll();
        }

        while (!pq.isEmpty()) ans += pq.poll();

        bw.write(ans+"");
        bw.flush();
    }

    static class Task implements Comparable<Task> {
        final int deadline, reword;

        public Task(int deadline, int reword) {
            this.deadline = deadline;
            this.reword = reword;
        }

        @Override
        public int compareTo(Task o) {
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
