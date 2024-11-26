import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static long[] prefixSum, cnts;
    private static Map<Integer, Integer> acc;
    private static int n, k, q, key, jump, cnt, src, dest;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        k = readInt();

        acc = new HashMap<>();
        for (int i = 0; i < k; i++) {
            key = readInt();
            acc.put(key, acc.getOrDefault(key, 0)+1);
        }

        cnts = new long[n+1];
        for (Map.Entry<Integer, Integer> entry : acc.entrySet()) {
            jump = entry.getKey();
            cnt = entry.getValue();

            for (int i = 1; i <= n; i += jump) cnts[i] += cnt;
        }

        prefixSum = new long[n+1];
        for (int i = 1; i <= n; i++) prefixSum[i] = prefixSum[i-1] + cnts[i];

        q = readInt();

        for (int i = 0; i < q; i++) {
            src = readInt()+1;
            dest = readInt()+1;

            bw.write((prefixSum[dest] - prefixSum[src-1]) + "\n");
        }

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
