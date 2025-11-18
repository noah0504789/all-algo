import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, max;
    private static int[][] arr, dp, dir = {
        {0, 1}, {0, -1}, {1, 0}, {-1, 0}
    };
    
    public static void main(String... args) throws IOException {
        n = readInt();
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) arr[i][j] = readInt();
        }
                
        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) max = Math.max(max, dfs(i, j));
        }

        System.out.print(max);
    }
    
    private static int dfs(int r, int c) {
        if (dp[r][c] > 0) return dp[r][c];
        int max = 1;
        
        for (int[] d : dir) {
            int nr = r + d[0], nc = c + d[1];
            if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
            if (arr[r][c] >= arr[nr][nc]) continue;

            max = Math.max(max, 1+dfs(nr, nc));
        }
        
        return dp[r][c] = max;
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