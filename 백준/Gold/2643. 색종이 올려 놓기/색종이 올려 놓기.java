import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, a, b;
    private static long ans;
    private static Paper[] papers;
    private static long[] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();

        papers = new Paper[n];
        for (int i = 0; i < n; i++) {
            a = readInt();
            b = readInt();
            
            papers[i] = new Paper(Math.max(a, b), Math.min(a, b));
        }
        
        Arrays.sort(papers);
        
        dp = new long[n];
        for (int i = 0; i < n; i++) ans = Math.max(ans, dfs(i));

        System.out.print(ans);
    }
    
    private static long dfs(int s) {
        //if (s >= n) return 0;
        if (dp[s]>0) return dp[s];
        
        long max = 0;
        for (int i = 0; i < n; i++) {
            if (i == s) continue;
            if (!can(papers[s], papers[i])) continue;
            max = Math.max(max, dfs(i));
        }
        
        return dp[s] = max+1;
    }
    
    private static boolean can(Paper down, Paper up) {
        if (down.w < up.w) return false;
        if (down.h < up.h) return false;
        
        return true;
    }
    
    static class Paper implements Comparable<Paper> {
        int w, h;
        Paper(int w, int h) {
            this.w=w;
            this.h=h;
        }
        
        public int compareTo(Paper o) {
            if (this.w==o.w) return o.h-this.h;
            
            return o.w-this.w;
        }
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