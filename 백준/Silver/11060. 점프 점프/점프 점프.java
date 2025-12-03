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
        dp[1] = 0;
        for (int i = 1; i <= n; i++) {
            int jump = arr[i];
            for (int j = i+1; j <= i+jump; j++) {
                if (j <= n && dp[i] != INF) dp[j] = Math.min(dp[j], dp[i] + 1);
            }            
        }
        
        System.out.print(dp[n] == INF ? -1 : dp[n]);
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