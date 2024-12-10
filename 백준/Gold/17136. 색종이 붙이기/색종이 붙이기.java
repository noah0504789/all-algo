import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static List<Integer> list;
    private static int[][] board, prefixSum;
    private static int[] paperCnt;
    private static int n, ans;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = 10;

        board = new int[n][n];
        prefixSum = new int[n][n];
        list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = readInt();

                int left = i-1>=0 ? prefixSum[i-1][j] : 0;
                int right = j-1>=0 ? prefixSum[i][j-1] : 0;
                int prev = i-1>=0 && j-1>=0 ? prefixSum[i-1][j-1] : 0;

                prefixSum[i][j] = left + right - prev + board[i][j];

                if (board[i][j] == 1) list.add(i*n+j);
            }
        }

        paperCnt = new int[]{0, 5, 5, 5, 5, 5};
        ans = Integer.MAX_VALUE;

        dfs(0, 0, 0);

        if (ans == Integer.MAX_VALUE) ans = -1;

        bw.write(ans+"");
        bw.flush();
    }

    private static void dfs(int r, int c, int cnt) {
        if (cnt >= ans) return;

        if (c >= 9 && r > 9) {
            ans = Math.min(ans, cnt);
            return;
        }

        if (r > 9) {
            dfs(0, c+1, cnt);
            return;
        }

        if (board[r][c] == 1) {
            for (int size = 5; size >= 1; size--) {
                if (paperCnt[size] > 0 && isAttach(r, c, size)) {
                    paperCnt[size]--;
                    attach(r, c, size, 0);
                    dfs(r+1, c, cnt+1);
                    attach(r, c, size, 1);
                    paperCnt[size]++;
                }
            }
        } else {
            dfs(r+1, c, cnt);
        }
    }

    private static boolean isAttach(int r1, int c1, int size) {
        for (int r = r1; r < r1 + size; r++) {
            for (int c = c1; c < c1 + size; c++) {
                if (r < 0 || r >= 10 || c < 0 || c >= 10) return false;
                if (board[r][c] == 0) return false;
            }
        }
        
        return true;
    }

    private static void attach(int cr, int cc, int size, int state) {
        for (int r = cr; r < cr + size; r++) {
            for (int c = cc; c < cc + size; c++) {
                board[r][c] = state;
            }
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