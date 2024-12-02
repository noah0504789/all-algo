import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static int[] dp;
    private static int n, s, r, ans;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        s = readInt();
        r = readInt();

        dp = new int[n+2];

        for (int i = 0; i < s; i++) dp[readInt()]--;
        for (int i = 0; i < r; i++) dp[readInt()]++;

        ans = 0;

        for (int i = 1; i <= n; i++) {
            if (dp[i] == -1) {
                if (dp[i-1] == 1) dp[i-1] = dp[i] = 0;
                else if (dp[i+1] == 1) dp[i+1] = dp[i] = 0;
                else ans++;
            }
        }

        bw.write(ans+"");
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
