import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static Set<Integer> set;
    private static int p, a;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        set = new HashSet<>();
        set.add(3);

        while (true) {
            p = readInt();
            a = readInt();

            if (p == 0 && a == 0) break;

            if (isPrime(p)) bw.write("no");
            else if (modPow(a, p) == a) bw.write("yes");
            else bw.write("no");

            bw.write("\n");
        }

        bw.flush();
    }

    private static boolean isPrime(int prime) {
        if (set.contains(prime)) return true;

        for (int i = 2; i <= Math.sqrt(prime); i++) {
            if (prime % i == 0) return false;
        }

        set.add(prime);

        return true;
    }

    private static long modPow(int base, int exp) {
        if (exp == 1) return base;

        long ret = modPow(base, exp/2);

        ret *= ret;
        ret %= p;

        if (exp % 2 == 1) {
            ret *= base;
            ret %= p;
        }

        return ret;
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
