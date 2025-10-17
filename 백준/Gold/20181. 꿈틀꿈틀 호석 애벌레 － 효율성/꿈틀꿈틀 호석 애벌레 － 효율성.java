import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, l;
    private static long sum, k;
    private static int[] arr;
    private static long[] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        k = readInt();
        arr = new int[n+1];
        dp = new long[n+1];
        
        for (int i = 1; i <= n; i++) arr[i] = readInt();
        
        l = 0;
        sum = 0L;
        for (int r = 1; r <= n; r++) {
            sum += arr[r];
            dp[r] = dp[r-1];
            while (sum >= k) {
                dp[r] = Math.max(dp[r], dp[l] + (sum-k));
                sum -= arr[++l];
            }
        }

        System.out.print(dp[n]);
    }

    public static int readInt() throws IOException {
        int r = 0, c = System.in.read();
        boolean negative = false;       

        while (c <= ' ') c = System.in.read();
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