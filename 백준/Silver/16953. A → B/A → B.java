import java.io.*;

public class Main {
    private static BufferedWriter bw;

    private static int a, b, cnt;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        a = readInt();
        b = readInt();

        cnt = 1;

        while (a < b) {
            if (b % 10 == 1) {
                b /= 10;
                cnt++;
            } else if (b % 2 != 0) {
                break;
            } else {
                b /= 2;
                cnt++;
            }

            if (a == b) break;
        }

        bw.write(a == b ? cnt+"" : "-1");
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
