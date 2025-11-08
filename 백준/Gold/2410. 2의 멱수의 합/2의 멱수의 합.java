import java.io.*;
import java.util.*;

public class Main {
    
    private static int n_, p = 1_000_000_000;
    private static int[] dp;
    
    public static void main(String... args) throws IOException {
        n_ = readInt();
        
        // 2
        // 1+1
        // 2
        
        // 3 (2+1)
        // 1+1+1
        // 1+2
        
        // 4 (2+2)
        // 1+1+1+1
        // 1+1+2
        // 2+2
        // 4
        
        // 5 (4+1)
        // 1+1+1+1+1
        // 1+1+1+2
        // 1+2+2
        // 1+4
        
        // 6 (5+1)
        // 1+1+1+1+1+1
        // 1+1+1+1+2
        // 1+1+2+2
        // 1+1+4
        // 2+2+2
        // 2+4
        
        // 7 (6+1)
        // 1+1+1+1+1+1+1
        // 1+1+1+1+1+2
        // 1+1+1+2+2
        // 1+1+1+4
        // 1+2+2+2        
        // 1+2+4
        
        // 8
        // 1+1+1+1+1+1+1+1
        // 1+1+1+1+1+1+2
        // 1+1+1+1+2+2
        // 1+1+2+2+2
        // 1+1+1+1+4
        // 1+1+2+4
        // 2+2+4
        // 4+4
        // 8
        
        dp = new int[n_+1];
        dp[0] = 1;
        
        for (int i = 1; i <= n_; i++) {
            if (i%2==1) dp[i] = dp[i-1];
            else dp[i] = (dp[i-1] + dp[i/2]) % p;
        }
        
        System.out.print(dp[n_] % p);
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