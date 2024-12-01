import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static long[] dp;
    private static int[] boxes;
    private static long max;
    private static int n;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        boxes = new int[n];
        for (int i = 0; i < n; i++) boxes[i] = readInt();

        dp = new long[n];
        
        max = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;

            for (int j = 0; j < i; j++) {
                if (boxes[i] > boxes[j]) dp[i] = Math.max(dp[i], dp[j]+1);
            }
            
            max = Math.max(max, dp[i]);
        }

        bw.write(max+"");
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
