import java.io.*;
import java.util.*;

public class Main {
        
    private static int n;
    private static int[] arr;
    private static long[][] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        arr = new int[n+1];
        for (int i = 1; i <= n; i++) arr[i] = readInt();
        
        if (n == 1) {
            System.out.print(arr[1]);
            return;
        }
        
        if (n == 2) {
            System.out.print(arr[1] + arr[2]);
            return;
        }
        
        dp = new long[n+1][3];
                
        System.out.print(Math.max(dfs(1, 1), dfs(2, 1)));
    }
    
    private static long dfs(int i, int con) {
        if (i == n) return arr[i];
        if (dp[i][con] != 0) return dp[i][con];
        
        long max = 0;
        if (i+2 <= n) max = Math.max(max, arr[i] + dfs(i+2, 1));
        if (i+1 <= n && con <= 1) max = Math.max(max, arr[i] + dfs(i+1, con+1));
        
        return dp[i][con] = max;
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