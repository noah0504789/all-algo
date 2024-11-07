import java.io.*;
import java.util.*;

public class Main {
    private static int[] nums;
    private static int n, k, l, r;

    public static void main(String... args) throws IOException {
        k = readInt();
        n = readInt();        
        
        nums = new int[k];
        
        r = 0;
        
        for (int i = 0; i < k; i++) {
            nums[i] = readInt();
            r = Math.max(r, nums[i]);
        }
        
        System.out.print(binarySearch(1, r));
    }
    
    public static long binarySearch(long left, long right) {
        long mid = 0;

        while (left <= right) {
            mid = (left+right)/2;

            long cnt = 0;
            for (int num : nums) cnt += num / mid;

            if (cnt < n) right = mid-1;
            else left = mid+1;
        }

        return right;
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
