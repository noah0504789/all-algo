import java.io.*;
import java.util.*;

public class Main {
        
    private static StringBuilder sb = new StringBuilder();
    private static int tc, maxN;
    private static int[] tArr;
    private static boolean[] vis;
    private static int[][] dp;
    
    public static void main(String... args) throws IOException {
        tc = readInt();
        tArr = new int[tc];
        for (int i = 0; i < tc; i++) {
            tArr[i] = readInt();
            maxN = Math.max(maxN, tArr[i]);
        }                
        
        dp = new int[Math.max(maxN, 1)+1][2];
        dp[0][0] = dp[1][1] = 1;
        
        vis = new boolean[Math.max(maxN, 1)+1];
        vis[0] = vis[1] = true;
        
        fib(maxN);
        for (int t : tArr) sb.append(dp[t][0] + " " + dp[t][1]).append("\n");

        System.out.print(sb);
    }
    
    private static int[] fib(int n) {
        if (n < 0) return new int[]{0, 0};
        if (vis[n]) return dp[n];
        vis[n] = true;
        
        return dp[n] = add(fib(n-1), fib(n-2));
    }
    
    private static int[] add(int[] a, int[] b) {        
        return new int[]{a[0] + b[0], a[1] + b[1]};
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