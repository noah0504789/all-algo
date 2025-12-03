import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb = new StringBuilder();
    private static int n, m;
    private static int[][] ans, arr, sinkR, sinkC, dir = {
        {0, 1}, {0, -1}, {1, 0}, {-1, 0},
        {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
    };    
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        
        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) arr[i][j] = readInt();
        }
            
        sinkR = new int[n][m];
        sinkC = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(sinkR[i], -1);
            Arrays.fill(sinkC[i], -1);
        }    
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) dfs(i, j);
        }
        
        ans = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int sr = sinkR[i][j];
                int sc = sinkC[i][j];
                ans[sr][sc]++;
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) sb.append(ans[i][j]+" ");
            sb.append("\n");
        }
        
        System.out.print(sb);
    }
    
    private static void dfs(int r, int c) {
        if (sinkR[r][c] != -1) return;
        
        int curVal = arr[r][c];
        int minVal = curVal;
        int nr = r, nc = c;
        
        for (int[] d : dir) {
            int tr = r + d[0], tc = c + d[1];
            if (tr < 0 || tr >= n || tc < 0 || tc >= m) continue;
            if (arr[tr][tc] >= minVal) continue;
            
            minVal = arr[tr][tc];
            nr = tr;
            nc = tc;
        }
        
        if (minVal == curVal) {
            sinkR[r][c] = r;
            sinkC[r][c] = c;            
        } else {
            dfs(nr, nc);
            sinkR[r][c] = sinkR[nr][nc];
            sinkC[r][c] = sinkC[nr][nc];            
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