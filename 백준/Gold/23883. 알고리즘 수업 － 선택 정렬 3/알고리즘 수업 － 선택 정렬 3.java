import java.io.*;
import java.util.*;

public class Main {
    private static Map<Integer, Integer> map;
    private static int[] nums, sortedNums;
    private static int n, k, cnt;

    public static void main(String... args) throws IOException {
        n = readInt();
        k = readInt();
        cnt = 0;

        nums = new int[n];
        sortedNums = new int[n];
        map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            nums[i] = readInt();
            sortedNums[i] = nums[i];
            map.put(nums[i], i);
        }
        
        Arrays.sort(sortedNums);

        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] == sortedNums[i]) continue;
            
            int min = nums[i], max = sortedNums[i];
            swap(i, map.get(max));
            cnt++;
            
            if (cnt == k) {
                System.out.print(min + " " + max);
                System.exit(0);
            }
        }

        System.out.print(-1);
    }

    private static void swap(int src, int dest) {
        int temp = nums[src];
        nums[src] = nums[dest];
        nums[dest] = temp;
        
        map.put(nums[src], src);
        map.put(nums[dest], dest);
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
