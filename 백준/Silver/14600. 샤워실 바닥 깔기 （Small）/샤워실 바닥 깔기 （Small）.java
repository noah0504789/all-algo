import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static int[][] board;
    private static int k, x, y, r, c, size, num;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        k = readInt();
        size = (int) Math.pow(2, k);

        board = new int[size][size];

        x = readInt()-1;
        y = readInt()-1;

        r = y;
        c = x;

        r = size - 1 - r;

        board[r][c] = -1;

        num = 0;

        quadPaint(0, 0, size);

        print();

        bw.flush();
    }

    private static void quadPaint(int r, int c, int size) {
        num++;
        
        size /= 2;
        
        if (check(r, c, size)) board[r+size-1][c+size-1] = num;
        if (check(r, c+size, size)) board[r+size-1][c+size] = num;
        if (check(r+size, c, size)) board[r+size][c+size-1] = num;
        if (check(r+size, c+size, size)) board[r+size][c+size] = num;
        
        if (size == 1) return;
        
        quadPaint(r, c, size);
        quadPaint(r, c+size, size);
        quadPaint(r+size, c, size);        
        quadPaint(r+size, c+size, size);
    }

    private static boolean check(int r, int c, int size) {
        for (int nr = r; nr < r + size; nr++) {
            for (int nc = c; nc < c + size; nc++) {
                if (board[nr][nc] != 0) return false;
            }
        }

        return true;
    }

    private static void print() throws IOException {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) bw.write(board[r][c] + " ");
            bw.write('\n');
        }
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
