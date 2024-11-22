import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static long[] remainCnt;
    private static int[] nums;
    private static long remainder, ans;
    private static int n, m;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        m = readInt();

        nums = new int[n];
        remainCnt = new long[m];

        ans = remainder = 0;

        for (int i = 0; i < n; i++) {
            nums[i] = readInt();

            remainder += nums[i];
            remainder %= m;

            if (remainder < 0) remainder += m;

            if (remainder == 0) ans++;

            ans += remainCnt[(int) remainder];

            remainCnt[(int) remainder]++;
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
