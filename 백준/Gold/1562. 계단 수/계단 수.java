import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, p = 1_000_000_000;
    private static long ans;
    private static long[][][] dp; // 자릿수,끝자리수,상태(숫자등장)
    
    public static void main(String... args) throws IOException {
        n = readInt();
        if (n < 10) {
            System.out.print(0);
            return;
        }
        
        dp = new long[n+1][10][1<<10];
        for (int d = 1; d < 10; d++) dp[1][d][1<<d] = 1;
        for (int i = 2; i <= n; i++) {
            for (int d = 0; d < 10; d++) {
                for (int m = 0; m < (1<<10); m++) {
                    long v = 0;
                    if (d-1>=0) v += dp[i-1][d-1][m];
                    if (d+1<10) v += dp[i-1][d+1][m];
                    if (v == 0) continue;
                    
                    dp[i][d][m|(1<<d)] += v;
                    dp[i][d][m|(1<<d)] %= p;
                }
            }
        }
        
        for (int d = 0; d < 10; d++) {
            ans += dp[n][d][(1<<10)-1];
            ans %= p;
        }

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