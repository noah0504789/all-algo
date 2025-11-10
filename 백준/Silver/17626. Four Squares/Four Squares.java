import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, lim, INF = Integer.MAX_VALUE;
    private static int[] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        lim = (int)Math.sqrt(n)+1;        
        
        dp = new int[n+1];
        
        Arrays.fill(dp, INF/2);
        dp[0] = 0;
        for (int i = 1; i<=n;i++) {
            for (int j = 1; j*j<=i; j++) dp[i] = Math.min(dp[i], dp[i-j*j]+1);
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