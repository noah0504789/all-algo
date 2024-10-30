import java.io.*;
import java.util.*;

public class Main {
    private static int[] nums;
    private static int n, k, sortCnt;

    public static void main(String... args) throws IOException {
        n = readInt();
        k = readInt();

        nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = readInt();

        sortCnt = 0;
        quickSort(0, n-1);

        System.out.print(-1);
    }

    private static void quickSort(int left, int right) {
        if (left >= right) return;

        int pivot = partition(left, right);
        quickSort(left, pivot-1);
        quickSort(pivot+1, right);
    }

    private static int partition(int left, int right) {
        int pivot = nums[right], i = left - 1;
        for (int j = left; j < right; j++) {
            if (nums[j] > pivot) continue;

            swap(++i, j);
        }

        if (right != ++i) swap(i, right);

        return i;
    }

    private static void swap(int src, int dest) {        
        int temp = nums[src];
        nums[src] = nums[dest];
        nums[dest] = temp;
        
        if (++sortCnt == k) {
            int min = Math.min(nums[src], nums[dest]);
            int max = Math.max(nums[src], nums[dest]);

            System.out.print(min + " " + max);
            System.exit(0);
        }
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
