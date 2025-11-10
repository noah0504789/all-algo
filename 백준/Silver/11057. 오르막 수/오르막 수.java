import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, p=10_007, ans;
    private static int[][] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        
        dp = new int[n+1][10];
        Arrays.fill(dp[1], 1);
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k <= j; k++) dp[i][j] += (dp[i-1][k] % p);
            }
        }
        
        for (int i = 0; i < 10; i++) ans += dp[n][i] % p;

        System.out.print(ans%p);
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