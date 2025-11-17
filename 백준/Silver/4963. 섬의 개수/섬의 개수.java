import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int n, m, cnt;
    private static int[][] arr, dir = {
        {0, 1}, {0, -1}, {1, 0}, {-1, 0},
        {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
    };
    private static boolean[][] visited;
    private static Queue<int[]> queue;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        queue = new LinkedList<>();
        
        while (true) {
            m = readInt();
            n = readInt();
            
            if (n == 0 && m == 0) break;
            
            arr = new int[n][m];
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < m; c++) arr[r][c] = readInt();
            }
            
            visited = new boolean[n][m];
            queue.clear();
            cnt = 0;
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < m; c++) {
                    if (visited[r][c] || arr[r][c] == 0) continue;
                    
                    bfs(r, c);
                    cnt++;
                }
            }
            
            sb.append(cnt+"\n");
        }

        System.out.print(sb);
    }
    
    private static void bfs(int r, int c) {
        queue.offer(new int[]{r, c});
        visited[r][c] = true;
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cr = cur[0], cc = cur[1];
            
            for (int[] d : dir) {
                int nr = cr+d[0], nc = cc+d[1];
                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                if (visited[nr][nc]) continue;
                if (arr[nr][nc] == 0) continue;
                
                visited[nr][nc] = true;
                queue.offer(new int[]{nr, nc});
            }
        }
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