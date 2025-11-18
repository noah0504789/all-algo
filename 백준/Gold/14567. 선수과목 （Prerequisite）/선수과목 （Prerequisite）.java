import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int n, m, cur, prev;
    private static int[] dp;
    private static List<Integer>[] pre;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        n = readInt();
        m = readInt();
        
        pre = new ArrayList[n+1];
        for (int i = 0; i <= n; i++) pre[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            prev = readInt();
            cur = readInt();
            
            pre[cur].add(prev);
        }
        
        dp = new int[n+1];
        for (int i = 1; i <= n; i++) sb.append(dfs(i)+" ");

        System.out.print(sb);
    }
    
    private static int dfs(int cur) {
        if (dp[cur]>0) return dp[cur];
        
        int max = 0;
        for (int p : pre[cur]) max = Math.max(max, dfs(p));
        
        return dp[cur] = max+1;
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