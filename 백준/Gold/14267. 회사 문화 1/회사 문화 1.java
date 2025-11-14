import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int n, m;
    private static int[] parent, dp;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        n = readInt();
        m = readInt();
        
        parent = new int[n+1];
        for (int i = 1; i <= n; i++) parent[i] = readInt();
        
        dp = new int[n+1];
        for (int i = 0; i < m; i++) {
            int a = readInt();
            int w = readInt();
            
            dp[a] += w;
        }
        
        for (int i = 2; i <= n; i++) {
            int p = parent[i];
            if (p!=-1) dp[i] += dp[p];
        }
        
        for (int i = 1; i <= n; i++) sb.append(dp[i]+" ");

        System.out.print(sb);
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