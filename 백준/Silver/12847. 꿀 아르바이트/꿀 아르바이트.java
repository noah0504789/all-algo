import java.io.*;

public class Main {
    private static int[] pay;
    private static long[] suffixSum;
    private static int n, m;
    private static long max;

    public static void main(String... args) throws IOException {
        max = 0;
        
        n = readInt();
        m = readInt();
        
        pay = new int[n];
        suffixSum = new long[n];
        for (int i = 0; i < n; i++) {
            pay[i] = readInt();
            suffixSum[i] = pay[i];
            
            if (i > 0) suffixSum[i] += suffixSum[i-1];
        }
        
        for (int start = 0; start <= n-m; start++) {            
            long startSum = start > 0 ? suffixSum[start-1] : 0;
            
            int end = start + m - 1;
            long endSum = suffixSum[end];
            
            max = Math.max(max, endSum-startSum);
        }

        System.out.print(max);
    }

    private static int readInt() throws IOException {
        int result = 0, c = System.in.read();

        while (c <= ' ') c = System.in.read();

        while (c >= '0' && c <= '9') {
            result *= 10;
            result += c - '0';
            c = System.in.read();
        }

        return result;
    }
}
