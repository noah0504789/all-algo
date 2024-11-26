import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static Map<Long, Integer> map;
    private static long[] acc1, acc2;
    private static long sum, ans;
    private static int tot, m, n;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        ans = 0;

        tot = readInt();
        m = readInt();
        n = readInt();

        acc1 = new long[m*2+1];
        acc2 = new long[n*2+1];
        for (int i = 1; i <= m; i++) acc1[i] = acc1[i+m] = readInt();
        for (int i = 1; i <= n; i++) acc2[i] = acc2[i+n] = readInt();
        for (int i = 1; i <= m*2; i++) acc1[i] += acc1[i-1];
        for (int i = 1; i <= n*2; i++) acc2[i] += acc2[i-1];

        if (acc1[m] == tot) ans++;
        if (acc2[n] == tot) ans++;

        map = new HashMap<>();
        map.put(acc1[m], 1);

        for (int i = 1; i <= m; i++) {
            for (int j = i; j < i+m-1; j++) {
                sum = acc1[j] - acc1[i-1];
                if (sum == tot) ans++;
                if (sum >= tot) continue;

                map.put(sum, map.getOrDefault(sum, 0)+1);
            }
        }

        ans += map.getOrDefault(tot-acc2[n], 0);

        for (int i = 1; i <= n; i++) {
            for (int j = i; j < i+n-1; j++) {
                sum = acc2[j] - acc2[i-1];
                if (sum == tot) ans++;

                ans += map.getOrDefault(tot-sum, 0);
            }
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
