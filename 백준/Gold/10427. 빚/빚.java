import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static long[] prefixSum;
    private static int[] nums;
    private static long ans;
    private static int tc, n;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        tc = readInt();

        while (tc-- > 0) {
            n = readInt();
            nums = new int[n+1];

            for (int i = 1; i <= n; i++) nums[i] = readInt();

            Arrays.sort(nums);

            prefixSum = new long[n+1];
            for (int i = 1; i <= n; i++) prefixSum[i] = prefixSum[i-1] + nums[i];

            ans = 0;

            for (int i = 2; i <= n; i++) ans += getS(i);

            bw.write(ans+"\n");
        }

        bw.flush();
    }

    private static long getS(int dist) {
        long res = Integer.MAX_VALUE;

        for (int i = 1; i <= n-dist+1; i++) res = Math.min(res, calc(i, i+dist-1));

        return res;
    }

    private static long calc(int start, int end) {
        long all = nums[end] * (end-start+1), part = prefixSum[end] - prefixSum[start-1];

        return all - part;
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
