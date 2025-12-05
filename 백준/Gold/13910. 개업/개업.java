import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m, INF = Integer.MAX_VALUE;
    private static int[] woks, dp;
    private static boolean[] possible;
    private static List<Integer> coins;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        
        woks = new int[m];
        for (int i = 0; i < m; i++) woks[i] = readInt();
        
        possible = new boolean[n+1];
        for (int i = 0; i < m-1; i++) {
            for (int j = i+1; j < m; j++) {
                possible[woks[i]] = true;
                possible[woks[j]] = true;
                
                int v = woks[i] + woks[j];
                if (v > n) continue;
                
                possible[v] = true;                
            }
        }
        coins = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (possible[i]) coins.add(i);
        }
        
        dp = new int[n+1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int coin : coins) {
            for (int i = coin; i <= n; i++) {
                if (dp[i-coin] == INF) continue;
                dp[i] = Math.min(dp[i], dp[i-coin]+1);
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