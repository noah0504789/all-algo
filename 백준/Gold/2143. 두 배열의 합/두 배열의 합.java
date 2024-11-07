import java.io.*;
import java.util.*;

public class Main {
    private static Map<Integer, Integer> map;
    private static List<Integer> list;
    private static int[] a, b;
    private static int t, n, m, aSum, bSum;
    private static long ans;

    public static void main(String... args) throws IOException {
        t = readInt();

        n = readInt();
        a = new int[n];

        for (int i = 0; i < n; i++) a[i] = readInt();

        m = readInt();
        b = new int[m];

        for (int i = 0; i < m; i++) b[i] = readInt();

        ans = 0;

        list = new ArrayList<>();
        for (int l = 0; l < n; l++) {
            aSum = 0;
            for (int r = l; r < n; r++) {
                aSum += a[r];
                list.add(aSum);
            }
        }

        map = new HashMap<>();
        for (int l = 0; l < m; l++) {
            bSum = 0;
            for (int r = l; r < m; r++) {
                bSum += b[r];
                map.put(bSum, map.getOrDefault(bSum, 0)+1);
            }
        }

        for (int aSum : list) ans += map.getOrDefault(t-aSum, 0);

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
