import java.io.*;
import java.util.*;

public class Main {        
    private static BufferedReader br;
    private static StringBuilder sb;

    private static char val;
    private static char[][] board;
    private static int n;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());

        board = new char[n][n];

        for (int i = 0; i < n; i++) board[i] = br.readLine().toCharArray();

        quadtree(0, 0, n);

        System.out.print(sb);
    }

    private static void quadtree(int r, int c, int size) {
        if (isSame(r, c, size)) {
            sb.append(board[r][c]);
            return;
        }

        size /= 2;

        sb.append('(');

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                int nr = r + i * size, nc = c + j * size;
                quadtree(nr, nc, size);
            }
        }

        sb.append(')');
    }

    private static boolean isSame(int r, int c, int size) {
        val = board[r][c];

        for (int nr = r; nr < r + size; nr++) {
            for (int nc = c; nc < c + size; nc++) {
                if (board[nr][nc] != val) return false;
            }
        }

        return true;
    }
}