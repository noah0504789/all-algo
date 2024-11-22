import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;
    private static StringBuilder sb;

    private static long[] prefixSum;
    private static long max, ans, sum;
    private static int n, l, src, dest;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        n = readInt();
        ans = 0;
        prefixSum = new long[1001];

        while (n-- > 0) {
            l = readInt();

            for (int i = 1; i <= l; i++) {
                prefixSum[i] = readInt();
                prefixSum[i] += prefixSum[i-1];
            }

            max = Long.MIN_VALUE;
            src = 1;
            dest = l;

            for (int i = 1; i <= l; i++) {
                for (int j = i; j <= l; j++) {
                    sum = prefixSum[j] - prefixSum[i-1];

                    if (max < sum) {
                        max = sum;
                        src = i;
                        dest = j;
                    } else if (max == sum && dest-src > j-i) {
                        src = i;
                        dest = j;
                    }
                }
            }

            ans += max;

            sb.append(src).append(" ").append(dest).append("\n");
        }

        sb.insert(0, ans + "\n");

        bw.write(sb.toString());
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
 