import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static List<int[]>[] ranges;
    private static BitSet visited;
    private static int[][] board, temp;
    private static int[] arrows;
    private static int n, m, d, ar, ac, ans, maxTurn;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        m = readInt();
        n = readInt();
        d = readInt();

        board = new int[n][m];

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) board[n-1-c][r] = readInt();
        }

        maxTurn = ac = m;
        arrows = new int[3];
        temp = new int[n][m];

        ranges = new ArrayList[n];
        for (int ar = 0; ar < n; ar++) {
            ranges[ar] = new ArrayList<>();

            for (int w = 1; w <= d; w++) {
                int c = ac-w;
                if (c < 0) break;

                ranges[ar].add(new int[]{ar, c, dist(ar, ac, ar, c)});

                for (int h = d-w; h >= 1; h--) {
                    int ur = ar - h, dr = ar + h;
                    if (ur >= 0) ranges[ar].add(new int[]{ur, c, dist(ar, ac, ur, c)});
                    if (dr < n) ranges[ar].add(new int[]{dr, c, dist(ar, ac, dr, c)});
                }
            }

            Collections.sort(ranges[ar], (a, b) -> {
                if (a[2] == b[2]) return b[0] - a[0];

                return a[2] - b[2];
            });
        }

        visited = new BitSet();
        ans = 0;

        comb(0, 0);

        bw.write(ans+"");
        bw.flush();
    }

    private static void comb(int depth, int start) {
        if (depth == 3) {
            ans = Math.max(ans, game());
            return;
        }

        for (int i = start; i < n; i++) {
            arrows[depth] = i;
            comb(depth+1, i+1);
        }
    }

    private static int game() {
        for (int r = 0; r < n; r++) System.arraycopy(board[r], 0, temp[r], 0, m);

        int killCnt = 0;

        for (int turn = 1; turn <= maxTurn; turn++) {
            visited.clear();
            // 궁수 - 공격
            for (int i = 2; i >= 0; i--) {
                int ar = arrows[i];

                for (int[] range : ranges[ar]) {
                    int tr = range[0], tc = range[1];
                    if (temp[tr][tc] == 0) continue;
                    if (visited.get(tr*m+tc)) break;

                    visited.set(tr*m+tc);
                    break;
                }
            }

            killCnt += visited.cardinality();

            int next = 0;
            while ((next = visited.nextSetBit(next)) != -1) {
                int r = next / m, c = next % m;
                temp[r][c] = 0;
                visited.clear(next);
            }

            // 적 - 이동
            for (int r = 0; r < n; r++) {
                System.arraycopy(temp[r], 0, temp[r], 1, m-1);
                temp[r][0] = 0;
            }
        }

        return killCnt;
    }

    private static int dist(int ar, int ac, int tr, int tc) {
        return Math.abs(ar - tr) + Math.abs(ac - tc);
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
