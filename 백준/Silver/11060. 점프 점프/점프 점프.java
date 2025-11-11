import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, INF = Integer.MAX_VALUE;
    private static int[] arr;
    private static long[] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        arr = new int[n+1];
        for (int i = 0; i < n; i++) arr[i] = readInt();
        dp = new long[n];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        
        for (int e = 0; e < n; e++) {
            for (int s = 0; s < e; s++) {
                if (arr[s]+s < e) continue;
                dp[e] = Math.min(dp[e], dp[s]+1);
            }
            if (dp[e] == INF) break;
        }

        System.out.print(dp[n-1] == INF ? -1 : dp[n-1]);
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