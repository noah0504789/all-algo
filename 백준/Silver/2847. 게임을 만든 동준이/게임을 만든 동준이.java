import java.io.*;

public class Main {
    private static BufferedWriter bw;

    private static int[] points;
    private static int n, cur, prev, ans;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();

        points = new int[n+1];
        for (int i = 1; i <= n; i++) points[i] = readInt();

        ans = 0;
        for (int i = n; i > 1; i--) {
            prev = points[i-1];
            cur = points[i];

            if (prev < cur) continue;

            ans += prev - cur + 1;
            points[i-1] = cur - 1;
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
