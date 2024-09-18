import java.io.*;

public class Main {
    
    private static int[] nums;
    private static int n, max;
    private static long tot;

    public static void main(String... args) throws IOException {
        n = readInt();
        max = 0;

        nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = readInt();
            max = Math.max(max, nums[i]);
        }

        tot = readLong();

        int l = 0, r = max, m = (l+r)/2;
        while (l <= r) {
            if (sum(m) > tot) r = m-1;
            else l = m+1;

            m = (l+r)/2;
        }

        System.out.print(m);
    }

    private static int sum(int mid) {
        int sum = 0;
        for (int num : nums) {
            if (num > mid) sum += mid;
            else sum += num;
        }
        return sum;
    }

    private static int readInt() throws IOException {
        int result = 0, c = System.in.read();

        while (c <= ' ') c = System.in.read();

        while (c >= '0' && c <= '9') {
            result *= 10;
            result += c - '0';
            c = System.in.read();
        }

        return result;
    }
    
    private static long readLong() throws IOException {
        long result = 0;
        int c = System.in.read();

        while (c <= ' ') c = System.in.read();

        while (c >= '0' && c <= '9') {
            result *= 10;
            result += c - '0';
            c = System.in.read();
        }

        return result;
    }    
}