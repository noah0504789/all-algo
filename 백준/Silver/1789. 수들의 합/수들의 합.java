import java.io.*;

public class Main {
    private static BufferedWriter bw;

    private static long s, cnt, sum;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        s = readLong();

        if (s == 1) {
            bw.write("1");
            bw.flush();
            return;
        }
        
        sum = 0;
        cnt = 1;

        while (sum + cnt <= s) {
            sum += cnt++;
        }

        bw.write((cnt-1)+"");
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
