import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static final int maxCost = 10_000;
    private static int[] memories, costs, dp;
    private static int n, m, ans;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        m = readInt();

        memories = new int[n+1];
        for (int i = 1; i <= n; i++) memories[i] = readInt();

        costs = new int[n+1];
        for (int i = 1; i <= n; i++) costs[i] = readInt();

        dp = new int[maxCost+1];

        for (int i = 1; i <= n; i++) {
            int memory = memories[i], cost = costs[i];

            for (int j = maxCost; j >= cost; j--) dp[j] = Math.max(dp[j], dp[j-cost] + memory);
        }

        for (int i = 0; i <= dp.length; i++) {
            if (dp[i] >= m) {
                ans = i;
                break;
            }
        }

        bw.write(ans+"");
        bw.write("\n");

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
