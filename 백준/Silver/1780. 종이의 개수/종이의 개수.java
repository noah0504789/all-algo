import java.io.*;
import java.util.*;

public class Main {
    private static int[][] board;
    private static int n, oneCnt, zeroCnt, minusCnt;

    public static void main(String... args) throws IOException {
        n = readInt();  
        
        board = new int[n][n];        
        
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) board[r][c] = readInt();
        }
                
        oneCnt = zeroCnt = minusCnt = 0;        
        
        countPaper(0, 0, n);

        System.out.println(minusCnt);
        System.out.println(zeroCnt);
        System.out.println(oneCnt);
    }
    
    public static void countPaper(int r, int c, int size) {
        if (isSame(r, c, size)) {
            if (board[r][c] == -1) minusCnt++;
            else if (board[r][c] == 0) zeroCnt++;
            else if (board[r][c] == 1) oneCnt++;
            
            return;
        }
        
        size /= 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                countPaper(r+i*size, c+j*size, size);
            }    
        }
    }
    
    public static boolean isSame(int r, int c, int size) {
        int num = board[r][c];
        for (int nr = r; nr < r+size; nr++) {
            for (int nc = c; nc < c+size; nc++) {
                if (board[nr][nc] != num) return false;
            }    
        }
        
        return true;
    }
        
    public static int readInt() throws IOException {
        int r = 0, c = System.in.read();

        while (c <= ' ') c = System.in.read();
        boolean negative = false;
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
