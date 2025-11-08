import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, k;
    private static int[] arr;
    private static long[] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        k = readInt();
        
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = readInt();
        
        dp = new long[n];
        
        solve(0, 0, 0L);

        System.out.print(dp[n-1]);
    }
    
    private static void solve(int l, int r, long score) {
        if (r == n) return;
        
        solve(l, r + 1, score);
        
        if (score+arr[r] >= k) {
            dp[r] = Math.max(dp[r], (l>=1 ? dp[l-1] : 0) + score+arr[r] - k);
            score = 0;
            l = ++r;
        } else {
            score += arr[r];
            r++;
        }
                
        solve(l, r, score);
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