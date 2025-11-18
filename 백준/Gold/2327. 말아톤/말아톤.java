import java.io.*;
import java.util.*;

public class Main {
    
    private static int h, n, min, maxS, maxH, INF = Integer.MAX_VALUE;
    private static int[] height, speed, dp;
    
    public static void main(String... args) throws IOException {
        h = readInt();
        n = readInt();
        
        height = new int[n];
        speed = new int[n];
        for (int i = 0; i < n; i++) {
            height[i] = readInt();            
            speed[i] = readInt();
        }
        
        dp = new int[h+1];
        Arrays.fill(dp, -1);
        dp[0] = INF;
        
        for (int i = 0; i < n; i++) {
            int hi = height[i], si = speed[i];
            
            for (int j = h-hi; j>=0; j--) {
                if (dp[j] == -1) continue;
                
                int nh = j + hi;
                if (nh > h) continue;
                
                int cand = Math.min(dp[j], si);
                dp[nh] = Math.max(dp[nh], cand);
            }
        }

        System.out.print(dp[h]);
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