import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, t, sum, max, INF = Integer.MAX_VALUE;
    private static int[] d, m, dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        t = readInt();
        
        d = new int[n];        
        m = new int[n];
        
        for (int i = 0; i < n; i++) {
            d[i] = readInt();
            m[i] = readInt();
            sum += m[i];
        }
        
        dp = new int[t+1];
        //Arrays.fill(dp, INF);
        //dp[0] = 0;
        
        for (int i = 0; i < n; i++) {
            int d_ = d[i], m_ = m[i];
            for (int j = t; j >= d_; j--) {
                dp[j] = Math.max(dp[j], dp[j-d_]+m_);
            }
        }
        
        for (int i = 0; i <= t; i++) max = Math.max(max, dp[i]);
        
        System.out.print(sum - max);
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