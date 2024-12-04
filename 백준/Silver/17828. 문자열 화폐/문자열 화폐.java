import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static char[] arr;
    private static long x, cur;
    private static int n;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        x = readLong();

        if (26 * n < x || n > x) {
            bw.write("!");
            bw.flush();
            return;
        }

        arr = new char[n];
        Arrays.fill(arr, 'A');
        x -= n;

        for (int i = n-1; i >= 0 && x > 0; i--) {
            cur = Math.min(x, 25);
            arr[i]+=cur;
            x -= cur;
        }

        bw.write(new String(arr));

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

    public static long readLong() throws IOException {
        long r = 0, c = System.in.read();

        while (c <= ' ') c = System.in.read();
        while (c >= '0' && c <= '9') {
            r *= 10;
            r += c - '0';
            c = System.in.read();
        }

        return r;
    }
}
