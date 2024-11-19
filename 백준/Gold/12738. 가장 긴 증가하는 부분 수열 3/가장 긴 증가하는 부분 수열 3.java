import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static int[] a, dp;
    private static int n;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();

        a = new int[n];
        for (int i = 0; i < n; i++) a[i] = readInt();

        dp = new int[n];

        bw.write(lis()+"");

        bw.flush();
    }

    private static int lis() {
        dp[0] = a[0];

        int idx = 0;

        for (int i = 1; i < n; i++) {
            int num = a[i];
            if (dp[idx] < num) dp[++idx] = num;
            else if (dp[idx] > num) dp[lowerbound(0, idx, num)] = num;
        }

        return idx+1;
    }

    private static int lowerbound(int l, int r, int key) {
        while (l < r) {
            int mid = (l+r)/2;

            if (dp[mid] < key) l = mid+1;
            else r = mid;
        }

        return l;
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
