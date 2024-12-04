import java.io.*;

public class Main {
    private static BufferedWriter bw;

    private static int[] dists, costs;
    private static int n, min, next, dist;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();

        dists = new int[n];
        for (int i = 1; i < n; i++) dists[i] = readInt();

        costs = new int[n+1];
        for (int i = 1; i <= n; i++) costs[i] = readInt();

        min = 0;

        for (int i = 1; i < n; i++) {
            dist = dists[i];
            next = i+1;

            while (next < n && costs[i] <= costs[next]) dist += dists[next++];

            min += dist * costs[i];

            if (next == n-1) break;

            i = next-1;
        }

        bw.write(min+"");
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
