import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m, sum, INF = Integer.MAX_VALUE;
    private static int[] woks, dp;
    private static boolean[] woks_possible;
    private static List<Integer> coins;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        
        woks = new int[m];
        for (int i = 0; i < m; i++) woks[i] = readInt();
        
        woks_possible = new boolean[n+1];
        for (int i = 0; i < m; i++) woks_possible[woks[i]] = true;
        for (int i = 0; i < m-1; i++) {
            for (int j = i+1; j < m; j++) {
                if (woks[i] + woks[j] <= n) woks_possible[woks[i] + woks[j]] = true;    
            }            
        }
        
        coins = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (woks_possible[i]) coins.add(i);
        }
        
        dp = new int[n+1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        
        Collections.sort(coins);        
        for (int i = 1; i <= n; i++) {
            for (int wok : coins) {
                if (i<wok) continue;
                if (dp[i-wok] != INF) dp[i] = Math.min(dp[i], dp[i-wok]+1);
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