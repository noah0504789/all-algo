import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, min, max;
    private static int[] arr, dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        
        arr = new int[n];        
        for (int i = 0; i < n; i++) arr[i] = readInt();
        
        dp = new int[n];
        min = Integer.MAX_VALUE;
        max = 0;
        for (int i = 0; i < n; i++) {
            min = arr[i];
            max = arr[i];
            for (int j = i; j >= 0; j--) {
                min = Math.min(min, arr[j]);
                max = Math.max(max, arr[j]);
                dp[i] = Math.max(dp[i], (j-1>=0 ? dp[j-1] : 0) +max-min);
            }
        }

        System.out.print(dp[n-1]);
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