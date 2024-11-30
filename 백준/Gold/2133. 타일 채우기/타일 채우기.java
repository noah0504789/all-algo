import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static long[] dp;
    private static int n;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        
        if (n % 2 != 0) {
            bw.write("0");
            bw.flush();
            return;
        }

        dp = new long[n+1];
        dp[0] = 1;
        dp[2] = 3;

        for (int i = 4; i <= n; i+=2) {
            dp[i] = dp[i-2]*3;
            for (int j = i-4; j >= 0; j-=2) dp[i] += (dp[j] * 2);
        }

        bw.write(dp[n]+"\n");
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
