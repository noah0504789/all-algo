import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, INF = Integer.MAX_VALUE;
    private static int[] arr, dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        arr = new int[n+1];
        for (int i = 1; i <= n; i++) arr[i] = readInt();
        
        dp = new int[n+1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            int card = arr[i];
            for (int j = i; j <= n; j++) {
                dp[j] = Math.min(dp[j], dp[j-i]+card);
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