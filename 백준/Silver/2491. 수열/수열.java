import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, len, ans;
    private static int[] arr, dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = readInt();
        
        ans = 1;
        dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            if (arr[i] >= arr[i-1]) {
                dp[i] = dp[i-1] + 1;                
            }
            else dp[i] = 1;
            ans = Math.max(ans, dp[i]);
        }
        
        dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            if (arr[i] <= arr[i-1]) {
                dp[i] = dp[i-1] + 1;
                
            }
            else dp[i] = 1;            
            ans = Math.max(ans, dp[i]);
        }
        
        System.out.print(ans);
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