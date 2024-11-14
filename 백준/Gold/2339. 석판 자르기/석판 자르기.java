import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;
    private static int ans;
    private static byte[][] board;
    private static byte n;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readByte();

        board = new byte[n][n];

        for (byte r = 0; r < n; r++) {
            for (byte c = 0; c < n; c++) board[r][c] = readByte();
        }

        ans = divideAndConquer(0, n-1, 0, n-1, -1);

        bw.write(String.valueOf(ans > 0 ? ans : -1));

        bw.flush();
    }

    // dir: 가로 -> 0, 세로 -> 1
    public static int divideAndConquer(int rl, int rh, int cl, int ch, int dir) {
        BitSet jewelR = new BitSet(), jewelC = new BitSet();
        BitSet impurityR = new BitSet(), impurityC = new BitSet();

        int iCnt = 0, jCnt = 0;

        for (byte r = (byte) rl; r <= rh; r++) {
            for (byte c = (byte) cl; c <= ch; c++) {
                if (board[r][c] == 1) {
                    impurityR.set(r);
                    impurityC.set(c);
                    iCnt++;
                } else if (board[r][c] == 2) {
                    jewelR.set(r);
                    jewelC.set(c);
                    jCnt++;
                }
            }
        }

        if (jCnt - iCnt != 1) return 0;
        if (jCnt == 1 && iCnt == 0) return 1;

        int res = 0;

        int ni = 0;
        if (dir != 0) {
            // 세로
            while ((ni = impurityR.nextSetBit(ni)) != -1) {
                if (!jewelR.get(ni)) res += (divideAndConquer(rl, ni-1, cl, ch, 0) * divideAndConquer(ni+1, rh, cl, ch, 0));

                ni++;
            }
        }

        ni = 0;
        if (dir != 1) {
            // 가로
            while ((ni = impurityC.nextSetBit(ni)) != -1) {
                if (!jewelC.get(ni)) res += (divideAndConquer(rl, rh, cl, ni-1, 1) * divideAndConquer(rl, rh, ni+1, ch, 1));

                ni++;
            }
        }

        return res;
    }

    public static byte readByte() throws IOException {
        byte r = 0, c = (byte) System.in.read();

        while (c <= ' ') c = (byte) System.in.read();
        while (c >= '0' && c <= '9') {
            r *= 10;
            r += c - '0';
            c = (byte) (System.in.read());
        }

        return r;
    }
}
