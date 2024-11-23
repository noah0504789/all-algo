import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static long[] remainCnt;
    private static long remain, ans;
    private static int tc, n, d;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        tc = readInt();

        while (tc-- > 0) {
            d = readInt();
            n = readInt();

            remainCnt = new long[d];
            remain = ans = 0;

            for (int i = 0; i < n; i++) {
                remain += readInt();
                remain %= d;
                
                if (remain < 0) remain += d;
                
                ans += remainCnt[(int) remain];
                
                remainCnt[(int) remain]++;
            }
            
            ans += remainCnt[0];

            bw.write(ans+"\n");
        }

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
