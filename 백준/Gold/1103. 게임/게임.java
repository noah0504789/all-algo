import java.io.*;
import java.util.*;

public class Main {
    
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static String[] input;
    private static int n, m;
    private static char[][] arr;
    private static boolean[][] vis;
    private static int[][] dp, dir = {
        {0, 1}, {0, -1}, {1, 0}, {-1, 0}
    };
    
    public static void main(String... args) throws IOException {
        input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        
        arr = new char[n][m];
        for (int i = 0; i < n; i++) arr[i] = br.readLine().toCharArray();
        
        dp = new int[n][m];        
        vis = new boolean[n][m];
        
        System.out.print(dfs(0, 0));
    }
    
    private static int dfs(int r, int c) {
        if (r < 0 || r >= n || c < 0 || c >= m) return 0;
        if (arr[r][c] == 'H') return 0;
        if (dp[r][c] > 0) return dp[r][c];
        if (vis[r][c]) {
            System.out.print(-1);
            System.exit(0);
        }
        
        vis[r][c] = true;
        int max = 0, w = arr[r][c]-'0';
        for (int[] d : dir) {
            int nr = r + d[0]*w, nc = c + d[1]*w;
            max = Math.max(max, dfs(nr, nc));
        }
        
        return dp[r][c] = max+1;
    }
}