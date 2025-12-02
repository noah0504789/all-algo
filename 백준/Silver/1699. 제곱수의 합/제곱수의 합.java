import java.io.*;
import java.util.*;

public class Main {
    
    private static int n;
    private static int[] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        dp = new int[n+1];        
        
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
            
            for (int j = 1; j * j <= i; j++) dp[i] = Math.min(dp[i], dp[i-j*j] + 1);
        }
        
        System.out.print(dp[n]);
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