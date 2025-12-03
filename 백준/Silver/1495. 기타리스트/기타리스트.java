import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, s, m, ans;
    private static int[] v;
    private static boolean[][] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        s = readInt();
        m = readInt();
        
        v = new int[n];
        for (int i = 0; i < n; i++) v[i] = readInt();
        
        dp = new boolean[n+1][m+1];
        dp[0][s] = true;
        
        for (int i = 0; i < n; i++) {
            for (int vol = 0; vol <= m; vol++) {
                if (!dp[i][vol]) continue;
                
                int down = vol - v[i], up = vol + v[i];
                if (down >= 0) dp[i+1][down] = true;
                if (up <= m) dp[i+1][up] = true;
            }
        }
        ans = -1;
        for (int vol = m; vol >= 0; vol--) {
            if (dp[n][vol]) {
                ans = vol;
                break;
            }
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