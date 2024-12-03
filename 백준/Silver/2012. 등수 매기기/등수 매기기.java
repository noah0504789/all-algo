import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static int[] cnts;
    private static long ans;
    private static int n, rank;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        cnts = new int[500_001];

        n = readInt();

        for (int i = 1; i <= n; i++) cnts[readInt()]++;

        rank = 1;

        for (int i = 1; i < cnts.length; i++) {
            while (cnts[i]-- > 0) ans += Math.abs(i - rank++);
        }

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
