import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static int[][] board;
    private static int n;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();

        board = new int[n][n];

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) board[r][c] = readInt();
        }

        bw.write(String.valueOf(polling(0, 0, n)));

        bw.flush();
    }

    public static int polling(int r, int c, int size) {
        if (size == 2) {
            int idx = 0;

            int[] temp = new int[4];

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    int nr = r+i, nc = c+j;

                    temp[idx++] = board[nr][nc];
                }
            }

            Arrays.sort(temp);

            return temp[2];
        }

        size /= 2;

        int[] temp = new int[4];

        temp[0] = polling(r, c, size);
        temp[1] = polling(r+size, c, size);
        temp[2] = polling(r, c+size, size);
        temp[3] = polling(r+size, c+size, size);

        Arrays.sort(temp);

        return temp[2];
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
