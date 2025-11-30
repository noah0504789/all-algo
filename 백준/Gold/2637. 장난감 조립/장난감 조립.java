import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb = new StringBuilder();
    private static int n, m, bs, be, ms, me;
    private static List<Integer>[] list;
    private static int[][] table, dp;
    private static int[] ans;
    private static boolean[] dp_vis;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        
        list = new ArrayList[n+1];
        for (int i = 0; i <= n; i++) list[i] = new ArrayList<>();
        
        table = new int[n+1][n+1];
        for (int i = 0; i < m; i++) {
            int mid = readInt();
            int basic = readInt();
            
            list[mid].add(basic);
            table[mid][basic] = readInt();
        }
        
        dp = new int[n+1][n+1];
        dp_vis = new boolean[n+1];
                
        ans = dfs(n);
        for (int i = 1; i < n; i++) {
            if (list[i].isEmpty()) sb.append(i + " " + ans[i]).append("\n");
        }

        System.out.print(sb);
    }
    
    private static int[] dfs(int i) {
        if (dp_vis[i]) return dp[i];
        dp_vis[i] = true;
        
        if (list[i].isEmpty()) {
            dp[i][i] = 1;
            return dp[i];
        }
        
        int[] res = new int[n+1];
        
        for (int child : list[i]) {
            int[] sub = dfs(child);
            int needCnt = table[i][child];
            for (int b = 1; b < n; b++) res[b] += needCnt * sub[b];
        }        
        
        return dp[i] = res;
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