import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int tc, n;
    private static int[] arr;
    private static long[] ps;
    private static long[][] dp;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        tc = readInt();
        while (tc-->0) {
            n = readInt();
            arr = new int[n];
            ps = new long[n];
            for (int i = 0; i < n; i++) {
                ps[i] = arr[i] = readInt();
                if (i > 0) ps[i] += ps[i-1];
            }
            
            dp = new long[n][n];
            
            sb.append(solve(0, n-1)+"\n");
        }

        System.out.print(sb);
    }
    
    private static long solve(int l, int r) {
        if (l > r) return 0L;
        if (l == r) return arr[l];
        if (dp[l][r] > 0) return dp[l][r];
                                
        return dp[l][r] = sum(l, r) - Math.min(solve(l+1, r), solve(l, r-1));
    }
    
    private static long sum(int l, int r) {
        if (l > r) return 0;
        return ps[r] - (l-1>=0 ? ps[l-1] : 0);
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