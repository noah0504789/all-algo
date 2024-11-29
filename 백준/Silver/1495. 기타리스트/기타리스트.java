import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static List<Integer> list;
    private static int[] dp;
    private static int n, s, m, v, ans, vol1, vol2;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        s = readInt();
        m = readInt();

        dp = new int[m+1];
        Arrays.fill(dp, -1);
        dp[s] = 0;

        list = new ArrayList<>();

        ans = -1;

        for (int i = 1; i <= n; i++) {
            v = readInt();

            for (int j = 0; j <= m; j++) {
                if (dp[j] != i-1) continue;

                vol1 = j+v;
                vol2 = j-v;

                if (vol1 <= m) list.add(vol1);
                if (vol2 >= 0) list.add(vol2);
            }

            for (int vol : list) {
                dp[vol] = i;
                if (i == n) ans = Math.max(ans, vol);
            }

            list.clear();
        }

        bw.write(ans + "");
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
