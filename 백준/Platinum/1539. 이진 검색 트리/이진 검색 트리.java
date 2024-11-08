import java.io.*;
import java.util.*;

public class Main {
    private static List<Integer> list;
    private static long ans;
    private static int[] height;
    private static int n;

    public static void main(String... args) throws IOException {
        n = readInt();

        height = new int[n];
        list = new ArrayList<>();
        
        ans = 0;

        for (int i = 0; i < n; i++) ans += getHeight(readInt());

        System.out.print(ans);
    }

    public static int binarySearchLower(int left, int right, int key) {
        while (left < right) {
            int mid = (left + right) >>> 1;

            if (list.get(mid) < key) left = mid + 1;
            else right = mid;
        }

        return right;
    }

    public static int getHeight(int node) {
        int size = list.size();
        
        int lb = binarySearchLower(0, size, node);

        int left = lb > 0 ? height[list.get(lb-1)] : 0;
        int right = lb < size ? height[list.get(lb)] : 0;

        height[node] = Math.max(left, right) + 1;
        list.add(lb, node);

        return height[node];
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
