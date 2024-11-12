import java.io.*;
import java.util.*;

public class Main {
    private static StringBuilder sb;

    private static char[][] board;
    private static int n;

    public static void main(String... args) throws IOException {
        sb = new StringBuilder();

        n = readInt();

        board = new char[n][n*2-1];

        star(0, n-1, n);

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) sb.append(board[r][c] == '\u0000' ? ' ' : board[r][c]);

            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static void star(int r, int c, int height) {
        if (height == 3) {
            board[r][c] = '*';
            board[r+1][c-1] = board[r+1][c+1] = '*';
            board[r+2][c-2] = board[r+2][c-1] = board[r+2][c] = board[r+2][c+1] = board[r+2][c+2] ='*';
            return;
        }

        int half = height / 2;

        star(r, c, half);
        star(r+half, c-half, half);
        star(r+half, c+half, half);
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
