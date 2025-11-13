import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int n, m, a, b;
    private static int[] dp;
    private static List<Integer>[] pre;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        n = readInt();
        m = readInt();

        pre = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) pre[i] = new ArrayList<>();
        
        for (int i = 0; i < m; i++) {
            a = readInt();
            b = readInt();
            
            pre[b].add(a);
        }
        
        dp = new int[n+1];
        Arrays.fill(dp, 1);
        dp[0] = 0;
        
        for (int i = 1; i <= n; i++) {
            for (int p : pre[i]) {
                dp[i] = Math.max(dp[i], dp[p]+1);
            }
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