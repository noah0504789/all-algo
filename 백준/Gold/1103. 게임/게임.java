import java.io.*;
import java.util.*;

public class Main {
    
    private static BufferedReader br;
    private static String[] input;
    private static int n, m;
    private static boolean inf;
    private static boolean[][] inStack;
    private static char[][] arr;
    private static int[][] dp; // dest, prev
    private static int[][] dir = {
        {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };
    
    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        
        input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        
        arr = new char[n][m];
        for (int i = 0; i < n; i++) arr[i] = br.readLine().toCharArray();
        
        dp = new int[n][m];
        //for (int[] row : dp) Arrays.fill(row, -1);
        inStack = new boolean[n][m];
        
        int ans = dfs(0, 0);
        
        System.out.print(inf ? -1 : ans);
    }
    
    private static int dfs(int r, int c) {
        if (r < 0 || r >= n || c < 0 || c >= m) return 0;
        if (arr[r][c] == 'H') return 0;
        if (inf) return 0;
        if (dp[r][c] > 0) return dp[r][c];
        if (inStack[r][c]) {
            inf = true;
            return 0;
        }
        
        inStack[r][c] = true;
                
        int w = arr[r][c] - '0';
        int best = 0;
        for (int[] d : dir) {
            int nr = r + d[0] * w, nc = c + d[1] * w;
                        
            best = Math.max(best, 1 + dfs(nr, nc));
            if (inf) break;            
        }    
        
        inStack[r][c] = true;
        return dp[r][c] = best;
    }
}