import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static int n, m, ans;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        m = readInt();

        if (n == 1) ans = 1;
        else if (n == 2) ans = Math.min(4, (m+1)/2);
        else if (m < 7) ans = Math.min(4, m);
        else if (m >= 7) ans = m-2;

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
