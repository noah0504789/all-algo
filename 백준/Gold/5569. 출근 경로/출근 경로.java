import java.io.*;
import java.util.*;

public class Main {
    
    private static int w, h, ans, p = 100_000;
    private static int[][] dir = {
        {0, 1}, {1, 0}
    };
    private static int[][][][] dp; // r, c, prevD, turned
    
    public static void main(String... args) throws IOException {
        w = readInt();
        h = readInt();
        
        dp = new int[h][w][2][2];
        
        if (w > 1) ans += dfs(0, 1, 0, 0);
        if (h > 1) ans += dfs(1, 0, 1, 0);

        System.out.print(ans % p);
    }
    
    private static int dfs(int r, int c, int d, int turned) {        
        if (r == h-1 && c == w-1) return 1;
        if (r >= h || c >= w) return 0;
        if (dp[r][c][d][turned] > 0) return dp[r][c][d][turned];
        
        int sum = 0;
        sum += dfs(r+dir[d][0], c+dir[d][1], d, 0);
        if (turned == 0) sum += dfs(r+dir[d^1][0], c+dir[d^1][1], d^1, 1);
        
        return dp[r][c][d][turned] = sum % p;
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