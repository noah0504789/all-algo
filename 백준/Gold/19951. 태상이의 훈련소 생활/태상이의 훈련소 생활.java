import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static int[] ground, acc;
    private static int n, m, src, dest, k;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        m = readInt();

        ground = new int[n+1];
        for (int i = 1; i <= n; i++) ground[i] = readInt();
        
        acc = new int[n+2];

        for (int i = 0; i < m; i++) {
            src = readInt();
            dest = readInt();
            k = readInt();

            acc[src] += k;
            acc[dest+1] -= k;
        }

        for (int i = 1; i <= n; i++) {
            acc[i] += acc[i-1];

            bw.write((acc[i]+ground[i]) + " ");
        }

        bw.flush();
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
