import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static int[] nums;
    private static int n, k, a, b, c;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();

        nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = readInt();

        Arrays.sort(nums);

        for (int i = n-1; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                for (int l = j; l >= 0; l--) {
                    k = nums[i];
                    a = nums[j];
                    b = nums[l];

                    c = k-a-b;

                    if (c <= 0) break;

                    if (Arrays.binarySearch(nums, c) < 0) continue;

                    bw.write(k+"");
                    bw.flush();
                    return;
                }
            }
        }
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
