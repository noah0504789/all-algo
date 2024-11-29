import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static int[][] records;
    private static int[] players;
    private static boolean[] visited, bases;
    private static long max;
    private static int n, out, record;

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

        max = 0;

        perm(1);

        bw.write(max+"");
        bw.flush();
    }

    private static void perm(int player) {
        if (player == 9) {
            max = Math.max(max, play());
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

    private static long play() {
        int pIdx = 0, points = 0;

        for (int inning = 0; inning < n; inning++) {
            out = 0;
            bases = new boolean[5];

            while (out < 3) {
                record = records[inning][players[pIdx]];

                if (record == 1) {
                    for (int k = 3; k >= 1; k--) {
                        if (!bases[k]) continue;

                        bases[k] = false;
                        if (k == 3) {
                            points++;
                            continue;
                        }

                        bases[k+1] = true;
                    }

                    bases[1] = true;
                } else if (record == 2) {
                    for (int k = 3; k >= 1; k--) {
                        if (!bases[k]) continue;

                        bases[k] = false;
                        if (k >= 2) {
                            points++;
                            continue;
                        }

                        bases[k+2] = true;
                    }

                    bases[2] = true;
                } else if (record == 3) {
                    for (int k = 3; k >= 1; k--) {
                        if (!bases[k]) continue;

                        bases[k] = false;
                        points++;
                    }

                    bases[3] = true;
                } else if (record == 4) {
                    for (int k = 3; k >= 1; k--) {
                        if (!bases[k]) continue;

                        bases[k] = false;
                        points++;
                    }

                    points++;
                } else if (record == 0) {
                    out++;
                }

                pIdx = (pIdx+1) % 9;
            }
        }

        return points;
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
