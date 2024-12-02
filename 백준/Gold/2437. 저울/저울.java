import java.io.*;
import java.util.*;
public class Main {
    private static BufferedWriter bw;

    private static int[] nums;
    private static int n, max;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = readInt();

        Arrays.sort(nums);

        max = 1;
        for (int num : nums) {
            if (num > max) break;

            max += num;
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
