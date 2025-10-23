import java.io.*;
import java.util.*;

public class Main {
    
    private static int n;
    private static long INF = Long.MAX_VALUE;
    private static int[] arr;
    private static long[] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = readInt();
        
        dp = new long[n];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        
        for (int i = 1; i < n; i++) {
            long best = INF;
            for (int j = 0; j < i; j++) {
                best = Math.min(best, Math.max(dp[j], power(j, i)));
            }
            dp[i] = best;
        }
        
        System.out.print(dp[n-1]);
    }
        
    private static long power(int i, int j) {
        long dist = (long)(j-i);
        long diff = Math.abs(arr[j]-arr[i]);
        
        return dist * (1L + diff);
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