import java.io.*;
import java.util.*;

public class Main {
    private static Set<Integer> set;
    private static BitSet visited;
    private static int[] nums;
    private static int n, ans, max;

    public static void main(String... args) throws IOException {
        ans = 1;

        n = readInt();
        nums = new int[n];

        for (int i = 0; i < n; i++) nums[i] = readInt();
        
        Arrays.sort(nums);
        
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (sum + 1 < nums[i]) break;
            
            sum += nums[i];
        }

        System.out.print(sum+1);
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
