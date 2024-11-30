import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static int[][] points, dpMax, dpMin;
    private static int n, max, min;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();

        points = new int[n+1][4];

        for (int i = 1; i <= n; i++) {
            points[i][1] = readInt();
            points[i][2] = readInt();
            points[i][3] = readInt();
        }

        dpMax = new int[n+1][4];
        dpMin = new int[n+1][4];

        for (int i = 1; i <= n; i++) {
            dpMax[i][1] = points[i][1] + Math.max(dpMax[i-1][1], dpMax[i-1][2]);
            dpMin[i][1] = points[i][1] + Math.min(dpMin[i-1][1], dpMin[i-1][2]);

            dpMax[i][2] = points[i][2] + Math.max(dpMax[i-1][1], Math.max(dpMax[i-1][2], dpMax[i-1][3]));
            dpMin[i][2] = points[i][2] + Math.min(dpMin[i-1][1], Math.min(dpMin[i-1][2], dpMin[i-1][3]));

            dpMax[i][3] = points[i][3] + Math.max(dpMax[i-1][2], dpMax[i-1][3]);
            dpMin[i][3] = points[i][3] + Math.min(dpMin[i-1][2], dpMin[i-1][3]);
        }

        max = Math.max(dpMax[n][1], Math.max(dpMax[n][2], dpMax[n][3]));
        min = Math.min(dpMin[n][1], Math.min(dpMin[n][2], dpMin[n][3]));

        bw.write(max + " " + min);
        bw.flush();
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
