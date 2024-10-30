import java.io.*;
import java.util.*;

public class Main {
    private static Map<Integer, Integer> map, sortedMap;
    private static int[] nums, sortedNums;
    private static int n, max;

    public static void main(String... args) throws IOException {
        n = readInt();

        nums = new int[n];
        map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            nums[i] = readInt();
            map.put(nums[i], i);
        }

        Arrays.sort(nums);
        
        sortedMap = new HashMap<>();
        for (int i = 0; i < n; i++) sortedMap.put(nums[i], i);

        max = 0;

        for (int num : nums) max = Math.max(max, map.get(num) - sortedMap.get(num));

        System.out.println(max);
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
