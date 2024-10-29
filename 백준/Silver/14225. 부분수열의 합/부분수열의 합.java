import java.io.*;
import java.util.*;

public class Main {
    private static Set<Integer> set;
    private static BitSet visited;
    private static int[] nums;
    private static int n, ans;

    public static void main(String... args) throws IOException {
        ans = 1;

        n = readInt();
        nums = new int[n];

        for (int i = 0; i < n; i++) nums[i] = readInt();

        set = new HashSet<>();
        visited = new BitSet();

        dfs(0);

        while (set.contains(ans)) ans++;

        System.out.print(ans);
    }

    private static void dfs(int depth) {
        if (depth == n) {
            int result = 0;

            for (int i = 0; i < n; i++) {
                if (visited.get(i)) continue;

                result += nums[i];
            }

            set.add(result);
            return;
        }

        visited.set(depth);
        dfs(depth+1);
        visited.clear(depth);
        dfs(depth+1);
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
