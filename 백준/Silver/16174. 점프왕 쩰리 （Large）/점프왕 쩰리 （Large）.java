import java.io.*;
import java.util.*;

public class Main {
    
    private static String success = "HaruHaru", fail = "Hing";
    private static int n;
    private static int[][] arr, dir = {{0, 1}, {1, 0}};
    private static Boolean[][] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        arr = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) arr[i][j] = readInt();
        }
        
        dp = new Boolean[n+1][n+1];

        System.out.print(dfs(1, 1) ? success : fail);
    }
    
    private static boolean dfs(int r, int c) {
        if (r == n && c == n) return true;
        if (dp[r][c] != null) return dp[r][c];
        
        int w = arr[r][c];
        if (w == 0) return dp[r][c] = false;
        
        for (int[] d : dir) {
            int nr = r + d[0]*w, nc = c + d[1]*w;
            if (nr < 0 || nr > n || nc < 0 || nc > n) continue;
            if (dfs(nr, nc)) return dp[r][c] = true;
        }
        
        return dp[r][c] = false;
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