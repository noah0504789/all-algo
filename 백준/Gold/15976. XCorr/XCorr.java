import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static final int MOD = 1_000_000_000;
    private static BitSet xVis, xVisTemp;
    private static Map<Integer, Integer> xMap, yMap;
    private static long ans, sum;
    private static int n, idx, val, a, b, xn, yn;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        xMap = new HashMap<>();
        xVis = new BitSet();
        n = readInt();
        for (int i = 0; i < n; i++) {
            idx = readInt();
            val = readInt();

            xMap.put(idx, val);
            xVis.set(idx);
        }

        yMap = new HashMap<>();
        n = readInt();
        for (int i = 0; i < n; i++) {
            idx = readInt();
            val = readInt();

            yMap.put(idx, val);
        }

        a = readInt();
        b = readInt();

        ans = 0;
        xVisTemp = new BitSet();

        for (int t = a; t <= b; t++) ans += s(t);

        bw.write(ans +"");
        bw.flush();
    }

    private static long s(int t) {
        xVisTemp.clear();
        xVisTemp.or(xVis);

        sum = 0;

        xn = 0;
        while ((xn = xVisTemp.nextSetBit(xn)) != -1) {
            yn = (xn + t + MOD) % MOD;
            if (yMap.containsKey(yn)) sum += (xMap.get(xn) * yMap.get(yn));

            xVisTemp.clear(xn);
        }

        return sum;
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
