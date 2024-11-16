import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static int[] dp;
    private static int n, k;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        k = readInt();
        
        dp = new int[n+1];

        for (int i = 1; i <= k; i++) {
            int point = readInt();
            int time = readInt();            

            for (int j = n; j >= time; j--) {
                if (j < time) continue;
                if (dp[j] >= dp[j-time]+point) continue;

                dp[j] = dp[j-time]+point;
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
