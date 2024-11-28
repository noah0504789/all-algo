import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static final int MAX = 1_000_000;
    private static boolean[] notPrime;
    private static long[] acc1, acc2;
    private static long cnt1, cnt2;
    private static int l, u;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        notPrime = new boolean[MAX+1];
        notPrime[0] = notPrime[1] = true;

        for (int i = 2; i <= Math.sqrt(MAX); i++) {
            if (notPrime[i]) continue;

            for (int j = i*i; j <= MAX; j+=i) notPrime[j] = true;
        }

        acc1 = new long[MAX+1];
        acc2 = new long[MAX+1];
        
        acc1[2] = acc2[2] = 1;
        for (int i = 3; i <= MAX; i++) {
            acc1[i] = acc1[i-1] + (!notPrime[i] ? 1 : 0);
            acc2[i] = acc2[i-1] + (!notPrime[i] && (i % 4 == 1) ? 1 : 0);
        }

        while (true) {
            l = readInt();
            u = readInt();

            if (l == -1 && u == -1) break;

            cnt1 = (u >= 0 ? acc1[u] : 0) - (l >= 1 ? acc1[l-1] : 0);
            cnt2 = (u >= 0 ? acc2[u] : 0) - (l >= 1 ? acc2[l-1] : 0);

            bw.write(l + " " + u + " " + cnt1 + " " + cnt2 + "\n");
        }

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