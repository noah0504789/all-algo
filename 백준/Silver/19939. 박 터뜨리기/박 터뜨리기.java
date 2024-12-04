import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static int n, k;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        k = readInt();

        n -= k*(k+1)/2;

        bw.write((n < 0 ? -1 : n % k == 0 ? k-1 : k) + "");
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
