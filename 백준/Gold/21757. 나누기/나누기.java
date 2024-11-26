import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static long[] acc;
    private static int[] nums;
    private static long sum1, sum2, sum3, sum4, ans;
    private static int n;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        ans = 0;

        n = readInt();

        nums = new int[n+1];
        acc = new long[n+1];
        
        for (int i = 1; i <= n; i++) {
            nums[i] = readInt();
            acc[i] = acc[i-1] + nums[i];
        }

        for (int i = 1; i <= n-3; i++) {
            sum1 = acc[i];
            for (int j = i+1; j <= n-2; j++) {
                sum2 = acc[j] - acc[i];

                if (sum1 != sum2) continue;
                for (int k = j+1; k <= n-1; k++) {
                    sum3 = acc[k] - acc[j];

                    if (sum2 != sum3) continue;

                    sum4 = acc[n] - acc[k];

                    if (sum3 != sum4) continue;

                    ans++;
                }
            }
        }

        bw.write(ans+"");
        bw.flush();
    }

    public static int readInt() throws IOException {
        int r = 0, c = System.in.read();

        while (c <= ' ') c = System.in.read();
        boolean negative = false;
        if (c == '-') {
            negative = true;
            c = System.in.read();
        }
        while (c >= '0' && c <= '9') {
            r *= 10;
            r += c - '0';
            c = System.in.read();
        }

        return negative ? -r : r;
    }
}
