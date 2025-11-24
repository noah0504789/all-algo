import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m, INF = Integer.MAX_VALUE;
    private static int[] names, ps;
    private static long[] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        names = new int[n+1];
        ps = new int[n+1];
        for (int i = 1; i <= n; i++) {
            ps[i] = names[i] = readInt();
            ps[i] += ps[i-1];
        }
        
        dp = new long[n+2];        
        Arrays.fill(dp, -1);

        System.out.print(dfs(1));
    }
    
    private static long dfs(int i) {
        if (dp[i] != -1) return dp[i];
        
        long min = INF;
        for (int ni = i; ni <= n; ni++) {
            int sum = ps[ni] - ps[i-1];
            int cnt = ni-i+1;
            int len = sum+cnt-1;
            int blank = m - len;
            
            if (len > m) break;
            if (ni == n) {
                min = 0;
                break;
            }
            
            min = Math.min(min, blank*blank + dfs(ni+1));
        }
        
        return dp[i] = min;
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