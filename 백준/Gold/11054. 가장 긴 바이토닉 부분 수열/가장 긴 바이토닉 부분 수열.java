import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static int[] a, lis, lds;
    private static int n, ans;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        a = new int[n];
        for (int i = 0; i < n; i++) a[i] = readInt();

        lis = new int[n];
        lis();

        lds = new int[n];
        lds();

        ans = 0;

        for (int i = 0; i < n; i++) ans = Math.max(ans, lis[i] + lds[i]);

        ans--;

        bw.write(ans+"");
        bw.flush();
    }

    public static void lis() {
        for (int i = 0; i < n; i++) {
            lis[i] = 1;
            for (int j = 0; j < i; j++) {
                if (a[i] <= a[j]) continue;
                if (lis[i] > lis[j]) continue;

                lis[i] = lis[j] + 1;
            }
        }
    }

    public static void lds() {
        for (int i = n-1; i >= 0; i--) {
            lds[i] = 1;
            for (int j = n-1; j > i; j--) {
                if (a[i] <= a[j]) continue;
                if (lds[i] > lds[j]) continue;

                lds[i] = lds[j] + 1;
            }
        }
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
