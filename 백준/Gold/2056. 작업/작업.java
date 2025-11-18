import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, cnt, p, ans;
    private static int[] time, dp;
    private static List<Integer>[] prev;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        
        time = new int[n+1];
        prev = new ArrayList[n+1];

        for (int i = 1; i <= n; i++) {            
            time[i] = readInt();
            prev[i] = new ArrayList<>();
            cnt = readInt();
            for (int j = 0; j < cnt; j++) prev[i].add(readInt());
        }
        
        dp = new int[n+1];
        for (int i = 1; i<=n; i++) {
            int best = 0;
            for (int p_ : prev[i]) best = Math.max(best, dp[p_]);
            dp[i] = best + time[i];
            ans = Math.max(ans, dp[i]);
        }

        System.out.print(ans);
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