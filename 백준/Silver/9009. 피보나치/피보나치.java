import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static Stack<Long> stack;
    private static long[] fib;
    private static long sum;
    private static int tc, n, last;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        stack = new Stack<>();

        fib = new long[100_001];
        fib[0] = 0;
        fib[1] = 1;

        for (int i = 2; i < fib.length; i++) fib[i] = fib[i-1] + fib[i-2];

        tc = readInt();

        while (tc-- > 0) {
            n = readInt();

            for (int i = 1; i < fib.length; i++) {
                if (fib[i] > n) {
                    last = i-1;
                    break;
                }
            }

            sum = 0;

            for (int i = last; i >= 1; i--) {
                if (n == sum) break;

                if (n >= sum + fib[i]) {
                    sum += fib[i];
                    stack.push(fib[i]);
                }
            }

            while (!stack.isEmpty()) bw.write(stack.pop()+" ");

            bw.write("\n");
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
