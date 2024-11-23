import java.io.*;

public class Main {
    private static BufferedWriter bw;

    private static final int MAX = 1_000_000;
    private static long[] dp, acc;
    private static int tc, n;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        dp = new long[MAX+1];
        for (int i = 1; i <= MAX; i++) {
            for (int j = 1; i*j<= MAX; j++) {
                dp[i*j] += i;
            }
        }

        acc = new long[MAX+1];
        for (int i = 1; i <= MAX; i++) acc[i] = acc[i-1] + dp[i];

        tc = readInt();

        while (tc-- > 0) {
            n = readInt();
            bw.write(acc[n]+"\n");
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