import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, INF = Integer.MAX_VALUE;
    private static int[] dp; 
    
    public static void main(String... args) throws IOException {
        n = readInt();
        
        dp = new int[n+1];
        Arrays.fill(dp, INF);
        
        System.out.print(dfs(n));
    }
    
    private static int dfs(int v) {
        if (v == 1) return 0;
        if (dp[v] != INF) return dp[v];
        
        int min = INF;
        if (v % 3 == 0) min = Math.min(min, 1+dfs(v/3));
        if (v % 2 == 0) min = Math.min(min, 1+dfs(v/2));
        min = Math.min(min, 1+dfs(v-1));
        
        return dp[v] = min;
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