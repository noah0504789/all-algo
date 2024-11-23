import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static long[] odds, evens;
    private static long ans, lastEven, odd, even;
    private static int n, size;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        size = n / 2;

        odds = new long[size+1];
        evens = new long[size+1];

        for (int i = 1; i <= size; i++) {
            odds[i] = odds[i-1] + readInt();
            evens[i] = evens[i-1] + readInt();
        }

        ans = odds[size];
        lastEven = evens[size] - evens[size-1];

        for (int i = 0; i <= size; i++) {
            odd = odds[i];
            even = evens[size] - evens[i];

            if (i > 0) even = Math.max(even, evens[size] - evens[i-1] - lastEven);

            ans = Math.max(ans, odd+even);
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