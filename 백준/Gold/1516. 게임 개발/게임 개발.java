import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int n, p;
    private static int[] time, dp;
    private static List<Integer>[] prev;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        n = readInt();
        prev = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) prev[i] = new ArrayList<>();
        
        time = new int[n+1];
        for (int i = 1; i <= n; i++) {
            time[i] = readInt();
            while ((p = readInt()) != -1) prev[i].add(p);
        }
        
        dp = new int[n+1];
        for (int i = 1; i <= n; i++) {
            //int max = 0;
            //for (int p_ : prev[i]) max = Math.max(max, dp[p_]);
            //dp[i] = max + time[i];            
            
            sb.append(dfs(i)).append("\n");
        }
        
        System.out.print(sb);
    }
    
    private static int dfs(int x) {
        if (dp[x]>0) return dp[x];
        int max = 0;
        for (int p_ : prev[x]) {
            max = Math.max(max, dfs(p_));
        }
        
        return dp[x] = max+time[x];
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