import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int n, l, r;
    private static long ans;
    private static int[] arr;
    private static long[] dp;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        while ((n = readInt()) != 0) {
            arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = readInt();
            dp = new long[n];
            ans = dp[0] = arr[0];
            for (int i = 1; i < n; i++) {
                dp[i] = Math.max(dp[i-1] + arr[i], arr[i]);
                ans = Math.max(ans, dp[i]);
            }
            
            sb.append(ans+"\n");
        }

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