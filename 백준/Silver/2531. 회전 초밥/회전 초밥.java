import java.io.*;
import java.util.*;

public class Main {
    private static Map<Integer, Integer> map;
    private static int[] nums;
    private static int n, d, k, c, ans;

    public static void main(String... args) throws IOException {
        n = readInt();
        d = readInt();
        k = readInt();
        c = readInt();

        nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = readInt();

        map = new HashMap<>();

        for (int i = 0; i < k; i++) map.put(nums[i], map.getOrDefault(nums[i], 0)+1);

        ans = map.keySet().size() + (map.containsKey(c) ? 0 : 1);
        
        for (int i = 0; i < n; i++) {
            int addIdx = i+k;
            if (addIdx >= n) addIdx %= n;
            int addKey = nums[addIdx];
            map.put(addKey, map.getOrDefault(addKey, 0)+1);

            int removeIdx = i, removeKey = nums[removeIdx];
            map.put(removeKey, map.get(removeKey)-1);
            if (map.get(removeKey) == 0) map.remove(removeKey);

            ans = Math.max(ans, map.keySet().size() + (map.containsKey(c) ? 0 : 1));
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
