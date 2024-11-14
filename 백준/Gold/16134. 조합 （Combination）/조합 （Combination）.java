import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static final int MOD = 1_000_000_007;
    private static long[] factArr;
    private static long nF, rF, n_rF, rIF, n_rIF, ans;
    private static int n, r;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        r = readInt();

        factArr = new long[n+1];
        factArr[0] = factArr[1] = 1;

        nF = factorial(n);
        rF = factorial(r);
        n_rF = factorial(n-r);

        rIF = iFactorial(rF);
        n_rIF = iFactorial(n_rF);

        ans = (nF * rIF) % MOD;
        ans *= n_rIF;
        ans %= MOD;

        bw.write(ans + "");

        bw.flush();
    }

    private static long factorial(int n) {
        if (factArr[n] > 0) return factArr[n];

        long res = 1;

        for (int i = 2; i <= n; i++) {
            res *= i;
            res %= MOD;

            factArr[i] = res;
        }

        return factArr[n];
    }

    private static long iFactorial(long n) {
        return modPow(n, MOD-2);
    }

    private static long modPow(long base, int exp) {
        if (exp == 0 || exp == 1) return base;

        long res = modPow(base, exp/2);

        res *= res;
        res %= MOD;

        if (exp % 2 == 1) {
            res *= base;
            res %= MOD;
        }

        return res;
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
