import java.io.*;
import java.util.*;

public class Main {
        
    private static StringBuilder sb = new StringBuilder();
    private static int tc, maxN;
    private static int[] tArr;
    private static long[] dp;
    
    public static void main(String... args) throws IOException {
        tc = readInt();
        tArr = new int[tc];
        for (int i = 0; i < tc; i++) {
            tArr[i] = readInt();
            maxN = Math.max(maxN, tArr[i]);
        }
        
        dp = new long[Math.max(5, maxN)+1];
        dp[1] = dp[2] = dp[3] = 1;
        dp[4] = dp[5] = 2;
        for (int i = 6; i <= maxN; i++) dp[i] = dp[i-5]+dp[i-1];
        
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