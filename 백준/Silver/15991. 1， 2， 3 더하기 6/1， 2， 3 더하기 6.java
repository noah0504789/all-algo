import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int tc, n, maxN, p = 1_000_000_009;
    private static int[] tArr;
    private static long[] dp;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        tc = readInt();
        tArr = new int[tc];
        for (int i = 0; i < tc; i++) {
            tArr[i] = readInt();
            maxN = Math.max(maxN, tArr[i]);
        }
        
        dp = new long[Math.max(4, maxN)+1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 2;
        dp[4] = 3;
        for (int i = 5; i <= maxN; i++) {
            dp[i] = dp[i-2] + dp[i-4] + (i-6>= 0 ? dp[i-6] : 0);
            dp[i] %= p;
        }
        
        for (int t : tArr) sb.append(dp[t]+"\n");

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