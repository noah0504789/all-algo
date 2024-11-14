import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static PriorityQueue<Integer> maxPQ;
    private static int[][] board;
    private static int n;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();

        maxPQ = new PriorityQueue<>(Collections.reverseOrder());

        board = new int[n][n];

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) board[r][c] = readInt();
        }

        bw.write(String.valueOf(polling(0, 0, n)));

        bw.flush();
    }

    public static int polling(int r, int c, int size) {
        if (size == 2) {
            maxPQ.clear();

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    int nr = r+i, nc = c+j;

                    maxPQ.offer(board[nr][nc]);
                }
            }

            maxPQ.poll();

            return maxPQ.poll();
        }

        size /= 2;

        int a1 = polling(r, c, size);
        int a2 = polling(r+size, c, size);
        int a3 = polling(r, c+size, size);
        int a4 = polling(r+size, c+size, size);

        maxPQ.clear();

        maxPQ.offer(a1);
        maxPQ.offer(a2);
        maxPQ.offer(a3);
        maxPQ.offer(a4);

        maxPQ.poll();

        return maxPQ.poll();
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
