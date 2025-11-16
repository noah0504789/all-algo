import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int tc, maxL, p = 1_000_000_007;    
    private static int[] arr;
    private static long[] dp;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        tc = readInt();
        arr = new int[tc];
        for (int i = 0; i < tc; i++) {
            arr[i] = readInt();
            maxL = Math.max(maxL, arr[i]);
        }
        
        dp = new long[Math.max(0, maxL)+1];
        dp[0] = 1;
        
        for (int i = 1; i <= maxL; i++) {
            long sum = 0;
            
            for (int j = 0; j < i; j++) {
                sum = (sum + dp[j] * dp[i-1-j]) % p;
            }
            
            dp[i] = sum;
        }
        
        for (int t : arr) {
            long ans = 0;
            if ((t&1) != 1) ans = dp[t/2];
            
            sb.append(ans+"\n");
        }

        System.out.print(sb);
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