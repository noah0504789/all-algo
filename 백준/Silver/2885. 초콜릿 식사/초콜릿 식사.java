import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static int k, n, cnt;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        k = readInt();
        n = 1;
        cnt = 0;

        while (n < k) n *= 2;

        bw.write(n+" ");

        while (k>0) {
            if (k >= n) {
                k -= n;
            } else {
                n /= 2;
                cnt++;
            }
        }

        bw.write(cnt+"");
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
