import java.io.*;
import java.util.*;

public class Main {
    
    private static int w, h, p = 100_000;
    private static long ans;
    private static int[][] DIR = {
        {0, 1}, {1, 0}
    };
    private static long[][][][] dp;
    
    public static void main(String... args) throws IOException {
        w = readInt();
        h = readInt();
        
        dp = new long[h+1][w+1][2][2];
        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= w; j++) {
                //Arrays.fill(dp[i][j][0], -1);
                //Arrays.fill(dp[i][j][1], -1);
            }
        }
        
        if (w > 1) ans += dfs(1, 2, 0, 0);
        if (h > 1) ans += dfs(2, 1, 1, 0);

        System.out.print(ans % p);
    }
    
    private static long dfs(int r, int c, int dir, int turned) {
        if (r == h && c == w) return 1;
        if (r >h||c>w) return 0;
        if (dp[r][c][dir][turned]>0) return dp[r][c][dir][turned];
        
        long v = dfs(r+DIR[dir][0], c+DIR[dir][1], dir, 0) % p;
        if (turned==0) v += dfs(r+DIR[dir^1][0], c+DIR[dir^1][1], dir^1, 1) % p;
        
        return dp[r][c][dir][turned] = v % p;
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