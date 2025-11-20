import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int n, p;
    private static int[] time;
    private static List<Integer>[] pre;
    private static long[] dp;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        n = readInt();
        time = new int[n+1];
        pre = new ArrayList[n+1];        
        for (int i = 1; i <= n; i++) {            
            time[i] = readInt();
            pre[i] = new ArrayList<>();
            while ((p=readInt())!= -1) pre[i].add(p);            
        }
        
        dp = new long[n+1];
        for (int i = 1; i <= n; i++) sb.append(dfs(i)+"\n");

        System.out.print(sb);
    }
    
    private static long dfs(int x) {
        if (dp[x]>0) return dp[x];
        
        long max = 0;
        for (int p_ : pre[x]) max = Math.max(max, dfs(p_));
        
        return dp[x] = max + time[x];
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