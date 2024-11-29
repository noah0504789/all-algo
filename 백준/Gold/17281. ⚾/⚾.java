import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static final int POINT_BIT = 0b1111000;
    private static int[][] records;
    private static int[] players;
//    private static boolean[] visited, bases;
    private static boolean[] visited;
    private static long max;
    private static int n, out, record, bases;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();

        records = new int[n][9];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 9; j++) records[i][j] = readInt();
        }

        players = new int[9];
        players[3] = 0;

        visited = new boolean[9];
        visited[3] = true;

//        bases = new boolean[5];

        max = 0;

        perm(1);

        bw.write(max+"");
        bw.flush();
    }

    private static void perm(int player) {
        if (player == 9) {
            max = Math.max(max, play(0, 0, 0));
            return;
        }

        for (int order = 0; order < 9; order++) {
            if (visited[order]) continue;

            visited[order] = true;
            players[order] = player;
            perm(player+1);
            visited[order] = false;
        }
    }

    private static long play(int inning, int pIdx, long points) {
        if (inning == n) return points;

        out = bases = 0;

        while (out < 3) {
            record = records[inning][players[pIdx]];

            if (record == 0) {
                out++;
            } else {
                bases <<= record;
                bases |= (1<<(record-1));

                if ((bases & POINT_BIT) > 0) {
                    points += Integer.bitCount((bases & POINT_BIT));
                    bases &= ~POINT_BIT;
                }
            }

            pIdx = (pIdx+1) % 9;
        }

        return play(inning+1, pIdx, points);
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
