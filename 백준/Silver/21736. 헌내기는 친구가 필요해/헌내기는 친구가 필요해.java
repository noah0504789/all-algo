import java.io.*;
import java.util.*;

public class Main {
    
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb;
    private static String[] input;
    private static int n, m, r, c;
    private static boolean flag;
    private static int[][] dir = {
        {0, 1}, {0, -1}, {1, 0}, {-1, 0}
    };
    private static char[][] arr;
    private static boolean[][] visited;
    private static Queue<int[]> queue;
    
    public static void main(String... args) throws IOException {        
        input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        
        arr = new char[n][m];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
            if (flag) continue;
            for (int j = 0; j < m; j++) {
                char ch = arr[i][j];
                
                if (ch == 'I') {r = i; c = j; flag = true; break;}
            }
        }
        
        visited = new boolean[n][m];
        queue = new LinkedList<>();
        
        int cnt = bfs(r, c);
        System.out.print(cnt == 0 ? "TT" : cnt);
    }

    private static int bfs(int r, int c) {
        int cnt = 0;
        queue.offer(new int[]{r, c});
        visited[r][c] = true;
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cr = cur[0], cc = cur[1];
            
            for (int[] d : dir) {
                int nr = cr + d[0], nc = cc + d[1];
                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                if (visited[nr][nc]) continue;
                if (arr[nr][nc] == 'X') continue;
                if (arr[nr][nc] == 'P') cnt++;
                
                visited[nr][nc] = true;
                queue.offer(new int[]{nr, nc});
            }
        }
        return cnt;
    }
}