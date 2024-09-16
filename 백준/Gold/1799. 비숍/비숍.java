import java.io.*;
import java.util.*;

public class Main {    
    private static final List<Integer> list = new ArrayList<>();;
    private static final int[][] DIRS = {{1, -1}, {-1, 1}, {1, 1}, {-1, -1}};
    private static int[][] board;
    private static boolean[][] isBlack, visited;    
    private static int n, ansB, ansW;
    
    public static void main(String... args) throws IOException {
        ansB = ansW = 0;

        n = readInt();
        
        board = new int[n][n];
        isBlack = new boolean[n][n];
        visited = new boolean[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = readInt();    
                isBlack[i][j] = (i%2==0 && j%2==0) || (i%2==1 && j%2==1);                
            }
        }
        
        dfs(0, true, 0);
        dfs(0, false, 0);
        
        System.out.print(ansB + ansW);
    }    
    
    private static void dfs(int start, boolean black, int cnt) {
        for (int i = start; i < n*n; i++) {
            int r = i / n, c = i % n;
            
            if (board[r][c] == 0 || isBlack[r][c] != black || !check(r, c)) continue;
            
            visited[r][c] = true;
            dfs(i+1, black, cnt+1);
            visited[r][c] = false;
        }
        
        if (black) {
            ansB = Math.max(ansB, cnt);
        } else {
            ansW = Math.max(ansW, cnt);
        }
    }
    
    private static boolean check(int r, int c) {
        for (int[] dir : DIRS) {
            int nr = r, nc = c;
            while (true) {
                if (nr < 0 || nr >= n || nc < 0 || nc >= n) break;
                if (visited[nr][nc]) return false;
                
                nr+=dir[0];
                nc+=dir[1];
            }
        }
        
        return true;
    }
    
    private static int readInt() throws IOException {
        int result = 0, c = System.in.read();
        
        while (c <= ' ') c = System.in.read();
        
        while (c >= '0' && c <= '9') {
            result *= 10;
            result += (c - '0');
            c = System.in.read();
        }
        
        return result;
    }
}