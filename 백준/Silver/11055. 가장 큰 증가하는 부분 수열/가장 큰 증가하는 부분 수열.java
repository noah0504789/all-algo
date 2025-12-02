import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, ans;
    private static int[] arr, dp; 
    
    public static void main(String... args) throws IOException {
        n = readInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = readInt();
        
        dp = new int[n];
        dp[0] = ans = arr[0];
        for (int i = 1; i < n; i++) {
            for (int j = i-1; j >= 0; j--) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);                    
                } else {
                    dp[i] = Math.max(dp[i], arr[i]);
                }
            }
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