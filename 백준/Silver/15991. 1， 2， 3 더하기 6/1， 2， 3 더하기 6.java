import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb = new StringBuilder();
    private static int tc, maxN, p = 1_000_000_009;
    private static int[] arr;
    private static long[] dp;
    
    public static void main(String... args) throws IOException {
        tc = readInt();
        arr = new int[tc];
        for (int i = 0; i < tc; i++) {
            arr[i] = readInt();
            maxN = Math.max(maxN, arr[i]);
        }
        
        dp = new long[Math.max(3, maxN)+1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 2;
        for (int i = 4; i <= maxN; i++) dp[i] = (dp[i-2] + dp[i-4] + (i-6>=0 ? dp[i-6] : 0)) % p;
        
        for (int t : arr) sb.append(dp[t]+"\n");

        System.out.print(sb);
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