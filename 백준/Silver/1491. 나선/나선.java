import java.io.*;
import java.util.*;

public class Main {
    
    private static int m, n, r, c, d, total;
    private static boolean[][] vis;
    private static int[][] dir = {
        {0,1},{-1,0},{0,-1},{1,0}
    };
    
    public static void main(String... args) throws IOException {        
        n = readInt();
        m = readInt();        
        
        vis = new boolean[m][n];
        
        r = m-1;
        c = 0;
        d = 0;
        
        total = m*n;
        
        for (int i = 1; i < total; i++) {
            vis[r][c] = true;
            
            int nr = r + dir[d][0], nc = c + dir[d][1];
            if (nr < 0 || nr >= m || nc < 0 || nc >= n || vis[nr][nc]) {
                d = (d+1)%4;
                nr = r + dir[d][0];
                nc = c + dir[d][1];
            }
            
            r = nr;
            c = nc;
        }

        System.out.print(c + " " + (m-1-r));
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