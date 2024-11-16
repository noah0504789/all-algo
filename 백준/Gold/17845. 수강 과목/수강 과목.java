import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static int[] times, points, dp;
    private static int n, k;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        k = readInt();

        points = new int[k+1];
        times = new int[k+1];

        for (int i = 1; i <= k; i++) {
            points[i] = readInt();
            times[i] = readInt();
        }

        dp = new int[n+1];

        for (int i = 1; i <= k; i++) {
            int time = times[i], point = points[i];

            for (int j = n; j > 0; j--) {
                if (j < time) continue;

                dp[j] = Math.max(dp[j], dp[j-time]+point);
            }
        }

        bw.write(dp[n]+"");

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
