import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static int[] nums, prefixSum, suffixSum;
    private static int n, max;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        nums = new int[n];
        prefixSum = new int[n];
        suffixSum = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = prefixSum[i] = readInt();
            if (i > 0) prefixSum[i] += prefixSum[i-1];
        }

        for (int i = n-1; i >= 0; i--) {
            suffixSum[i] = nums[i];
            if (i < n-1) suffixSum[i] += suffixSum[i+1];            
        }

        max = 0;

        for (int i = 1; i < n-1; i++) {
            max = Math.max(max, prefixSum[i-1] + prefixSum[n-2] - nums[i]); // 왼쪽 꿀통 / 오른쪽 꿀벌
            max = Math.max(max, suffixSum[i+1] + suffixSum[1] - nums[i]); // 왼쪽 꿀벌 / 오른쪽 꿀통
            max = Math.max(max, prefixSum[i] - nums[0] + suffixSum[i] - nums[n-1]); // 왼쪽 꿀벌 / 오른쪽 꿀벌
        }

        bw.write(max+"");
        bw.flush();
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
