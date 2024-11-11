import java.io.*;
import java.util.*;

public class Main {
    private static StringBuilder sb;

    private static long[] heights;
    private static int n;

    public static void main(String... args) throws IOException {
        sb = new StringBuilder();

        while ((n = (int) readLong()) > 0) {
            heights = new long[n];

            for (int i = 0; i < n; i++) heights[i] = readLong();

            sb.append(getArea(0, n-1)).append("\n");
        }

        System.out.print(sb);
    }

    public static long getArea(int low, int high) {
        if (low == high) return heights[low];

        int mid = (low+high)/2;

        long leftArea = getArea(low, mid), rightArea = getArea(mid+1, high);
        long maxArea = Math.max(leftArea, rightArea);

        return Math.max(maxArea, getMidArea(low, high, mid));
    }

    public static long getMidArea(int low, int high, int mid) {
        int l, r;
        l = r = mid;
        long h = heights[mid], max = h;

        while (low < l && r < high) {
            if (heights[l-1] < heights[r+1]) h = Math.min(h, heights[++r]);
            else h = Math.min(h, heights[--l]);

            max = Math.max(max, h * (r - l + 1));
        }

        while (r < high) {
            h = Math.min(h, heights[++r]);
            max = Math.max(max, h * (r - l + 1));
        }

        while (low < l) {
            h = Math.min(h, heights[--l]);
            max = Math.max(max, h * (r - l + 1));
        }

        return max;
    }

    public static long readLong() throws IOException {
        long r = 0, c = System.in.read();

        while (c <= ' ') c = System.in.read();
        while (c >= '0' && c <= '9') {
            r *= 10;
            r += c - '0';
            c = System.in.read();
        }

        return r;
    }
}
