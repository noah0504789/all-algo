import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, p = 1_000_000_007;
    private static long[][] dp; // n, 각 자리수를 3으로 나눈 나머지
    
    public static void main(String... args) throws IOException {
        n = readInt();
        if (n == 1) {
            System.out.print(0);
            return;
        }
        
        dp = new long[n+1][3];        
        dp[2][0] = dp[2][1] = 1;

        for (int i = 3; i <= n; i++) {
            dp[i][0] = (dp[i-1][1] + dp[i-1][2]) % p; // 맨 앞자리를 1 or 5로 둘 때 
            dp[i][1] = (dp[i-1][0] + dp[i-1][2]) % p;
            dp[i][2] = (dp[i-1][0] + dp[i-1][1]) % p;
        }

        System.out.print(dp[n][0]);
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