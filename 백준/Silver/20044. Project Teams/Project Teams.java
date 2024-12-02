import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static int[] points;
    private static int n, length, ans;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        length = n*2;

        points = new int[length];

        for (int i = 0; i < length; i++) points[i] = readInt();

        Arrays.sort(points);

        ans = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) ans = Math.min(ans, points[i] + points[length-i-1]);

        bw.write(ans+"");
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
