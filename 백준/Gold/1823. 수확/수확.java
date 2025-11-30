import java.io.*;
import java.util.*;

public class Main {
    
    private static int n;
    private static long[][] dp; // l~r구간이 남을 때 최대 이익
    private static int[] arr;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = readInt();               
        
        dp = new long[n][n];
        for (int len = 1; len <= n; len++) {
            for (int l = 0; l+len-1<n; l++) {
                int r = l+len-1;
                int k = n-len+1;
                
                if (l == r) {
                    dp[l][r] = arr[l] * k;
                } else {
                    long left = arr[l]*k + dp[l+1][r];
                    long right = arr[r]*k + dp[l][r-1];
                    dp[l][r] = Math.max(left, right);
                }
            }
        }
        
        System.out.print(dp[0][n-1]);
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