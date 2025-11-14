import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, k, ans, p = 1_000_000_003;
    private static int[][] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        k = readInt();
        
        if (k>n/2) {
            System.out.print(0);
            return;
        }
        
        if (k==0) {
            System.out.print(1);
            return;
        }
        
        dp = new int[n+1][k+1];        
        for (int i = 0; i <= n; i++) dp[i][0] = 1;
        if (n>=1 && k>=1) dp[1][1] = 1;
        
        for (int i = 2; i <= n; i++) {
            int upTo = Math.min(k, (i+1)/2);
            dp[i][1] = i;
            for (int j= 2; j <= upTo; j++) dp[i][j] = (dp[i-1][j] + dp[i-2][j-1]) % p;
        }
        
        int part1 = (k-1>=0 && n-3>=0) ? dp[n-3][k-1] : 0;
        int part2 = dp[n-1][k];
        int ans = (part1 + part2) % p;
        
        System.out.print(ans);
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