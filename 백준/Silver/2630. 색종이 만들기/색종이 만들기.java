import java.io.*;
import java.util.*;

public class Main {
    private static int[][] board;
    private static int n, val, oneCnt, zeroCnt;

    public static void main(String... args) throws IOException {
        n = readInt();  
        
        board = new int[n][n];
        
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) board[r][c] = readInt();
        }
        
        countPaper(0, 0, n);

        System.out.println(zeroCnt);
        System.out.print(oneCnt);
    }
    
    public static void countPaper(int r, int c, int size) {
        if (isSame(r, c, size)) {
            val = board[r][c];
            if (val == 0) zeroCnt++;
            else if (val == 1) oneCnt++;
            
            return;
        }
        
        size /= 2;
        
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                int nr = r + i * size, nc = c + j * size;
                
                countPaper(nr, nc, size);
            }    
        }
    }
    
    public static boolean isSame(int r, int c, int size) {
        val = board[r][c];
        
        for (int nr = r; nr < r + size; nr++) {
            for (int nc = c; nc < c + size; nc++) {
                if (board[nr][nc] != val) return false;
            }
        }
        
        return true;
    }

    public static int readInt() throws IOException {
        int r = 0, c = System.in.read();

        while (c <= ' ') c = System.in.read();
        while (c >= '0' && c <= '9') {
            r *= 10;
            r += c - '0';
            c = System.in.read();
        }

        return r;
    }
}
