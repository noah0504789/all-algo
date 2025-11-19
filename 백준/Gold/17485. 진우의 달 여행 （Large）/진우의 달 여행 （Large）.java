import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m, min, INF = Integer.MAX_VALUE;
    private static int[][] arr, dir = {
        {1, -1}, {1, 0}, {1, 1}
    };
    private static int[][][] dp;
    private static Queue<int[]> queue;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        
        arr = new int[n][m];
        dp = new int[n][m][3];
        queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = readInt();
                Arrays.fill(dp[i][j], INF);                
            }
        }        

        for (int j = 0; j < m; j++) {
            for (int d = 0; d < dir.length; d++) {
                dp[0][j][d] = arr[0][j];
                queue.offer(new int[]{0, j, d});
            }
        }
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cr = cur[0], cc = cur[1], cd = cur[2];
            int cCost = dp[cr][cc][cd];
            if (cCost == INF) continue;
            
            for (int i = 0; i < dir.length; i++) {
                if (cd == i) continue;
                int[] d = dir[i];
                int nr = cr + d[0], nc = cc + d[1];
                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                if (dp[nr][nc][i] > cCost + arr[nr][nc]) {
                    dp[nr][nc][i] = cCost + arr[nr][nc];
                    queue.offer(new int[]{nr, nc, i});
                }                
            }
        }
            
        min = INF;
        for (int j = 0; j < m; j++) {
            min = Math.min(min, dp[n-1][j][0]);
            min = Math.min(min, dp[n-1][j][1]);
            min = Math.min(min, dp[n-1][j][2]);
        }

        System.out.print(min);
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