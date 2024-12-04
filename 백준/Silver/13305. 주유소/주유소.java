import java.io.*;

public class Main {
    private static BufferedWriter bw;

    private static long[] dists, costs;
    private static long total, cost;
    private static int n;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();

        dists = new long[n];
        for (int i = 1; i < n; i++) dists[i] = readLong();

        costs = new long[n+1];
        for (int i = 1; i <= n; i++) costs[i] = readLong();

        cost = costs[1];
        total = 0;

        for (int i = 1; i < n; i++) {
            if (costs[i] < cost) cost = costs[i];

            total += cost * dists[i];
        }

        bw.write(total +"");
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

    public static long readLong() throws IOException {
        long r = 0, c = System.in.read();

        while (c <= ' ') c = System.in.read();
        while (c >= '0' && c <= '9') {
            r *= 10;
            r += c - '0';
            c = System.in.read();
        }

        return r;
    }
}
