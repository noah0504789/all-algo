import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static PriorityQueue<Integer> pq;
    private static Integer[] temp;
    private static int n, m, req, limit, ans, cost;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        pq = new PriorityQueue<>();
//        temp = new PriorityQueue<>(Comparator.reverseOrder());
        temp = new Integer[101];

        n = readInt();
        m = readInt();

        for (int i = 0; i < n; i++) {
            req = readInt();
            limit = readInt();

            Arrays.fill(temp, 0);

            for (int j = 0; j < req; j++) temp[j] = readInt();

            if (req < limit) {
                pq.offer(1);
            } else {
                Arrays.sort(temp, (a, b) -> b - a);

                pq.offer(temp[limit-1]);
            }
        }

        ans = 0;

        while (!pq.isEmpty()) {
            cost = pq.poll();
            if (cost > m) break;
            m -= cost;
            ans++;
        }

        bw.write(ans+"");
        bw.flush();
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
