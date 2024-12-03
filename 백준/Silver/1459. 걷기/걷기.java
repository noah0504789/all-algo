import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static long x, y, w, s, ans, maxDiag, minDiag;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        x = readLong();
        y = readLong();
        w = readLong();
        s = readLong();

        maxDiag = Math.max(x, y);
        minDiag = Math.min(x, y);

        ans = (x+y)*w;

        if ((x+y)%2 == 0) ans = Math.min(ans, maxDiag*s);
        else ans = Math.min(ans, (maxDiag-1)*s+w);

        ans = Math.min(ans, minDiag*s + Math.abs(x-y)*w);

        bw.write(ans+"");
        bw.flush();
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
