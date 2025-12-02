import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, INF = Integer.MAX_VALUE;
    private static int[] dp, coins = {2, 5};
    
    public static void main(String... args) throws IOException {
        n = readInt();
        dp = new int[n+1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int coin : coins) {
            for (int i = coin; i <= n; i++) {
                if (dp[i-coin] != INF) dp[i] = Math.min(dp[i], dp[i-coin] + 1);
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