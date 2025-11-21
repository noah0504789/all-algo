import java.io.*;
import java.util.*;

public class Main {
    
    private static int h, n;
    private static int[] speed, height, dp;
    
    public static void main(String... args) throws IOException {
        h = readInt();
        n = readInt();
        speed = new int[n];
        height = new int[n];
        for (int i =0; i < n; i++) {
            height[i] = readInt();
            speed[i] = readInt();
        }
        
        dp = new int[h+1];
        Arrays.fill(dp, -1);
        dp[0] = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int h_ = height[i], s_ = speed[i];
            
            for (int j = h-h_; j >= 0; j--) {
                int cand = Math.min(s_, dp[j]);
                dp[j+h_] = Math.max(dp[j+h_], cand);
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