import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static char[][] board;
    private static int[][] wFirstMismatch, bFirstMismatch;
    private static int n, m, k, min;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new char[n][m];

        for (int i = 0; i < n; i++) board[i] = br.readLine().toCharArray();

        wFirstMismatch = new int[n+1][m+1];
        bFirstMismatch = new int[n+1][m+1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char wFirstExpect = (i+j) % 2 == 0 ? 'W' : 'B';
                char bFirstExpect = (i+j) % 2 == 0 ? 'B' : 'W';

                wFirstMismatch[i+1][j+1] = wFirstMismatch[i][j+1] + wFirstMismatch[i+1][j] - wFirstMismatch[i][j] + (board[i][j] == wFirstExpect ? 0 : 1);
                bFirstMismatch[i+1][j+1] = bFirstMismatch[i][j+1] + bFirstMismatch[i+1][j] - bFirstMismatch[i][j] + (board[i][j] == bFirstExpect ? 0 : 1);
            }
        }

        min = Integer.MAX_VALUE;

        for (int r1 = 1; r1 <= n-k+1; r1++) {
            for (int c1 = 1; c1 <= m-k+1; c1++) {
                int r2 = r1+k-1, c2 = c1+k-1;

                min = Math.min(min, wFirstMismatch[r2][c2] - wFirstMismatch[r1-1][c2] - wFirstMismatch[r2][c1-1] + wFirstMismatch[r1-1][c1-1]);
                min = Math.min(min, bFirstMismatch[r2][c2] - bFirstMismatch[r1-1][c2] - bFirstMismatch[r2][c1-1] + bFirstMismatch[r1-1][c1-1]);
            }
        }

        bw.write(min+"");
        bw.flush();
    }
}
