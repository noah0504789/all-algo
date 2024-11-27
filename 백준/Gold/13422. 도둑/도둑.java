import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static long[] acc;
    private static int[] houses;
    private static long ans;
    private static int tc, n, m, k;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        tc = readInt();

        while (tc-- > 0) {
            n = readInt();
            m = readInt();
            k = readInt();

            houses = new int[n*2+1];
            for (int i = 1; i <= n; i++) houses[i] = houses[i+n] = readInt();

            acc = new long[n+m+1];
            for (int i = 1; i <= n+m; i++) acc[i] = acc[i-1] + houses[i];

            ans = 0;
            for (int start = 1; start <= n+m; start++) {
                int end = start + m - 1;
                if (end >= n+m) break;

                if (acc[end] - acc[start-1] < k) ans++;

                if (n == m) break;
            }

            bw.write(ans+"\n");
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
