import java.io.*;
import java.util.*;

public class Main {
    
    private static int n;
    private static long max;
    private static int[][] arr, dir = {
        {0, 1},{0, -1},{1, 0},{-1, 0}
    };
    private static int[][] dp; // 좌표(r, c)
    
    public static void main(String... args) throws IOException {
        n = readInt();
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) arr[i][j] = readInt();
        }
        
        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, dfs(i, j));
            }
        }

        System.out.print(max);
    }
    
    private static int dfs(int r, int c) {
        if (dp[r][c] > 0) return dp[r][c];
        int cv = arr[r][c];
        
        int m = 1;
        for (int[] d : dir) {
            int nr = r + d[0], nc = c + d[1];
            if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
            if (cv >= arr[nr][nc]) continue;
            
            m = Math.max(m, 1 + dfs(nr, nc));
        }
        
        return dp[r][c] = m;
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