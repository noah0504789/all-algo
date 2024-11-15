import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static final int MOD = 1_000_000_000;
    private static final long[][] base = {{1, 1}, {1, 0}};
    private static int n, nAbs, sign, abs;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        nAbs = Math.abs(n);

        if (n == 0) {
            sign = abs = 0;
        } else {
            abs = (int) pow(base, nAbs)[1][0];
            sign = 1;

            if (n < 0 && nAbs % 2 == 0) sign = -1;
        }

        bw.write(sign+"");
        bw.write("\n");
        bw.write(Math.abs(abs)+"");

        bw.flush();
    }

    public static long[][] pow(long[][] a, int exp) {
        if (exp == 1) return a;

        long[][] res = pow(a, exp/2);

        res = mul(res, res);

        if (exp % 2 == 1) res = mul(res, a);

        return res;
    }

    private static long[][] mul(long[][] a, long[][] b) {
        long[][] c = new long[2][2];
        
        c[0][0] = ((a[0][0]*b[0][0]) % MOD + (a[0][1]*b[1][0]) % MOD) % MOD;
        c[0][1] = ((a[0][0]*b[0][1]) % MOD + (a[0][1]*b[1][1]) % MOD) % MOD;
        c[1][0] = ((a[1][0]*b[0][0]) % MOD + (a[1][1]*b[1][0]) % MOD) % MOD;
        c[1][1] = ((a[1][0]*b[0][1]) % MOD + (a[1][1]*b[1][1]) % MOD) % MOD;
        
        return c;
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
