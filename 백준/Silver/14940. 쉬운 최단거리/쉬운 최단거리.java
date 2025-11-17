import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int n, m, sr, sc;
    private static int[][] arr, dp, dir = {
        {0, 1}, {0, -1}, {1, 0}, {-1, 0}
    };
    private static Queue<int[]> queue;
    private static BitSet visited;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        n = readInt();
        m = readInt();
        arr = new int[n+1][m+1];
        dp = new int[n+1][m+1];
        
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dp[i], -1);
            for (int j = 1; j <= m; j++) {
                arr[i][j] = readInt();
                
                if (arr[i][j] == 0) dp[i][j] = 0;
                else if (arr[i][j] == 2) {sr = i; sc = j; dp[i][j] = 0;}                
            }
        }
                       
        queue = new LinkedList<>();
        visited = new BitSet();
        
        bfs(sr, sc);
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) sb.append(dp[i][j]+" ");
            sb.append("\n");
        }        

        System.out.print(sb);
    }
    
    private static void bfs(int r, int c) {
        queue.offer(new int[]{r, c, 0});
        visited.set(key(r, c));
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cr = cur[0], cc = cur[1], cv = cur[2];
            
            for (int[] d : dir) {
                int nr = cr + d[0], nc = cc + d[1];
                
                if (nr < 1 || nr > n || nc < 1 || nc > m) continue;
                if (arr[nr][nc] == 0) continue;
                if (visited.get(key(nr, nc))) continue;
                
                dp[nr][nc] = cv + 1;
                visited.set(key(nr, nc));
                queue.offer(new int[]{nr, nc, dp[nr][nc]});                
            }
        }
    }
    
    private static int key(int r, int c) {
        return r*m+c;
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