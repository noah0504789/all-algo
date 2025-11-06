import java.io.*;
import java.util.*;

public class Main {
    
    private static int n;
    private static long[][] dp; // 0-i죽음, 1-i안죽음
    
    public static void main(String... args) throws IOException {
        n = readInt();
        dp = new long[n+1][2];
        
        dp[1][0] = readInt();
        
        for (int i = 2; i <= n; i++) {
            int val = readInt();
                        
            dp[i][0] = Math.min(dp[i-1][0], dp[i-1][1]) + val;
            dp[i][1] = dp[i-1][0];
        }

        System.out.print(Math.min(dp[n][0], dp[n][1]));
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