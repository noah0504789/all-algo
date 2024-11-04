import java.io.*;
import java.util.*;

public class Main {
    private static int[] counts, nums;
    private static int n, k, l, r, ans;

    public static void main(String... args) throws IOException {
        n = readInt();
        k = readInt();

        nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = readInt();

        if (n == k) {
            System.out.print(n);
            return;
        }
        
        ans = 0;
        counts = new int[100_001];                

        l = r = 0;

        while (r < n) {            
            while (r < n && counts[nums[r]] < k) counts[nums[r++]]++;

            ans = Math.max(ans, r - l);
            counts[nums[l++]]--;
        }

        System.out.print(ans);
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
