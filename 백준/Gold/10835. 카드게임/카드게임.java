import java.io.*;
import java.util.*;

public class Main {
    
    private static int n;
    private static int[] a, b;
    private static long[][] dp; // 남은 l갯수, 남은 r갯수
    
    public static void main(String... args) throws IOException {
        n = readInt();
        
        a = new int[n];
        for (int i = 0; i < n; i++) a[i] = readInt();
        
        b = new int[n];
        for (int i = 0; i < n; i++) b[i] = readInt();
        
        dp = new long[n+1][n+1];
        for (int i = n-1; i >= 0; i--) {
            for (int j = n-1; j >= 0; j--) {
                // 왼
                dp[i][j] = Math.max(dp[i][j], dp[i+1][j]);
                
                // 왼+오
                dp[i][j] = Math.max(dp[i][j], dp[i+1][j+1]);
                
                // 오
                if (a[i] > b[j]) dp[i][j] = Math.max(dp[i][j], dp[i][j+1] + b[j]);
            }
        }

        System.out.print(dp[0][0]);
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