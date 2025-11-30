import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, k, INF = Integer.MAX_VALUE;
    private static int[] arr, dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        k = readInt();
        
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = readInt();
        Arrays.sort(arr);
        
        dp = new int[k+1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int coin : arr) {
            for (int i = k; i >= coin; i--) {
                if (dp[i-coin] != INF) dp[i] = Math.min(dp[i], dp[i-coin] + 1);
            }
        }

        System.out.print(dp[k] == INF ? -1 : dp[k]);
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