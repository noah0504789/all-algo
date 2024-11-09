import java.io.*;
import java.util.*;

public class Main {
    private static PriorityQueue<Integer> pq;
    private static int n, ans;

    public static void main(String... args) throws IOException {
        n = readInt();

        pq = new PriorityQueue<>(Comparator.reverseOrder());

        for (int i = 0; i < n * n; i++) pq.offer(readInt());

        for (int i = 0; i < n; i++) ans = pq.poll();

        System.out.print(ans);
    }

    public static int readInt() throws IOException {
        int r = 0, c = System.in.read();

        while (c <= ' ') c = System.in.read();
        boolean negative = false;
        if (c == '-') {
            negative = true;
            c = System.in.read();
        }

        while (c >= '0' && c <= '9') {
            r *= 10;
            r += c - '0';
            c = System.in.read();
        }

        return negative ? -r : r;
    }
}
