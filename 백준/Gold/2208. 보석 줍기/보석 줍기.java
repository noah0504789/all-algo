import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static int[] jewels, dp;
    private static int n, m, min, max;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        m = readInt();

        jewels = new int[n+1];
        dp = new int[n+1];
        max = 0;    
        min = 0;

        for (int i = 1; i <= n; i++) {
            jewels[i] = readInt();

            dp[i] = dp[i-1] + jewels[i];

            if (i >= m) {
                min = Math.min(min, dp[i-m]);
                max = Math.max(max, dp[i] - min);
            }
        }

        bw.write(max+"");
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
