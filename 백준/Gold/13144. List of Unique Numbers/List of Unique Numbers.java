import java.io.*;
import java.util.*;

public class Main {
    private static long cnt;
    private static int[] seq, counts;
    private static int n, l, r;

    public static void main(String... args) throws IOException {
        n = readInt();
        seq = new int[n];

        for (int i = 0; i < n; i++) seq[i] = readInt();

        cnt = 0;
        l = r = 0;

        counts = new int[100_001];

        while (l < n) {
            while (r < n && counts[seq[r]] == 0) counts[seq[r++]]++;

            cnt += (r - l);

            counts[seq[l++]]--;
        }

        System.out.print(cnt);
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
