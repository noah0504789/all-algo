import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static Map<Long, Long> map;
    private static long[] prefixSum;
    private static long k, ans;
    private static int n;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        k = readInt();

        ans = 0;

        prefixSum = new long[n+1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = readInt();
            prefixSum[i] += prefixSum[i-1];
        }

        map = new HashMap<>();
        map.put(0L, 1L);
        for (int i = 1; i <= n; i++) {
            ans += map.getOrDefault(prefixSum[i]-k, 0L);
            map.put(prefixSum[i], map.getOrDefault(prefixSum[i], 0L)+1);
        }

        bw.write(ans+"");

        bw.flush();
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
