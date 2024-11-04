import java.io.*;
import java.util.*;

public class Main {
    private static int[] nums;
    private static int n, m, l, r, sum, cnt;

    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();

        nums = new int[n];

        for (int i = 0; i < n; i++) nums[i] = readInt();

        cnt = 0;

        l = 0;
        while (l < n) {
            sum = nums[l];
            r = l + 1;

            while (l <= r) {
                if (sum == m) {
                    cnt++;
                    break;
                } else if (sum < m) {
                    if (r >= n) break;
                    sum += nums[r++];
                } else {
                    sum -= nums[l++];
                }
            }

            l++;
        }

        System.out.print(cnt);
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
