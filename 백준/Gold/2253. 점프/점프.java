import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m, ans, INF = 1_000_000_000;
    private static boolean[] unavailable;
    private static boolean[][] dp;
    private static Queue<int[]> queue;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        unavailable = new boolean[n+1];
        for (int i = 0; i < m; i++) unavailable[readInt()] = true;
        
        if (n == 2) {
            System.out.print(1);
            return;
        }
        
        if (unavailable[2]) {
            System.out.print(-1);
            return;
        }
        
        dp = new boolean[n+1][151];
        dp[2][1] = true;        
        
        queue = new LinkedList<>();
        queue.offer(new int[]{2, 1, 1});
        
        ans = bfs();
        
        System.out.print(ans == INF ? -1 : ans);
    }
    
    private static int bfs() {
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int i = cur[0], j = cur[1], v = cur[2];
            
            for (int nv = v-1; nv <= v+1; nv++) {
                if (nv <= 0 || nv > 150) continue;
                int ni = i + nv;
                if (ni == n) return j+1;
                if (ni > n) continue;
                if (unavailable[ni]) continue;
                if (dp[ni][nv]) continue;
                
                dp[ni][nv] = true;
                queue.offer(new int[]{ni, j+1, nv});
            }
        }
        
        return INF;
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