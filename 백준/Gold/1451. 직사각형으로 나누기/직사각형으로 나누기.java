import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static int[][] board, prefixSum;
    private static long max, mul, one, two, three;
    private static int n, m, left, right, prev;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        m = readInt();

        board = new int[n+1][m+1];
        prefixSum = new int[n+1][m+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                board[i][j] = prefixSum[i][j] = readBit();

                left = prefixSum[i-1][j];
                right = prefixSum[i][j-1];
                prev = prefixSum[i-1][j-1];

                prefixSum[i][j] += left;
                prefixSum[i][j] += right;
                prefixSum[i][j] -= prev;
            }
        }

        max = 0;

        // 세로 3개
        for (int i = 1; i <= m-2; i++) {
            for (int j = i+1; j <= m-1; j++) {
                one = getArea(1, 1, n, i);
                two = getArea(1, i+1, n, j);
                three = getArea(1, j+1, n, m);
                
                max = Math.max(max, one*two*three);
            }
        }

        // 가로 3개
        for (int i = 1; i <= n-2; i++) {
            for (int j = i+1; j <= n-1; j++) {
                one = getArea(1, 1, i, m);
                two = getArea(i+1, 1, j, m);
                three = getArea(j+1, 1, n, m);
                
                max = Math.max(max, one*two*three);
            }
        }
        
        // 가로 1개(위) + 세로 2개
        for (int i = 1; i <= n-1; i++) {
            for (int j = 1; j <= m-1; j++) {
                one = getArea(1, 1, i, m);
                two = getArea(i+1, 1, n, j);
                three = getArea(i+1, j+1, n, m);
                
                max = Math.max(max, one*two*three);
            }
        }
        
        // 가로 1개(아래) + 세로 2개
        for (int i = 1; i <= n-1; i++) {
            for (int j = 1; j <= m-1; j++) {
                one = getArea(1, 1, i, j);
                two = getArea(1, j+1, i, m);
                three = getArea(i+1, 1, n, m);
                
                max = Math.max(max, one*two*three);
            }
        }

        // 세로 1개(왼) + 가로 2개
        for (int i = 1; i <= n-1; i++) {
            for (int j = 1; j <= m-1; j++) {
                one = getArea(1, 1, n, j);
                two = getArea(1, j+1, i, m);
                three = getArea(i+1, j+1, n, m);
                
                max = Math.max(max, one*two*three);
            }
        }

        // 세로 1개(오) + 가로 2개
        for (int i = 1; i <= n-1; i++) {
            for (int j = 1; j <= m-1; j++) {
                one = getArea(1, 1, i, j);
                two = getArea(i+1, 1, n, j);
                three = getArea(1, j+1, n, m);
                
                max = Math.max(max, one*two*three);
            }
        }

        bw.write(max+"");
        bw.flush();
    }

    private static long getArea(int sr, int sc, int er, int ec) {
        return prefixSum[er][ec] - prefixSum[sr-1][ec] - prefixSum[er][sc-1] + prefixSum[sr-1][sc-1];
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

    public static int readBit() throws IOException {
        int c = System.in.read();

        while (c <= ' ') c = System.in.read();
        if (c >= '0' && c <= '9') return c - '0';

        return 0;
    }
}
