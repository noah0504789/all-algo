import java.io.*;
import java.util.*;

public class Main {
    
    private static int n;
    private static int[] arr;
    private static long[] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        arr = new int[n+1];
        for (int i = 1; i <= n; i++) arr[i] = readInt();
        
        dp = new long[n+1];
        dp[1] = arr[1];
        for (int i = 2; i <= n; i++) {
            for (int j = i; j >= 0; j--) dp[i] = Math.max(dp[i], dp[i-j]+arr[j]);
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