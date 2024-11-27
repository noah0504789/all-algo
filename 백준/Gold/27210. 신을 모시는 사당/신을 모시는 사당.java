import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static int[] nums1, nums2, dp;
    private static int n, max;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();

        nums1 = new int[n+1];
        nums2 = new int[n+1];
        for (int i = 1; i <= n; i++) {
            nums1[i] = readInt() == 1 ? 1 : -1;
            nums2[i] = -nums1[i];
        }

        dp = new int[n+1];
        for (int i = 1; i <= n; i++) dp[i] = Math.max(dp[i-1] + nums1[i], nums1[i]);

        max = 0;
        for (int i = 1; i <= n; i++) max = Math.max(max, dp[i]);

        dp = new int[n+1];
        for (int i = 1; i <= n; i++) dp[i] = Math.max(dp[i-1] + nums2[i], nums2[i]);
        
        for (int i = 1; i <= n; i++) max = Math.max(max, dp[i]);

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
