import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, k, INF = Integer.MAX_VALUE;
    private static int[] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        k = readInt();
        
        dp = new int[n+1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int i = 0; i <= n; i++) {
            if (dp[i] == INF) continue;
            
            if (i+1<=n) dp[i+1] = Math.min(dp[i+1], dp[i]+1);
            if (i+i/2<=n) dp[i+i/2] = Math.min(dp[i+i/2], dp[i]+1);
        }
        
        System.out.print(dp[n] <= k ? "minigimbob" : "water");
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